package com.kael.surf.net.config;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.protobuf.GeneratedMessage;

public class CmdsConfig {
    private final Map<Integer, ReqCmdProperties> reqCmdsMap = new HashMap<Integer, ReqCmdProperties>();

    private final Map<GeneratedMessage, ReqCmdProperties> proto2reqCmdsMap = new HashMap<GeneratedMessage, ReqCmdProperties>();

    private final Logger logger;

//    private final Map<String, Integer> respCmdsMap = new HashMap<String, Integer>();
    
    public enum CmdDomain {
        direct,global,synRoom
    }
    
    //synfight
    public CmdsConfig(String reqCmdUrl, Logger logger) {
        this.logger = logger;
        init(reqCmdUrl);
    }
    
    private void init(String reqCmdUrl) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream reqCmdInputStream = loader.getResourceAsStream(reqCmdUrl);
//        InputStream respCmdInputStream = loader.getResourceAsStream(respCmdUrl);
        try {
            initReqCmd(reqCmdInputStream);
//            initRespCmd(respCmdInputStream);
        } catch (DocumentException e) {
            logger.error("load cmd.xml fail", e);
            throw new Error(e);
        } catch (IllegalArgumentException e) {
            logger.error("load cmd.xml fail", e);
            throw new Error(e);
        } catch (SecurityException e) {
            logger.error("load cmd.xml fail", e);
            throw new Error(e);
        } catch (InstantiationException e) {
            logger.error("load cmd.xml fail", e);
            throw new Error(e);
        } catch (IllegalAccessException e) {
            logger.error("load cmd.xml fail", e);
            throw new Error(e);
        } catch (ClassNotFoundException e) {
            logger.error("load cmd.xml fail", e);
            throw new Error(e);
        } catch (InvocationTargetException e) {
            logger.error("load cmd.xml fail", e);
            throw new Error(e);
        } catch (NoSuchMethodException e) {
            logger.error("load cmd.xml fail", e);
            throw new Error(e);
        }
    }
    
//    @SuppressWarnings("unchecked")
//    public void initRespCmd(InputStream inputStream) throws DocumentException, InstantiationException, IllegalAccessException,
//            ClassNotFoundException, IllegalArgumentException, SecurityException, InvocationTargetException,
//            NoSuchMethodException {
//        SAXReader reader = new SAXReader();
//        Document doc = reader.read(inputStream);
//        List<Element> elements = doc.getRootElement().elements();
//        for (Element cmdTypeEle : elements) {
//            int id = Integer.parseInt(cmdTypeEle.elementText("ID"));
//            String protoName = cmdTypeEle.elementText("ProtoName");
//            this.respCmdsMap.put(protoName, id);
//        }
//    }

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
            String domain = cmdTypeEle.elementText("Region");
//            CmdDomain cmdDomain = CmdDomain.valueOf(domain);
            
            GeneratedMessage proto = (GeneratedMessage) Class.forName(protoName).getMethod("getDefaultInstance", null)
                    .invoke(null, null);
            if (proto == null) {
                logger.error("load cmd.xml fail, can't find the proto: name = " + protoName);
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
    
//    public Integer getRespCmdId(String protoName) {
//        return respCmdsMap.get(protoName);
//    }

}