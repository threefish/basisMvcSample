package com.sample.core;

import com.sgaop.basis.annotation.Setup;
import com.sgaop.basis.mvc.view.ViewsRegister;
import com.sgaop.basis.plugin.views.BeetlView;
import com.sgaop.basis.plugin.views.FreeMarkerView;
import com.sgaop.basis.web.WebSetup;

import javax.servlet.ServletContextEvent;

/**
 * Created by IntelliJ IDEA.
 * User: 306955302@qq.com
 * Date: 2016/5/16 0016
 * To change this template use File | Settings | File Templates.
 */
@Setup
public class WebMainSetup implements WebSetup {

    public void init(ServletContextEvent servletContextEvent) {
        ViewsRegister.registerView("freemarker", FreeMarkerView.class);
        ViewsRegister.registerView("beetl", BeetlView.class);
    }

    public void destroy(ServletContextEvent servletContextEvent) {
        System.out.println("销毁了哦");
    }
}
