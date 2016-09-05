package com.sample.action;

import com.google.gson.Gson;
import com.sample.entity.User;
import com.sgaop.web.frame.server.dao.DBConnPool;
import com.sgaop.web.frame.server.mvc.AjaxResult;
import com.sgaop.web.frame.server.mvc.Mvcs;
import com.sgaop.web.frame.server.mvc.annotation.*;
import com.sgaop.web.frame.server.mvc.upload.TempFile;
import com.sgaop.web.frame.server.mvc.view.ViewsRegister;
import com.sgaop.web.frame.server.util.IoTool;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by IntelliJ IDEA.
 * User: 306955302@qq.com
 * Date: 2016/5/8 0008
 * To change this template use File | Settings | File Templates.
 *
 * @OK("rd:testpage.jsp")//重定向
 * @OK("json")//返回JSON对象
 * @OK("fw:testpage.jsp")//转发
 * @GET//请求方式
 * @Path//默认使用方法名
 *
 * @WebController 标识这是一个可访问的webAction
 *
 */
@WebController("/main")
public class MainAction {


    @OK("jsp:testpage.jsp")
    @GET
    @Path
    public AjaxResult index(
            @Parameter("id") int id,
            @Parameter("name") String name,
            @Parameter("age") int age,
            @Parameter("doubleNum") double doubleNum,
            @Parameter("flag") boolean flag,
            @Parameter("ids") String[] ids,
            HttpServletRequest request) {
        System.out.println("----" + id + "----" + name + "----" + age);
        System.out.println("mian index");
        request.setAttribute("test", "测试request.setAttribute");
        return new AjaxResult(true, "呵呵呵", "json哦");
    }


    @OK("rd:testpage.jsp")
    @GET
    @Path("/testpage")
    public void testpage() {
        System.out.println("---testpage");
    }


    @OK("freemarker:TestFreeMarker.ftl")
    @GET
    @Path("/freemarker")
    public Map freemarkerTest() {
        System.out.println("---freemarkerTest");
        System.out.println(Mvcs.getReq().getRealPath("/ftl/"));
        Map data1 = new HashMap();
        data1.put("name", "张三");
        data1.put("age", 11);
        return data1;
    }

    @OK("json")
    @POST
    @Path("/buildBeanFile")
    public AjaxResult buildBeanFile(@Parameter("data>>") User bean, @Parameter("docName") TempFile docName) {
        System.out.println(new Gson().toJson(bean));
        try {
            if (docName != null) {
                System.out.println(docName.getName());
                IoTool.writeFile(docName.getInputStream(), "d:\\temp\\" + docName.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AjaxResult(true, "呵呵呵", bean);
    }

    @OK("json")
    @POST
    @Path("/buildBeanFiles")
    public AjaxResult buildBeanFiles(@Parameter("data>>") User bean, @Parameter("docName") TempFile[] docName) {
        System.out.println(new Gson().toJson(bean));
        for (TempFile file : docName) {
            System.out.println(file.getName());
            System.out.println(file.getContentType());
        }
        return new AjaxResult(true, "批量文件上传", bean);
    }


    @OK("json")
    @POST
    @Path("/buildBean")
    public AjaxResult buildBean(@Parameter("data>>") User bean) {
        System.out.println(new Gson().toJson(bean));
        Connection connection = DBConnPool.getDbConn();
        System.out.println(connection);
        return new AjaxResult(true, "呵呵呵", bean);
    }


    @OK("file")
    @GET
    @Path("/dowload")
    public File dowloadFile() {
        return new File("D:\\TEMP\\模版说明.docx");
    }
}

