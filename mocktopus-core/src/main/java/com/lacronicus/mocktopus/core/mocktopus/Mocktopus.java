package com.lacronicus.mocktopus.core.mocktopus;

import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by fdoyle on 7/16/14.
 */
public class Mocktopus {

    static Mocktopus mocktopus;

    public Map<Type, Object> services;

    public Map<Type, MockInvocationHandler> handlers;

    private Mocktopus() {
        handlers = new HashMap<Type, MockInvocationHandler>();
        services = new HashMap<Type, Object>();
    }


    public static Mocktopus getInstance() {
        if(mocktopus == null) {
            mocktopus = new Mocktopus();
        }
        return mocktopus;
    }

    public <T> T initApi(Class<T> api) {

        MockInvocationHandler handler = new MockInvocationHandler(api);
        T service = (T) Proxy.newProxyInstance(
                api.getClassLoader(),
                new Class[]{api}, // is this right?
                handler
        );

        services.put(api,service);
        handlers.put(api, handler);
        return service;
    }


    public MockInvocationHandler getHandler(Type apiType) {
        return handlers.get(apiType);
    }

    public void addErrorState(String stateName, Class toReturn) {
        //todo
    }

    public int getApiCount() {
        return services.size();
    }

    public Set<Type> getApiSet() {
        return services.keySet();
    }
}