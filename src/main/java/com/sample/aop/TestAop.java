package com.sample.aop;

import com.sgaop.basis.annotation.IocBean;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: 306955302@qq.com
 * Date: 2016/10/9 0009
 * To change this template use File | Settings | File Templates.
 */
@IocBean("TestAop")
public class TestAop implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("方法执行前");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("方法执行后");
        return result;
    }
}
