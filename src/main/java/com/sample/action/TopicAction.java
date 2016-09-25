package com.sample.action;

import com.google.gson.Gson;
import com.sample.entity.UserAccount;
import com.sgaop.web.frame.server.dao.Dao;
import com.sgaop.web.frame.server.ioc.annotation.Inject;
import com.sgaop.web.frame.server.ioc.annotation.IocBean;
import com.sgaop.web.frame.server.mvc.annotation.GET;
import com.sgaop.web.frame.server.mvc.annotation.OK;
import com.sgaop.web.frame.server.mvc.annotation.Path;
import com.sgaop.web.frame.server.mvc.annotation.WebController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 30695 on 2016/9/24 0024.
 */
@IocBean("topicAction")
@WebController("/topic")
public class TopicAction {

    private HttpServletRequest request;

    private HttpServletResponse response;

    @Inject("dao")
    private Dao dao;

    @OK("beetl:index")
    @GET
    @Path("/index")
    public void index() {
        System.out.println(request);
        System.out.println(response);
        UserAccount userAccount= dao.querySinge(UserAccount.class,"id=?",1);
        System.out.println(new Gson().toJson(userAccount));
    }
}
