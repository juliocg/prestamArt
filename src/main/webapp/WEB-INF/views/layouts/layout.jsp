<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title></title>
	    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	    <!--tiles:insertAttribute name="title" ignore="true" /-->
	    <link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
        <link rel="icon" href="<c:url value='/resources/images/favicon.ico' />" type="image/x-icon">
	    
	    <link href="<c:url value='/resources/jquery-ui-1.10.4.custom/css/base/jquery-ui-1.10.4.custom.css' />" rel="stylesheet">
	    <link href="<c:url value='/resources/jquery-ui-1.10.4.custom/development-bundle/themes/base/jquery.ui.all.css' />" rel="stylesheet">
	    <link href="<c:url value='/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css' />" rel="stylesheet">
        <link href="<c:url value='/resources/bootstrap-3.3.6-dist/css/bootstrap-theme.min.css' />" rel="stylesheet">
        
        <script src="<c:url value='/resources/jquery-ui-1.10.4.custom/js/jquery-1.10.2.js' />"></script>
	    <script src="<c:url value='/resources/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js' />"></script>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <!--script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script-->
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="<c:url value='/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js' />"></script>
        
        <link href="<c:url value='/resources/css/estilo.css' />" rel="stylesheet">
        <!--link href="<c:url value='/resources/css/mobile.css' />" rel="stylesheet"-->
        
        <script type="text/javascript">
        $(function() {
        	if (!$('div.alert.alert-warning a.close').exist()) {
        	   $('div.alert.alert-warning').append('<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>');
        	}
        });
        </script>
    </head>
    <body style="margin:0">
        <table style="width:100%; border:0" class="container">
	        <tr>
		        <td style="width:100%; background-color:#232f3e" class="page-header">
		            <tiles:insertAttribute name="header" />
		        </td>
	        </tr>
	        <tr>
		        <td>
		            <div>
			            <!--nav style="width:50%; float:left">
			            </nav-->
			            <nav class="navbar navbar-default" style="min-height:40px; height:40px; margin-bottom:0px">
			                <div class="container-fluid" style="height:40px;">
			                <div></div>
			                <div class="collapse navbar-collapse" style="height:40px;">
			                    <sec:authorize var="loggedIn" access="isAuthenticated()" />
			                    <c:choose>
                                <c:when test="${loggedIn}">
			                    <!--sec:authorize access="isAuthenticated()"-->
			                    <ul class="nav navbar-nav navbar-right" style="height:40px;">
			                    <li style="height:40px">
			                    <a href="<c:url value='/usuario' />" style="padding-top:8px">Administrar usuario</a>
			                    </li>
		                        <li style="height:40px">
		                        <a href="<c:url value='/salidaDelSistema' />" style="padding-top:8px">Salir del sistema</a>
		                        </li>
		                        </ul>
		                        <!--/sec:authorize-->
		                        </c:when>
		                        <c:otherwise>
		                        <ul class="nav navbar-nav navbar-right" style="height:40px;">
			                    <li style="height:40px">
		                        <a href="<c:url value='/ingresoAlSistema' />" style="padding-top:8px">Ingreso al sistema</a>
		                        </li>
		                        </ul>
		                        </c:otherwise>
		                        </c:choose>
		                    </div>
		                    </div>
			            </nav>
		            </div>
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
