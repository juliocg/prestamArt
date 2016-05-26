<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Insert title here</title>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    </head>
    <body>
        <div class="body">
        <h2>Ingreso al sistema</h2>
        
        <c:choose>
        <c:when test="${not empty error}">
        <div class="alert alert-warning">
        ${error}<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        </div>
        </c:when>
        <c:when test="${not empty mensaje}">
        <div class="alert alert-success">
        ${mensaje}<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        </div>
        </c:when>
        </c:choose>
        
        <!--form:form method="POST" action="${pageContext.request.contextPath}/ingresoAlSistema/ingresarAlSistema" commandName="ingresoAlSistemaForm"-->
        <!--form:form method="POST" action="<c:url value='j_spring_security_check' />" commandName="ingresoAlSistemaForm"-->
        <form method="POST" action="<c:url value='/j_spring_security_check' />"> 
            <div class="dialog">
		    <table>
		        <tr>
		            <td>
		                <label>Correo Electrónico</label>
		            </td>  
		            <td>
		                <input type="text" name="correoElectronico" value="" />
		            </td>  
		        </tr>
		        
		        <tr>
		            <td>
		                <label>Contrase&ntilde;a</label>
		            </td>  
		            <td>
		                <input type="password" name="contrasenia" value="" />
		            </td>  
		        </tr>
		        
		        <tr>
		            <td></td>
		            <td><input type="submit" value="Ingresar al sistema" class="btn btn-success" /></td>  
		        </tr>
		    </table>
		    </div>
		</form>
        <!--/form:form-->
        </div>
    </body>
</html>