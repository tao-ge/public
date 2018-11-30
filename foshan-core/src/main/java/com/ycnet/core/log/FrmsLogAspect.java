package com.ycnet.core.log;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ycnet.frms.bean.Logs;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.LogsService;

@Aspect
@Repository
public class FrmsLogAspect {

	@Resource
	private LogsService logsService;
	
	
	@Pointcut("@annotation(com.ycnet.core.log.FrmsLog)")
	public void record(){
	}
	
	@AfterReturning("record()")
	public void afterRound(JoinPoint joinPoint){
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		Users u = null;
		Logs frmsLog = new Logs();
		
		frmsLog.setIp(request.getRemoteAddr());
		frmsLog.setOprTime(new Date());
//		frmsLog.setUserName(new String());;
//		frmsLog.setLogContent(new String());
		try{
			Object target = joinPoint.getTarget();
			String methodName = joinPoint.getSignature().getName();
			Class<?>[] arguments = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
			Method m = target.getClass().getDeclaredMethod(methodName, arguments);
			frmsLog.setLogContent(m.getAnnotation(FrmsLog.class).value());
			if(target.getClass().getName().contains(".mobile.")){
				frmsLog.setLogCode("mobile");
				frmsLog.setLogContent(frmsLog.getLogContent()+"(手机登录)");//fl修改  移动端登录
			}else
			{
				frmsLog.setLogCode("pc");
				frmsLog.setLogContent(frmsLog.getLogContent()+"(电脑登录)");//fl修改  PC端登录
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if( (u=(Users)session.getAttribute("users"))!=null||(u=(Users)session.getAttribute("platformUser"))!=null)
		{
//			u=(Users)session.getAttribute("platformUser");
			frmsLog.setUserId(u.getUserId());
			frmsLog.setUserName(u.getUserName());
			frmsLog.setOrgId(u.getOrgId());
		}else{
			frmsLog.setLogContent(frmsLog.getLogContent() +"(未知用户)");
		}
		
		logsService.addLogs(frmsLog);
	}
}
