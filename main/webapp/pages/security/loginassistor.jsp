<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="pub.platform.advance.utils.MessagePropertyManager" %>
<%@ page import="pub.platform.form.config.SystemAttributeNames" %>
<%@ page import="pub.platform.security.OnLineOpersManager" %>
<%@ page import="pub.platform.security.OperatorManager" %>
<%
    String path = request.getContextPath();
    String loginUsername = request.getParameter("username");
    if (loginUsername == null) {
        out.println("<script language=\"javascript\">if(top){ top.location.href='" + path + "/pages/security/loginPage.jsp'; } else { location.href = '" + path + "/pages/security/loginPage.jsp';} </script>");
        return;
    }

    String password = request.getParameter("password");
    String cookieFlag = request.getParameter("DropExpiration");

    Cookie cookies[] = request.getCookies();
    int cookieMaxAge = 0;
    if ("Month".equalsIgnoreCase(cookieFlag)) {
        cookieMaxAge = 30 * 24 * 60 * 60;
    } else if ("Year".equalsIgnoreCase(cookieFlag)) {
        cookieMaxAge = 365 * 24 * 60 * 60;
    }
    if (!"None".equalsIgnoreCase(cookieFlag)) {
        boolean isFound = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (("usernamecookie").equals(cookie.getName())) {
                        if (!loginUsername.equals(cookie.getValue())) {
                            cookie.setValue(loginUsername);
                            cookie.setPath(request.getContextPath() + "/pages");
                            cookie.setMaxAge(cookieMaxAge);
                            response.addCookie(cookie);
                        }
                        isFound = true;
                }
            }
        }
        if (!isFound) {
            Cookie cookie = new Cookie("usernamecookie", loginUsername);
            cookie.setMaxAge(cookieMaxAge);
            cookie.setPath(request.getContextPath() + "/pages");
            response.addCookie(cookie);
        }
    }
%>

<%
    OperatorManager loginOM = (OperatorManager) session.getAttribute(SystemAttributeNames.USER_INFO_NAME);

    if (loginOM == null) {
        loginOM = new OperatorManager();
        session.setAttribute(SystemAttributeNames.USER_INFO_NAME, loginOM);
    }
    //String imgsign = request.getParameter("imgsign");
    String loginCode = "";
    try {
        //if(!om.ImgSign(imgsign))
        //	out.println("<script language=\"javascript\">alert ('输入校验码有误！'); if(top){ top.location.href='/index.jsp'; } else { location.href = '/index.jsp';} </script>");
        loginOM.setRemoteAddr(request.getRemoteAddr());
        loginOM.setRemoteHost(request.getRemoteHost());
//        isLogin = loginOM.login(loginUsername, password);
        loginCode = loginOM.login(loginUsername.toUpperCase(), password.toUpperCase());
        if (!"T901".equals(loginCode)) {
            String loginErrMsg = MessagePropertyManager.getProperty(loginCode);
            if (loginErrMsg == null) {
                out.println("<script language=\"javascript\">alert ('" + loginCode + "'); if(top){ top.location.href='" + path + "/pages/security/loginPage.jsp'; } else { location.href = '" + path + "/pages/security/loginPage.jsp';} </script>");
            } else
                out.println("<script language=\"javascript\">alert ('" + loginErrMsg + "'); if(top){ top.location.href='" + path + "/pages/security/loginPage.jsp'; } else { location.href = '" + path + "/pages/security/loginPage.jsp';} </script>");
        }else {
            if (!OnLineOpersManager.isHasUserList(application)) {
                OnLineOpersManager.setUserListToServer(application);
                OnLineOpersManager.addOperToServer(session.getId() + loginUsername.toUpperCase(), loginOM, application);
            } else {
                OnLineOpersManager.addOperToServer(session.getId() + loginUsername.toUpperCase(), loginOM, application);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

