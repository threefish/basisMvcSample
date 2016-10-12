package com.sample.aop;

import com.sgaop.basis.annotation.Aspect;
import com.sgaop.basis.annotation.Control;
import com.sgaop.basis.annotation.IocBean;
import com.sgaop.basis.aop.InterceptorProxy;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: 306955302@qq.com
 * Date: 2016/10/9 0009
 * To change this template use File | Settings | File Templates.
 */
@IocBean
@Aspect(annotation = Control.class)
public class ShiroAuthcAspect extends InterceptorProxy {

    private static final Logger logger = Logger.getRootLogger();


    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        logger.debug("---------- before ShiroAuthcAspect ----------");
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
    }

    @Override
    protected void exception(Class<?> cls, Method method, Object[] params, Throwable e) {

    }

    @Override
    protected void finalize() {

    }
}
