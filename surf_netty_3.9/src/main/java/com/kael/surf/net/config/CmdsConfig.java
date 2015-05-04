package com.kael.surf.net.config;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.protobuf.GeneratedMessage;

public class CmdsConfig {
    private final Map<Integer, ReqCmdProperties> reqCmdsMap = new HashMap<Integer, ReqCmdProperties>();

    private final Map<GeneratedMessage, ReqCmdProperties> proto2reqCmdsMap = new HashMap<GeneratedMessage, ReqCmdProperties>();

    public enum CmdDomain {
        direct,global,synRoom
    }
    
    //synfight
    public CmdsConfig(String reqCmdUrl) {
        init(reqCmdUrl);
    }
    
    private void init(String reqCmdUrl) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream reqCmdInputStream = loader.getResourceAsStream(reqCmdUrl);
        try {
            initReqCmd(reqCmdInputStream);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void initReqCmd(InputStream inputStream) throws DocumentException, InstantiationException, IllegalAccessException,
            ClassNotFoundException, IllegalArgumentException, SecurityException, InvocationTargetException,
            NoSuchMethodException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(inputStream);
        List<Element> elements = doc.getRootElement().elements();
        for (Element cmdTypeEle : elements) {
            int id = Integer.parseInt(cmdTypeEle.elementText("ID"));
            String protoName = cmdTypeEle.elementText("ProtoName");
            String serviceName = cmdTypeEle.elementText("Service");
            String methodName = cmdTypeEle.elementText("Method");

            GeneratedMessage proto = (GeneratedMessage) Class.forName(protoName).getMethod("getDefaultInstance", null)
                    .invoke(null, null);
            if (proto == null) {
                System.err.println("load cmd.xml fail, can't find the proto: name = " + protoName);
                throw new Error("load cmd.xml fail, can't find the proto: name = " + protoName);
            }
            ReqCmdProperties cmdProperties = new ReqCmdProperties(id, protoName, serviceName, methodName, proto);

            reqCmdsMap.put(id, cmdProperties);
            proto2reqCmdsMap.put(proto, cmdProperties);
        }
    }

    public Map<Integer, ReqCmdProperties> getCmdsMap() {
        return reqCmdsMap;
    }

    public ReqCmdProperties getReqCmdProperties(GeneratedMessage proto) {
        return proto2reqCmdsMap.get(proto);
    }

    public ReqCmdProperties getCmdProperties(int cmdType) {
        return reqCmdsMap.get(cmdType);
    }
    
}