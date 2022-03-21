package com.example.spring_test.aspects.concrete;

import java.lang.reflect.Method;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.example.spring_test.Utils.JWTToken;
import com.example.spring_test.aspects.annotation.SecuredMethod;
import com.example.spring_test.exceptions.NotAuthorizedException;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class SecuredMethodAspect {

    private JWTToken jwtToken;

    @Autowired
    public SecuredMethodAspect(JWTToken jwtToken) {
        this.jwtToken = jwtToken;
    }


    @Around("@annotation(com.example.spring_test.aspects.annotation.SecuredMethod)")
    public Object initializeSecuredOperation(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        SecuredMethod securedMethod = method.getAnnotation(SecuredMethod.class);
        List<String> acceptedRoles = new ArrayList<>(Arrays.asList(securedMethod.role().trim().toLowerCase(Locale.ENGLISH).split(",")));

        String token = getTokenFromHeader();
        if(Objects.isNull(token)) {
            throw new IllegalStateException("Token not exists in the header");
        }

        Claims claims;
        String userRoleName;
        try {
            claims = jwtToken.extractAllClaims(token);
            userRoleName = (String) ((HashMap) claims.get("role")).get("name");
            System.out.println("User claims " + claims.toString());
        }
        catch (Exception e) {
            throw new NotAuthorizedException();
        }

        if(acceptedRoles.size() == 1 && acceptedRoles.get(0).trim().equals("")) {
            return joinPoint.proceed();
        }

        if(!acceptedRoles.contains(userRoleName.toLowerCase(Locale.ENGLISH))) {
            throw new NotAuthorizedException();
        }

        return joinPoint.proceed();
    }




    public String getTokenFromHeader() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(servletRequestAttributes)) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            String header = request.getHeader("Authorization");
            return headerValid(header);
        }
        return null;
    }

    private String headerValid(String header) {
        try {
            String[] tokenArr = header.split("\\s+");
            if (tokenArr.length == 2 && tokenArr[0].equals("Bearer"))
                return tokenArr[1];
        } catch (Exception e) {
            throw new IllegalStateException("Token form illegal");
        }
        return null;
    }


}
