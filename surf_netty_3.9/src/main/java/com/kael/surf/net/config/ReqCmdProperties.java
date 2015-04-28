package com.kael.surf.net.config;

import com.google.protobuf.GeneratedMessage;

public class ReqCmdProperties {
    private final int cmdId;

    private final String protoName;

    private final String serviceName;

    private final String methodName;

//    private final CmdDomain cmdDomain;

    private final GeneratedMessage defaultInstance;

    public ReqCmdProperties(int cmdId, String protoName, String serviceName, String methodName, GeneratedMessage proto
//    		,            CmdDomain cmdDomain
            ) {
        this.cmdId = cmdId;
        this.protoName = protoName;
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.defaultInstance = proto;
//        this.cmdDomain = cmdDomain;
    }

    public int getCmdId() {
        return cmdId;
    }

    public String getProtoName() {
        return protoName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public GeneratedMessage getDefaultInstance() {
        return defaultInstance;
    }

//    public CmdDomain getCmdDomain() {
//        return cmdDomain;
//    }
//
//    public boolean isGlobalCmd() {
//        return cmdDomain == CmdDomain.global;
//    }
//
//    public boolean isSynRoomCmd() {
//        return cmdDomain == CmdDomain.synRoom;
//    }
//    
//    public boolean isDirectCmd() {
//        return cmdDomain == CmdDomain.direct;
//    }

}