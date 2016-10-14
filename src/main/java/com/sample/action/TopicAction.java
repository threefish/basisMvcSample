package com.sample.action;

import com.sgaop.basis.annotation.*;
import com.sgaop.basis.dao.Dao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 30695 on 2016/9/24 0024.
 */
@IocBean
@Control("/topic")
@RequiresUser
public class TopicAction {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private HttpSession session;

    private ServletContext context;

    @Inject("dao")
    private Dao dao;

    @OK("beetl:index")
    @GET
    @Path("/index")
    @Aop({"TestAop"})
    @RequiresPermissions("看帖子")
    public void index(HttpSession session, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getContextPath());
//        UserAccount userAccount= dao.querySinge(UserAccount.class,"id=?",1);
//        System.out.println(new Gson().toJson(userAccount));
    }

    @OK("beetl:index")
    @GET
    @Path("/index2")
    @Aop({"test2aop"})
    @RequiresPermissions("看帖子2")
    public void index2() {
        System.out.println("index2");
    }

    @OK("beetl:index")
    @GET
    @Path("/index3")
    public void index3(HttpSession session, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("index3");
    }
}
