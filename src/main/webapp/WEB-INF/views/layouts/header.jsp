<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Cabecera</title>
    </head>
    <body>
	    <div style="color:#fff; width:100%; height:100%; display:flex; align-items:center;">
	        <div class="nav-left" style="float:left; width:50%" >
	            <h1 style="margin-top:10px">
	            <a href="index" style="color:#fff"><img src="<c:url value='/resources/images/icono.png' />" style="height:80px" />PrestamArt</a>
	            </h1>
	        </div>
	        
	        <div class="nav-right" style="float:right; width:50%;">
		        <form:form method="POST" action="">
		        <div class="col-lg-6">
                <div class="input-group">
	            <input type="text" name="campo" value="" class="form-control" placeholder="Buscar..." />
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button">Go!</button>
                </span>
                </div>
                </div>
	            </form:form>
	        </div>
	    </div>
    </body>
</html>
