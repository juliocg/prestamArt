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
	    <h2>Registro del usuario</h2>
	    
		<form:form method="POST" action="${pageContext.request.contextPath}/registroUsuario/registrar" modelAttribute="usuario">
		    <div class="dialog">
		    <table>
		        <tr>
		            <td><form:label path="correoElectronico">Correo Electrónico</form:label></td>  
		            <td>
		                <form:input path="correoElectronico" value="${usuario.correoElectronico}" class="form-control" />
		            </td>
		            <td>
			            <form:errors path="correoElectronico" cssClass="alert alert-warning" element="div" />
		            </td>
		        </tr>
		        
		        <tr>
		            <td><form:label path="contrasenia">Contraseña</form:label></td>  
		            <td>
		                <form:password path="contrasenia" value="${usuario.contrasenia}" class="form-control" />
		            </td>
		            <td>
		                <form:errors path="contrasenia" cssClass="alert alert-warning" element="div" />
		            </td>
		        </tr>
		        
		        <tr>
		            <td><form:label path="tipoUsuario">Tipo de Usuario</form:label></td>
		            <td>
		                <form:select multiple="single" path="tipoUsuario.tipoUsuarioId" class="form-control" >
		                <!--form:option selected="true" value="${usuario.tipoUsuario.tipoUsuarioId}" /-->
                        <form:option value="" label="-Elige-" />
                        <form:options items="${tiposUsuario}" itemValue="tipoUsuarioId" itemLabel="nombreTipoUsuario" />
                        </form:select>
		            </td>
		            <td>
		                <form:errors path="tipoUsuario" cssClass="alert alert-warning" element="div" />
		            </td>
		        </tr>
		        
		        <tr>
		            <td><form:label path="nombre">Nombre</form:label></td>  
		            <td>
		                <form:input path="nombre" value="${usuario.nombre}" class="form-control" />
		            </td>
		            <td>
		                <form:errors path="nombre" cssClass="alert alert-warning" element="div" />
			        </td>
		        </tr>
		        
		        <tr>
		            <td><form:label path="apellidos">Apellidos</form:label></td>  
		            <td>
		                <form:input path="apellidos" value="${usuario.apellidos}" class="form-control" />
		            </td>
		            <td>
		                <form:errors path="apellidos" cssClass="alert alert-warning" element="div" />
		            </td>
		        </tr>
		        
		        <tr>
		            <td><form:label path="telefono">Telefono</form:label></td>  
		            <td>
		                <form:input path="telefono" value="${usuario.telefono}" class="form-control" />
		            </td>
		            <td>
		                <form:errors path="telefono" cssClass="alert alert-warning" element="div" />
			        </td>
		        </tr>
		        
		        <tr>
		            <td><form:label path="otroDatoContacto">Otro dato de contacto</form:label></td>  
		            <td>
		                <form:textarea path="otroDatoContacto" value="${usuario.otroDatoContacto}" class="form-control" />
		            </td>
		            <td>
		                <form:errors path="otroDatoContacto" cssClass="alert alert-warning" element="div" />
			        </td>
		        </tr>
		        
		        <tr>
		            <td></td>
		            <td><input type="submit" value="Dar de Alta Usuario" class="btn btn-success" /></td>  
		        </tr>  
            </table>
            </div>
        </form:form>
        </div>
	</body>
</html>
