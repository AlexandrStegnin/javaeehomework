package ru.stegnin.javaee.boot;

import ru.stegnin.javaee.annotation.Logged;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Logged
@Interceptor
public class LogInterceptor {
    private static final Logger LOGGER = Logger.getLogger(LogInterceptor.class.getSimpleName());

    @AroundInvoke
    public Object addLog(InvocationContext context) throws Exception {
        LOGGER.info(context.getMethod().getName());
        return context.proceed();
    }
}
