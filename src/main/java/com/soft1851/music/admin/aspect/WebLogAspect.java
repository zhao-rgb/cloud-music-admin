package com.soft1851.music.admin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhao
 * @className WebLogAspect
 * @Description TODO
 * @Date 2020/4/30
 * @Version 1.0
 **/
@Aspect
@Component
@Order(100)
@Slf4j
public class WebLogAspect {
//    /**
//     *threadLocal可以让我们拥有当前线程的变量，可以通过set()和get()来对这个局部变量进行操作
//     * 不会和其他线程的局部变量进行冲突，实现了线程的数据隔离
//     * 此处用它进行日志信息在各个方法之间的共享
//     */
//    private ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal<>();
//    /**
//     * 定义切点,课后思考如何切的力度更精准
//     */
//    @Pointcut("execution(public * com.soft1851.music.admin.controller..*.*(..))")
//    public void webLog(){
//    }
//
//    @Before(value = "webLog() && @annotation(controllerWebLog)")
//    public void doBefore(JoinPoint joinPoint, ControllerWebLog controllerWebLog){
//        //接受请求
//        RequestAttributes at = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) at;
//        assert sra != null;
//        HttpServletRequest request = sra.getRequest();
//        log.info("请求URI：" + request.getRequestURI());
//        log.info("请求URL：" + request.getRequestURL());
//        log.info("请求头的User-Agent：" + request.getHeader("User-Agent"));
//        log.info("请求方法：" + request.getMethod());
//        log.info("请求地址：" + request.getRemoteAddr());
//        log.info("连接点对象通过反射获得类名和方法名：" + joinPoint.getSignature().getDeclaringTypeName() + "."
//                + joinPoint.getSignature().getName());
//        log.info("AOP拦截获得参数：" + Arrays.toString(joinPoint.getArgs()));
//        log.info("执行方法：" + controllerWebLog.name());
//        //定义一个map用来记录日志信息，并将其put入threadLocal
//        Map<String,Object> map = new HashMap<>(16);
//        map.put("uri",request.getRequestURI());
//        map.put("url",request.getRequestURL());
//        map.put("user-Agent",request.getHeader("user-Agent"));
//        map.put("request-method",request.getMethod());
//        map.put("remote-address",request.getRemoteAddr());
//        map.put("class-method",joinPoint.getSignature().getDeclaringTypeName() + "."
//                + joinPoint.getSignature().getName());
//        map.put("arguments",Arrays.toString(joinPoint.getArgs()));
//        map.put("execute-method",controllerWebLog.name());
//        threadLocal.set(map);
//    }
//
//    /**
//     * 执行成功后处理
//     * @param controllerWebLog
//     * @param ret
//     * @throws Throwable
//     */
//    @AfterReturning(value = "webLog() && @annotation(controllerWebLog)",returning = "ret")
//    public void doAfterReturning(ControllerWebLog controllerWebLog, Object ret) throws Throwable{
//        //从当前线程变量取出数据
//        Map<String,Object> threadInfo = threadLocal.get();
//        //将请求的目标方法getHello()的执行的返回结果存入线程对象
//        threadInfo.put("result",ret);
//        if (controllerWebLog.isSaved()){
//            //真实场景可做定时插入数据库操作，此处仅模拟
//            log.info("日志已存入数据库");
//        }
//        //处理完请求。返回内容
//        log.info("RESPONSE" + ret);
//    }
//
//    /**
//     * 通过环绕增强获取目标方法执行时间，可用于分析性能
//     * 可通过连接点入参和反射机制，在这里调用目标方法getHello(),并返回调用结果
//     * @param proceedingJoinPoint
//     * @return
//     * @throws Throwable
//     */
//    @Around(value = "webLog()")
//    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
//        //得到开始时间
//        long startTime = System.currentTimeMillis();
//        //执行连接点的目标方法getHello()
//        Object ob = proceedingJoinPoint.proceed();
//        Map<String, Object> threadInfo = threadLocal.get();
//        //计算出方法的真实执行时间，可以在目标方法中加入线程休眠体会结果
//        long takeTime = System.currentTimeMillis() - startTime;
//        //存入线程变量
//        threadInfo.put("takeTime",takeTime);
//        log.info("耗时：" + takeTime);
//        threadLocal.set(threadInfo);
//        return ob;
//    }
//
//    /**
//     * 异常处理
//     * @param throwable
//     */
//    @AfterThrowing(value = "webLog()", throwing = "throwable")
//    public void doAfterThrowing(Throwable throwable){
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        assert sra != null;
//        HttpServletRequest request = sra.getRequest();
//        //异常信息
//        log.error("{}接口调用异常，异常信息{}",request.getRequestURI(),throwable);
//    }
}
