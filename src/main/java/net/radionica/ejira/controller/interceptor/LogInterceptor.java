package net.radionica.ejira.controller.interceptor;

import static net.radionica.ejira.util.SecurityUtil.CACHE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import net.radionica.ejira.exception.InvalidCredentialsException;
import net.radionica.ejira.model.Role;
import net.radionica.ejira.service.UserService;

public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService _userService;

    public static final ThreadLocal<RequestContext> context = new ThreadLocal<>();
    private static final String USER_TOKEN = "token";

    Logger _log = org.slf4j.LoggerFactory.getLogger(this.getClass());

    public UserService getUserService() {
	return _userService;
    }

    public void setUserService(UserService userService) {
	_userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
	String authToken = (String) request.getHeader(USER_TOKEN);
	_log.info(getClientIp(request));
	if (authToken == null || CACHE.getIfPresent(authToken) == null) {
	    throw new InvalidCredentialsException("Invalid login credentials!");
	} else {
	    context.set(new RequestContext());
	    context.get().setUserId(CACHE.getIfPresent(authToken));

	    context.get().setToken(authToken);
	    Role role = getUserService().getUserRole(context.get().getUserId());
	    context.get().setRole(role);
	}
	return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
	    throws Exception {
	context.remove();
    }

    private static String getClientIp(HttpServletRequest request) {

	String remoteAddr = "";

	if (request != null) {
	    remoteAddr = request.getHeader("X-FORWARDED-FOR");
	    if (remoteAddr == null || "".equals(remoteAddr)) {
		remoteAddr = request.getRemoteAddr();
	    }
	}

	return remoteAddr;
    }

}