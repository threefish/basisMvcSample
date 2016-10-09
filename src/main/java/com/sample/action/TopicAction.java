package com.sample.action;

import com.google.gson.Gson;
import com.sample.entity.UserAccount;
import com.sgaop.basis.annotation.*;
import com.sgaop.basis.dao.Dao;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 30695 on 2016/9/24 0024.
 */
@IocBean("topicAction")
@Control("/topic")
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
    public void index(HttpSession session, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request);
        System.out.println(response);
        UserAccount userAccount= dao.querySinge(UserAccount.class,"id=?",1);
        System.out.println(new Gson().toJson(userAccount));
    }
}
