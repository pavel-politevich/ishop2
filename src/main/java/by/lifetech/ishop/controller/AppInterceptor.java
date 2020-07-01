package by.lifetech.ishop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AppInterceptor implements HandlerInterceptor {

	private static final String RESOURCES_FOLDER = "resources";
	private static final String LAST_REQUEST_PARAM = "lastRequest";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

		if ("GET".equalsIgnoreCase(request.getMethod()) && !request.getServletPath().contains(RESOURCES_FOLDER)) {

			if (request.getQueryString() != null) {
				request.getSession(true).setAttribute(LAST_REQUEST_PARAM,
						request.getServletPath() + "?" + request.getQueryString());

			} else {
				request.getSession(true).setAttribute(LAST_REQUEST_PARAM, request.getServletPath());

			}
		}

	}

}
