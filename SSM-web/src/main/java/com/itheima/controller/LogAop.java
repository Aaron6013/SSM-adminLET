package com.itheima.controller;

import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Aaron
 * @date 2020/7/26 - 12:07
 */
@Component("logAop")
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date visitTime; //执行时间
    private Class clazz; //执行的类
    private Method method; //执行的方法

    @Pointcut("execution(* com.itheima.controller.*.*(..))")
    private void pt(){}

    /**
     * 前置通知：获取执行的时间，执行的是哪一个类以及哪一个方法
     */
    @Before("pt()")
    public void doBefore(JoinPoint joinPoint){
        visitTime = new Date();
        clazz = joinPoint.getTarget().getClass();

        String methodName = joinPoint.getSignature().getName(); //获取执行方法的名字
        Object[] args= joinPoint.getArgs(); //执行方法的参数
        //获取method对象
        if(args == null || args.length == 0){
            try {
                method = clazz.getMethod(methodName);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }else {
            Class[] classes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classes[i] = args[i].getClass();
            }
            try {
                method = clazz.getMethod(methodName,classes);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 后置通知
     */
    @After("pt()")
    public void doAfter(){

        //获取访问时长
        long time = new Date().getTime() - visitTime.getTime();

        //获取url(获取类上和方法上的@RequestMapping的值)
        String url = null;
        if(clazz != null && method != null && clazz != LogAop.class && clazz != SysLogController.class){

            //1、获取类上
            RequestMapping clazzAnnotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            if(clazzAnnotation != null){
                String[] classValue = clazzAnnotation.value();
                //2、获取方法上
                RequestMapping methodAnnotation = (RequestMapping)method.getAnnotation(RequestMapping.class);
                if(methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];

                    //获取访问的ip地址（通过Request对象）
                    String ip = request.getRemoteAddr();

                    //获取当前操作用户
                    SecurityContext context = SecurityContextHolder.getContext(); //获取当前登录用户
                    User user = (User)context.getAuthentication().getPrincipal();
                    String username = user.getUsername();


                    //将相关信息封装到SysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setVisitTime(visitTime);
                    sysLog.setExecutionTime(time);
                    sysLog.setUrl(url);
                    sysLog.setIp(ip);
                    sysLog.setUsername(username);
                    sysLog.setMethod("类名：" + clazz.getName() + " 方法名：" +method.getName());

                    //调用service完成记录日志操作
                    sysLogService.saveLog(sysLog);
                }
            }
        }
    }
}
