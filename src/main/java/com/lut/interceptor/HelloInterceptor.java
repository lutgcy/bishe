package com.lut.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.lut.mapper.AdminMapper;
import com.lut.mapper.EmployeeMapper;
import com.lut.mapper.HumanResourceMapper;
import com.lut.utils.JWTUtil;
import com.lut.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloInterceptor implements HandlerInterceptor {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private HumanResourceMapper humanResourceMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        return true;
        boolean getVerifyResult = true;  // get请求
        boolean cookieVerifyResult = false; // 所有请求

        String cookieToken = RequestUtils.getCookieToken(request);
        String secret;
        String username = JWTUtil.getClaimValueByToken(cookieToken, "username");
        String roleType = JWTUtil.getClaimValueByToken(cookieToken, "roleType");
        if (roleType.equals("admin")) {
            secret = adminMapper.getAdminByUserName(username).getPwdHash();
        } else if (roleType.equals("hr")) {
            secret = humanResourceMapper.getHumanResourceByUserName(username).getPwdHash();
        } else if (roleType.equals("employee")) {
            secret = employeeMapper.getLoginInfoFromEmployee(username).getPwdHash();
        } else {
            return false;
        }

        DecodedJWT cookieJwt = null;
        try {
            cookieJwt = JWTUtil.verify(cookieToken, secret);
        } catch (Exception e) {
            response.setStatus(401);
        }
        cookieVerifyResult = cookieJwt == null ? false : true;

        if (request.getMethod().equals("GET")) {
            String getToken = request.getParameter("token");
            if (getToken != null) {
                DecodedJWT getJwt = null;
                try {
                    getJwt = JWTUtil.verify(getToken, secret);
                } catch (Exception e) {
                    response.setStatus(401);
                }
                getVerifyResult = getJwt == null ? false : true;
            } else {
                return false;
            }
        }
        return getVerifyResult && cookieVerifyResult;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
