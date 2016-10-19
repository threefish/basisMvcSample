package com.sample.aop;

import com.sample.error.AuthzException;
import com.sgaop.basis.annotation.Aspect;
import com.sgaop.basis.annotation.Action;
import com.sgaop.basis.annotation.IocBean;
import com.sgaop.basis.aop.InterceptorProxy;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: 306955302@qq.com
 * Date: 2016/10/9 0009
 * To change this template use File | Settings | File Templates.
 * 结合shiro控制用户访问后台方法
 */
@IocBean
@Aspect(annotation = Action.class, No = 1)
public class ShiroAuthcAspect extends InterceptorProxy {

    private static final Logger logger = Logger.getRootLogger();

    /**
     * 定义一个基于授权功能的注解类数组
     */
    private static final Class[] annotationClassArray = {
            RequiresAuthentication.class, RequiresUser.class, RequiresGuest.class, RequiresRoles.class, RequiresPermissions.class
    };


    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        System.out.println("权限检查");
        Annotation clasAnn = getAnnotation(cls);
        Annotation methodAnn = getAnnotation(method);
        if (clasAnn != null) {
            handleAuth(clasAnn);
            System.out.println("class权限检查");
        }
        if (methodAnn != null) {
            System.out.println("method权限检查");
            handleAuth(methodAnn);
        }
    }

    @SuppressWarnings("unchecked")
    private Annotation getAnnotation(Class<?> cls) {
        // 遍历所有的授权注解
        for (Class<? extends Annotation> annotationClass : annotationClassArray) {
            // 然后判断目标方法上是否带有授权注解
            if (cls.isAnnotationPresent(annotationClass)) {
                return cls.getAnnotation(annotationClass);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private Annotation getAnnotation(Method method) {
        // 遍历所有的授权注解
        for (Class<? extends Annotation> annotationClass : annotationClassArray) {
            // 然后判断目标方法上是否带有授权注解
            if (method.isAnnotationPresent(annotationClass)) {
                return method.getAnnotation(annotationClass);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void handleAuth(Annotation annotation) {
        if (annotation.annotationType().equals(RequiresAuthentication.class)) {
            handleAuthenticated();
        } else if (annotation.annotationType().equals(RequiresUser.class)) {
            handleUser();
        } else if (annotation.annotationType().equals(RequiresGuest.class)) {
            handleGuest();
        } else if (annotation.annotationType().equals(RequiresRoles.class)) {
            handleHasRoles((RequiresRoles) annotation);
        } else if (annotation.annotationType().equals(RequiresPermissions.class)) {
            handleHasPermissions((RequiresPermissions) annotation);
        }
    }

    private void handleAuthenticated() {
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            throw new AuthzException("当前用户尚未认证");
        }
    }

    private void handleUser() {
        Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection principals = currentUser.getPrincipals();
        if (principals == null || principals.isEmpty()) {
            throw new AuthzException("当前用户尚未登录");
        }
    }

    private void handleGuest() {
        Subject currentUser = SecurityUtils.getSubject();
        PrincipalCollection principals = currentUser.getPrincipals();
        if (principals != null && !principals.isEmpty()) {
            throw new AuthzException("当前用户不是访客");
        }
    }

    private void handleHasRoles(RequiresRoles hasRoles) {
        String[] roleName = hasRoles.value();
        Collection<String> roles = new ArrayList<>();
        for (String role : roleName) {
            roles.add(role);
        }
        Subject currentUser = SecurityUtils.getSubject();
        //必须匹配全部角色
        if (!currentUser.hasAllRoles(roles)) {
            throw new AuthzException("当前用户角色不符");
        }
    }

    private void handleHasPermissions(RequiresPermissions hasPermissions) {
        String[] permissionName = hasPermissions.value();
        Subject currentUser = SecurityUtils.getSubject();
        //必须匹配全部权限
        if (!currentUser.isPermittedAll(permissionName)) {
            throw new AuthzException("当前用户权限不符");
        }
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
