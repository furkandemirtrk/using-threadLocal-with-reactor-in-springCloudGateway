package com.demirturk.springcloudgatewaycontext.context;

import io.micrometer.context.ThreadLocalAccessor;

public class ThreadLocalContext implements ThreadLocalAccessor<String> {

    public static final String KEY = "micrometer.observation";

    @Override
    public Object key() {
        return KEY;
    }

    @Override
    public String getValue() {
        return ThreadLocalHolder.getValue();
    }

    @Override
    public void setValue(String value) {
        ThreadLocalHolder.setValue(value);
    }

    @Override
    public void reset() {
        ThreadLocalHolder.reset();
    }
}
