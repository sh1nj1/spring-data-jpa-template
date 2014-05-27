package com.chk0ndanger.springframework.context;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.context.ApplicationContext;

public class ApplicationContextUtils {

    public static final String ENVIRONMENT_DEFAULT_KEY = "config.env";

    private static final AtomicReference<ApplicationContext> context = new AtomicReference<ApplicationContext>();

    public static ApplicationContext getContext() {

        ApplicationContext ctx = context.get();
        if (ctx == null)
            throw new IllegalStateException();
        return ctx;
    }

    public static boolean isInitialized() {

        return context.get() == null;
    }

    public static void setContext(final ApplicationContext ctx) {

        if (ctx == null)
            throw new IllegalArgumentException();
        if (!context.compareAndSet(null, ctx))
            throw new IllegalStateException();
    }

    public static <T> T getBean(final String name, final Class<T> type) {

        if (type == null)
            throw new IllegalArgumentException();
        return type.cast(getContext().getBean(name, type));
    }

    public static <T> T getBean(final Class<T> type) {

        return type.cast(getContext().getBean(type));
    }

    private ApplicationContextUtils() {

        throw new AssertionError();
    }

}