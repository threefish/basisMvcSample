package com.sample.view;

import com.sgaop.web.frame.server.mvc.Mvcs;
import com.sgaop.web.frame.server.mvc.view.View;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.WebAppResourceLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: 306955302@qq.com
 * Date: 2016/9/13 0013
 * To change this template use File | Settings | File Templates.
 * 自定义的Beetl视图
 */
public class BeetlView implements View {

    private static GroupTemplate gt = null;

    /**
     * 默认格式
     */
    private final static String _suffix = ".html";

    static {
        try {
            WebAppResourceLoader resourceLoader = new WebAppResourceLoader();
            Configuration cfg = Configuration.defaultConfiguration();
            resourceLoader.setCharset("utf-8");
            resourceLoader.setRoot(Mvcs.getSession().getServletContext().getRealPath("/_view_/btl/"));
            gt = new GroupTemplate(resourceLoader, cfg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(String path, HttpServletRequest request, HttpServletResponse response, Object data) {
        try {
            Template tpl = gt.getTemplate(path + _suffix);
            if (data instanceof Map) {
                tpl.binding("data", data, false);
            } else {
                tpl.binding("data", data, true);
            }
            tpl.binding("base", request.getContextPath());
            OutputStream out = response.getOutputStream();
            tpl.renderTo(response.getOutputStream());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
