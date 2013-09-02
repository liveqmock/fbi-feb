<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="pub.platform.security.OperatorManager" %>
<%@ page import="pub.platform.form.config.SystemAttributeNames" %>
<%@page import="java.util.Enumeration" %>

<html>
<head>
    <title>Logout</title>
</head>
<body bgcolor="#cccccc" link="#3366cc" vlink="#9999cc" alink="#0000cc">
<table border=0 cellspacing="18" cellpadding="0">
    <tr>
        <td valign="top">
            <%
                String path = request.getContextPath();
                OperatorManager om = (OperatorManager) session.getAttribute(SystemAttributeNames.USER_INFO_NAME);
                if (om != null) {
                    om.logout();
                }
                Enumeration p_enum = session.getAttributeNames();
                while (p_enum.hasMoreElements()) {
                    String removeStr = (String) p_enum.nextElement();
                    //System.out.println("-------:\n  "+removeStr);
                    session.removeAttribute(removeStr);
                }
                String isClose = request.getParameter("isclose");
                if (isClose == null || isClose.trim().equals("")) {
                    out.println("<script language=\"javascript\">alert ('«©ÕÀ≥…π¶£°'); if(top){ top.location.href='" + path + "/pages/security/loginPage.jsp'; } else { location.href = '" + path + "/pages/security/loginPage.jsp';} </script>");
                } else {
                    out.println("<script>alert ('«©ÕÀ ß∞‹');</script>");
                    out.println("<script>window.close();</script>");
                }
            %>
        </td>
    </tr>
</table>
</body>
</html>
