package com.sample.core;

import com.alibaba.druid.pool.DruidDataSource;
import com.sgaop.basis.annotation.Setup;
import com.sgaop.basis.cache.PropertiesManager;
import com.sgaop.basis.dao.DaosRegister;
import com.sgaop.basis.dao.impl.DaoImpl;
import com.sgaop.basis.mvc.view.ViewsRegister;
import com.sgaop.basis.plugin.views.BeetlView;
import com.sgaop.basis.plugin.views.FreeMarkerView;
import com.sgaop.basis.web.WebSetup;

import javax.servlet.ServletContextEvent;
import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 * User: 306955302@qq.com
 * Date: 2016/5/16 0016
 * To change this template use File | Settings | File Templates.
 */
@Setup
public class WebMainSetup implements WebSetup {
    /**
     * 数据源1
     *
     * @return
     */
    private DataSource getDsA() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setPassword(PropertiesManager.getCacheStr("db.password"));
        dataSource.setUsername(PropertiesManager.getCacheStr("db.user"));
        dataSource.setUrl(PropertiesManager.getCacheStr("db.jdbcUrl"));
        dataSource.setMaxActive(PropertiesManager.getIntCache("db.maxActive"));
        dataSource.setDriverClassName(PropertiesManager.getCacheStr("db.driverClassName"));
        dataSource.setValidationQuery(PropertiesManager.getCacheStr("db.validationQuery"));
        dataSource.setValidationQueryTimeout(PropertiesManager.getIntCache("db.validationQueryTimeout"));
        dataSource.setInitialSize(PropertiesManager.getIntCache("db.initialSize"));
        dataSource.setMinIdle(PropertiesManager.getIntCache("db.minIdle"));
        dataSource.setMaxWait(PropertiesManager.getIntCache("db.maxWait"));
        dataSource.setTimeBetweenEvictionRunsMillis(PropertiesManager.getIntCache("db.timeBetweenEvictionRunsMillis"));
        dataSource.setMinEvictableIdleTimeMillis(PropertiesManager.getIntCache("db.minEvictableIdleTimeMillis"));
        dataSource.setTestWhileIdle(PropertiesManager.getBooleanCache("db.testWhileIdle"));
        dataSource.setTestOnBorrow(PropertiesManager.getBooleanCache("db.testOnBorrow"));
        dataSource.setTestOnReturn(PropertiesManager.getBooleanCache("db.testOnReturn"));
        dataSource.setPoolPreparedStatements(PropertiesManager.getBooleanCache("db.poolPreparedStatements"));
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(60 * 60 * 1000);
        return dataSource;
    }

    /**
     * 数据源2
     *
     * @return
     */
    private DataSource getDsB() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setPassword(PropertiesManager.getCacheStr("dbB.password"));
        dataSource.setUsername(PropertiesManager.getCacheStr("dbB.user"));
        dataSource.setUrl(PropertiesManager.getCacheStr("dbB.jdbcUrl"));
        dataSource.setMaxActive(PropertiesManager.getIntCache("dbB.maxActive"));
        dataSource.setDriverClassName(PropertiesManager.getCacheStr("dbB.driverClassName"));
        dataSource.setValidationQuery(PropertiesManager.getCacheStr("dbB.validationQuery"));
        dataSource.setValidationQueryTimeout(PropertiesManager.getIntCache("dbB.validationQueryTimeout"));
        dataSource.setInitialSize(PropertiesManager.getIntCache("dbB.initialSize"));
        dataSource.setMinIdle(PropertiesManager.getIntCache("dbB.minIdle"));
        dataSource.setMaxWait(PropertiesManager.getIntCache("dbB.maxWait"));
        dataSource.setTimeBetweenEvictionRunsMillis(PropertiesManager.getIntCache("dbB.timeBetweenEvictionRunsMillis"));
        dataSource.setMinEvictableIdleTimeMillis(PropertiesManager.getIntCache("dbB.minEvictableIdleTimeMillis"));
        dataSource.setTestWhileIdle(PropertiesManager.getBooleanCache("dbB.testWhileIdle"));
        dataSource.setTestOnBorrow(PropertiesManager.getBooleanCache("dbB.testOnBorrow"));
        dataSource.setTestOnReturn(PropertiesManager.getBooleanCache("dbB.testOnReturn"));
        dataSource.setPoolPreparedStatements(PropertiesManager.getBooleanCache("dbB.poolPreparedStatements"));
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(60*60*1000);
        return dataSource;
    }


    public void init(ServletContextEvent servletContextEvent) {
        ViewsRegister.registerView("freemarker", FreeMarkerView.class);
        ViewsRegister.registerView("beetl", BeetlView.class);
        //注册数据源1
        DaosRegister.registerDao("daoA", DaoImpl.class, getDsA());
        //注册数据源2
        DaosRegister.registerDao("daoB", DaoImpl.class, getDsB());
    }

    public void destroy(ServletContextEvent servletContextEvent) {
        System.out.println("销毁了哦");
    }
}
