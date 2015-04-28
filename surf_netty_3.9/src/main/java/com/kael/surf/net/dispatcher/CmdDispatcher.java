package com.kael.surf.net.dispatcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.GeneratedMessage;
import com.kael.surf.net.codec.IMessage;
import com.kael.surf.net.config.CmdsConfig;
import com.kael.surf.net.config.ReqCmdProperties;
import com.kael.surf.net.logic.SynRoomPlayer;
import com.kael.surf.net.mutithread.GlobalBusinessThreadPool;

public class CmdDispatcher {
    /**
     * key = serviceName + "." + method + "." + First para's protoJavaType + "."
     * + Second para's protoJavaType...
     */
    private Map<String, MethodInvoker> methodInvokersMap = new HashMap<String, MethodInvoker>();

    private ApplicationContext ac;

    private static final Logger logger = Logger.getLogger("synfight");

    private final CmdsConfig cmdsConfig;

//    private SynRoomBusinessThreadPool businessThreadPool;

    private GlobalBusinessThreadPool globalThreadPool;

//    private ExceptionHandler exceptionHandler;

    public CmdDispatcher(CmdsConfig cmdsConfig,/** SynRoomBusinessThreadPool businessThreadPool,*/ GlobalBusinessThreadPool globalThreadPool/**, ExceptionHandler exceptionHandler*/) {
        this.cmdsConfig = cmdsConfig;
//        this.businessThreadPool = businessThreadPool;
        this.globalThreadPool = globalThreadPool;
//        this.exceptionHandler = exceptionHandler;
    }

    /**
     * 只允许在服务器开始的时候调用
     * 
     * @param ac
     */
    public void init(ApplicationContext ac) {
        this.ac = ac;
        initServiceMethodMap();
    }

    private String convertToServiceMethodKey(String serviceName, String methodName, int paraNum) {
        return serviceName + "." + methodName + "." + paraNum;
    }

    private void initServiceMethodMap() {
        for (ReqCmdProperties cmdProperties : cmdsConfig.getCmdsMap().values()) {
            String serviceName = cmdProperties.getServiceName();
            String methodName = cmdProperties.getMethodName();
            Object service = ac.getBean(serviceName);
            if (service == null) {
                logger.error("init cmd.xml'serviceMethod definition, but can't find the service: serviceName = "
                        + serviceName + ", methodName = " + methodName);
                throw new Error("init cmd.xml'serviceMethod definition, but can't find the service: serviceName = "
                        + serviceName + ", methodName = " + methodName);
            }
            
            Method[] methods = service.getClass().getMethods();

            boolean hadFind = false;
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    int paraNum = method.getParameterTypes().length;
                    String key = convertToServiceMethodKey(serviceName, methodName, paraNum - 1); //默认真正的service方法会比protobuf中的参数少一个
                    /*if(methodInvokersMap.containsKey(key)) {
                        throw new Error("declare duplicate service method");
                    }*/
                    FastClass fastClass = FastClass.create(service.getClass());
                    FastMethod fastMethod = fastClass.getMethod(methodName, method.getParameterTypes());
                    MethodInvoker methodInvoker = new MethodInvoker(fastMethod, service);
                    methodInvokersMap.put(key, methodInvoker);
                    hadFind = true;
                }
            }
            if (!hadFind) {
                logger.error("init cmd.xml'serviceMethod definition, but can't find the method: serviceName = "
                        + serviceName + ", methodName = " + methodName);
                throw new Error("init cmd.xml'serviceMethod definition, but can't find the method: serviceName = "
                        + serviceName + ", methodName = " + methodName);
            }

        }
    }

    /**
     * 
     * @param context
     * @param cmd
     */
    public void dispatch(final SynRoomPlayer player,/** final SynRoomBusinessCommand cmd*/final IMessage msg) throws Exception {
    	
    	
        final ReqCmdProperties cmdProperties = cmdsConfig.getCmdProperties(msg.getCode());
        
        //if (cmdProperties.isGlobalCmd()) {
            globalThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        dispatch0(msg, cmdProperties, player);
                    } catch (Throwable e) {
//                        exceptionHandler.handleException(e, player.getChannelHandlerCtx());
                    }
                }
            });

            /** } else if(cmdProperties.isSynRoomCmd()) {
            SynRoom synRoom = player.getLocation();
            if (synRoom == null) {
                logger.error("get an synRoom msg,but the location is null, playerId=" + player.getPlayerId());
                return;
            }
            businessThreadPool.execute(new SynRoomTask(synRoom, player.getChannelHandlerCtx()) {
                @Override
                public void processTask() {
                    try {
                        dispatch0(cmd, cmdProperties, player);
                    } catch (Throwable e) {
                        exceptionHandler.handleException(e, player.getChannelHandlerCtx());
                    }
                }
            });
        } else if(cmdProperties.isDirectCmd()) {
            try {
                dispatch0(cmd, cmdProperties, player);
            } catch (Throwable e) {
                exceptionHandler.handleException(e, player.getChannelHandlerCtx());
            }
        }*/
    }

    private void dispatch0(IMessage cmd,/**SynRoomBusinessCommand cmd, */ReqCmdProperties cmdProperties, SynRoomPlayer player)
            throws Throwable {
        GeneratedMessage proto = cmd.paser(cmdProperties);
        String serviceName = cmdProperties.getServiceName();
        String methodName = cmdProperties.getMethodName();
        List<FieldDescriptor> fields = proto.getDescriptorForType().getFields();
        Object[] params = getSortedParams(fields, proto, player); //把player放入params作为最后一个参数
        String key = getMethodInvokerKey(serviceName, methodName, fields);
        MethodInvoker methodInvoker = methodInvokersMap.get(key);

        if (methodInvoker == null) {
            logger.error("Can't find the method: serviceName = " + serviceName + ", methodName = " + methodName
                    + ", protoName = " + proto.getClass().getSimpleName() + ", paraNum = " + fields.size());
        }

        try {
            methodInvoker.invoke(params);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
    
    private Object[] getSortedParams(List<FieldDescriptor> fields, GeneratedMessage proto, SynRoomPlayer player) {
        int size = fields.size();
        Object[] params = new Object[size + 1];
        for (int i = 0; i < size; i++) {
            FieldDescriptor fieldDescriptor = fields.get(i);
            params[i] = proto.getField(fieldDescriptor);
        }
        params[size] = player;
        return params;
    }

    private String getMethodInvokerKey(String serviceName, String methodName, List<FieldDescriptor> fields) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(serviceName);
        stringBuilder.append(".");
        stringBuilder.append(methodName);
        int size = fields.size();
        stringBuilder.append(".");
        stringBuilder.append(size);
        String key = stringBuilder.toString();
        return key;
    }

}