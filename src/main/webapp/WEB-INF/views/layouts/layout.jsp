<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title></title>
	    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	    <!--tiles:insertAttribute name="title" ignore="true" /-->
	    
	    <!--link href="<c:url value='/resources/jquery-ui-1.10.4.custom/css/base/jquery-ui-1.10.4.custom.css' />" rel="stylesheet">
	    <link href="<c:url value='/resources/jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css' />" rel="stylesheet"-->
	    <link href="<c:url value='/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css' />" rel="stylesheet">
        <link href="<c:url value='/resources/bootstrap-3.3.6-dist/css/bootstrap-theme.min.css' />" rel="stylesheet">
        
        <!--link href="<c:url value='/resources/jquery-ui-1.10.4.custom/css/base/jquery-ui-1.10.4.custom.css' />" rel="stylesheet">
	    <link href="<c:url value='/resources/jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css' />" rel="stylesheet"-->
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value='/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js' />"></script>
        
        <link href="<c:url value='/resources/css/estilo.css' />" rel="stylesheet">
        <!--link href="<c:url value='/resources/css/mobile.css' />" rel="stylesheet"-->
    </head>
    <body style="margin:0">
        <table style="width:100%; border:0" class="container">
	        <tr>
		        <td style="width:100%; background-color:#232f3e" class="page-header">
		            <tiles:insertAttribute name="header" />
		            <c:choose>
                    <c:when test="${usuario != null}">
                    hola
                    </c:when>
                    </c:choose>
                    
                    <sec:authorize access="isAuthenticated()">
                    you already logged in.
                    </sec:authorize>
		        </td>
	        </tr>
	        <tr>
		        <td style="height:20%; width:100%" class="content">
		            <tiles:insertAttribute name="body" />
		        </td>
	        </tr>
	        <tr>
		        <td style="height:10%;" class="footer">
		            <tiles:insertAttribute name="footer" />
		        </td>
	        </tr>
        </table>
    </body>
</html>
