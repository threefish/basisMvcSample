package com.sample.aop;

import com.sgaop.basis.annotation.IocBean;
import com.sgaop.basis.aop.Interceptor.AbstractMethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: 306955302@qq.com
 * Date: 2016/10/9 0009
 * To change this template use File | Settings | File Templates.
 */
@IocBean("TarnsAop")
public class TarnsAop extends AbstractMethodInterceptor {

    @Override
    protected void finalize(Object obj, Method method, Object[] args, MethodProxy proxy) {

    }

    @Override
    protected void exception(Object obj, Method method, Object[] args, MethodProxy proxy) {

    }

    @Override
    protected void after(Object obj, Method method, Object[] args, MethodProxy proxy) {
        System.out.println("方法执行后1");
    }

    @Override
    protected void before(Object obj, Method method, Object[] args, MethodProxy proxy) {
        System.out.println("方法执行前2");
    }
}
