package com.jdpin.api.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class JDPINExecuteTimeInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger
			.getLogger(JDPINExecuteTimeInterceptor.class);

	// before the actual handler will be executed
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		synchronized (JDPINExecuteTimeInterceptor.class) {
			UUID uuid = UUID.randomUUID();
			request.setAttribute("uuid", uuid);
			// Replacing PREFIX in the log file to identify each request
			// uniquely
			MDC.put("PREFIX", uuid);

			//Logging request time details
			long startTime = System.currentTimeMillis();
			// log it
			// if (logger.isDebugEnabled()) {
			logger.debug("[" + handler + "] startTime : " + startTime + "ms");
			// }

			request.setAttribute("startTime", startTime);
		}

		return true;
	}

	// after the handler is executed
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		/*
		 * Replacing PREFIX in the log file to identify each request uniquely
		 * once the request processing completed
		 */
		MDC.put("PREFIX", request.getAttribute("uuid"));
		
		//Logging request time details
		long startTime = (Long) request.getAttribute("startTime");

		long endTime = System.currentTimeMillis();

		long executeTime = endTime - startTime;

		// log it
		// if (logger.isDebugEnabled()) {
		logger.debug("[" + handler + "] executeTime : " + executeTime + "ms");
		// }
	}
}