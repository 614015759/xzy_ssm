package com.atxzy.ssm.web.controller;

import com.atxzy.ssm.domain.SysLog;
import com.atxzy.ssm.service.ISystemLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISystemLogService systemLogService;

    private Date visitTime; //访问的开始时间
    private Class aclass;//访问的类
    private Method method;//访问的方法

    @Before("execution(* com.atxzy.ssm.web.controller.*.*(..))")  //controller目录下所有的方法全都拦截了
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//访问时间
        aclass = jp.getTarget().getClass();//具体要访问的类
        String methodName = jp.getSignature().getName();//获取访问方法的名称
        Object[] args = jp.getArgs();//获取访问的方法的参数

        //获取到了具体执行的方法的method对象
        if (args == null || args.length == 0) {
            method = aclass.getMethod(methodName);//只能获取无参数的方法
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = aclass.getMethod(methodName, classArgs);
        }


    }

    @After("execution(* com.atxzy.ssm.web.controller.*.*(..))")  //controller目录下所有的方法全都拦截了
    public void doAfter(JoinPoint jp) {

        long time = System.currentTimeMillis() - visitTime.getTime();
        String url = "";
        //获取url
        if (aclass != null && method != null && aclass != LogAop.class) {
            //获取类上的requestMapping注解的值
            RequestMapping aclassAnnotation = (RequestMapping) aclass.getAnnotation(RequestMapping.class);
            if (aclassAnnotation != null) {
                String aclassValue = aclassAnnotation.value()[0];
                //获取方法上的RequestMapping的值
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String methodValue = methodAnnotation.value()[0];
                    url = aclassValue + methodValue;
                    String ip = request.getRemoteAddr();

                    //获取当前操作用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登录了的用户

                    User user = (User) context.getAuthentication().getPrincipal();
                    String userName = user.getUsername();

                    //将日志相关信息封装到SysLog对象里
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名]"+aclass.getName()+"  [方法名]"+method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(userName);
                    sysLog.setVisitTime(visitTime);


                    //调用Service完成数据库的insert

                    systemLogService.saveLog(sysLog);
                }
            }
        }

    }

}
