package com.kael.surf.net.dispatcher;

import java.lang.reflect.InvocationTargetException;
import net.sf.cglib.reflect.FastMethod;
public class MethodInvoker {
    
    private final FastMethod fastMethod;
    
    private final Object instance;

    public MethodInvoker(FastMethod fastMethod, Object instance) {
        this.fastMethod = fastMethod;
        this.instance = instance;
    }
    
    /**
     * TODO the caller need to deal with exception
     * @throws InvocationTargetException 
     */
    public void invoke(Object[] params) throws InvocationTargetException {
        fastMethod.invoke(instance, params);
    }
    
}