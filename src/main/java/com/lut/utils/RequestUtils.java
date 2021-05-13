package com.lut.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static String getCookieToken(HttpServletRequest request) {
        String cookieToken = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                cookieToken = cookie.getValue();
                break;
            }
        }
        return cookieToken;
    }

}
