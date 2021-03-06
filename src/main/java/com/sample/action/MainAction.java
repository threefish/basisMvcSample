package com.sample.action;

import com.sample.entity.Topic;
import com.sgaop.basis.annotation.*;
import com.sgaop.basis.dao.Dao;
import com.sgaop.basis.trans.TransAop;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


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
 * @Control 标识这是一个可访问的webAction
 */
@IocBean("MainAction")
@Action("/main")
public class MainAction {

    @Inject("daoA")
    private Dao daoA;

    @Inject("daoB")
    private Dao daoB;

    @Inject("java:db.jdbcUrl")
    private String jdbcUrl;

    @Inject("java:db.password")
    private int password;

    @Inject("java:db.testOnBorrow")
    private boolean testOnBorrow;

    //    @OK("beetl:index")
    @GET
    @Path("/index")
    @Aop({TransAop.READ_COMMITTED})
    public String index(HttpServletRequest request) throws SQLException {
        try {
            Topic tp = new Topic();
            tp.setContent(Thread.currentThread().getName());
            daoA.insert(tp);

            Topic tp2 = new Topic();
            tp2.setId(2);
            tp2.setContent("我了个艹a+");
            daoB.insert(tp2);
//            throw new RuntimeException("我是故意的");

        } catch (Exception e) {
            throw e;
        }

        return "beetl:index";
    }

    @OK("beetl:index")
    @GET
    @Path("/index2")
    public void index2(HttpServletRequest request) {
        Topic tp = new Topic();
        tp.setContent(Thread.currentThread().getName());
        try {
//            tp.setId(daoA.insert(tp));
//            daoA.delect(tp);
            daoA.insert(tp);
            daoB.insert(tp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("index2");

    }

//    @OK("jsp:testpage.jsp")
//    @GET
//    @Path("/testindex")
//    public AjaxResult index(
//            @Parameter("id") int id,
//            @Parameter("name") String name,
//            @Parameter("age") int age,
//            @Parameter("doubleNum") double doubleNum,
//            @Parameter("flag") boolean flag,
//            @Parameter("ids") String[] ids,
//            HttpServletRequest request) {
//        System.out.println("----" + id + "----" + name + "----" + age);
//        System.out.println("mian index");
//        request.setAttribute("test", "测试request.setAttribute");
//        return new AjaxResult(true, "呵呵呵", "json哦");
//    }
//
//
//    @OK("rd:testpage.jsp")
//    @GET
//    @Path("/testpage")
//    public void testpage() {
//        System.out.println("---testpage");
//    }
//
//
//    @OK("freemarker:TestFreeMarker")
//    @GET
//    @Path("/freemarker")
//    public Map freemarkerTest() {
//        System.out.println("---freemarkerTest");
//        Map data1 = new HashMap();
//        data1.put("name", "张三");
//        data1.put("age", 11);
//        return data1;
//    }
//
//    @OK("beetl:TestBeetl")
//    @GET
//    @Path("/beetl")
//    public Map beetlTest() {
//        System.out.println("---beetlTest");
//        Map data1 = new HashMap();
//        data1.put("name", "张三");
//        data1.put("age", 11);
//        List<Map> datalist=new ArrayList<Map>();
//        for(int i=1;i<=9;i++){
//            Map temp = new HashMap();
//            temp.put("name", "张"+i);
//            temp.put("age", (Math.random() * 100));
//            datalist.add(temp);
//        }
//        data1.put("data", datalist);
//        return data1;
//    }
//
//    @OK("beetl:TestBeetl2")
//    @GET
//    @Path("/beetl2")
//    public User beetl2() {
//        System.out.println("---beetlTest2");
//        User user = new User();
//        user.setAge(18);
//        user.setName("李四");
//        return user;
//    }
//
//    @OK("beetl:TestBeetl2")
//    @GET
//    @Path("/beetl3")
//    public Map beetl3() {
//        System.out.println("---beetlTest3");
//        Map data1 = new HashMap();
//        data1.put("name", "张三");
//        data1.put("age", 11);
//        return data1;
//    }
//
//
//    @OK("json")
//    @POST
//    @Path("/buildBeanFile")
//    public AjaxResult buildBeanFile(@Parameter("data>>") User bean, @Parameter("docName") TempFile docName) {
//        System.out.println(new Gson().toJson(bean));
//        try {
//            if (docName != null) {
//                System.out.println(docName.getName());
//                IoTool.writeFile(docName.getInputStream(), "d:\\temp\\" + docName.getName());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new AjaxResult(true, "呵呵呵", bean);
//    }
//
//    @OK("json")
//    @POST
//    @Path("/buildBeanFiles")
//    public AjaxResult buildBeanFiles(@Parameter("data>>") User bean, @Parameter("docName") TempFile[] docName) {
//        System.out.println(new Gson().toJson(bean));
//        for (TempFile file : docName) {
//            System.out.println(file.getName());
//            System.out.println(file.getContentType());
//        }
//        return new AjaxResult(true, "批量文件上传", bean);
//    }
//
//
//    @OK("json")
//    @POST
//    @Path("/buildBean")
//    public AjaxResult buildBean(@Parameter("data>>") User bean) throws SQLException {
//        System.out.println(new Gson().toJson(bean));
//        Connection connection = DBConnPool.getDataSource().getConnection();
//        System.out.println(connection);
//        return new AjaxResult(true, "呵呵呵", bean);
//    }
//
//
//    @OK("file")
//    @GET
//    @Path("/dowload")
//    public File dowloadFile() {
//        return new File("D:\\TEMP\\模版说明.docx");
//    }
}

