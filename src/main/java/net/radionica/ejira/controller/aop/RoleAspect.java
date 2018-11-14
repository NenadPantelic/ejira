package net.radionica.ejira.controller.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import net.radionica.ejira.controller.interceptor.LogInterceptor;
import net.radionica.ejira.exception.UnauthorizedException;
import net.radionica.ejira.model.Role;

@Aspect
@Component
public class RoleAspect {
    private final String message = "You are not authorized to access this URL!";
    //@Pointcut(value="@annotation(net.radionica.ejira.controller.aop.UserRole)",argNames="userRole")
    //@Pointcut("@annotation(userRole)")
   // public void projectPMMethods() {};
    // @Around("@annotation(userRole)")
//    public boolean authorizationCheck(ProceedingJoinPoint joinPoint, UserRole userRole) throws Throwable {
//	if (!LogInterceptor.context.get().getRole().equals(userRole.name())) {
//	    throw new UnauthorizedException(message);
//	}
//
////        Signature signature =  joinPoint.getSignature();
////        Class returnType = ((MethodSignature) signature).getReturnType();
////        return (Class) joinPoint;
//	joinPoint.proceed();
//	return true;
//    }

    @Before(value="@annotation(userRole)")
    public void authCheck(JoinPoint joinPoint, UserRole userRole) {
	if (!LogInterceptor.context.get().getRole().equals(userRole.name())) {
	    throw new UnauthorizedException(message);
	}

    }

}
