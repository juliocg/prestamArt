<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	</head>
	<body>
	    <div class="body">
	    <h2>Consulta de usuario</h2>
        
        <div class="dialog">
	    <table class="table" style="width:auto">
		    <tr>
			    <th>Correo Electronico</th>
			    <td>${usuario.correoElectronico}</td>
		    </tr>
		    
		    <tr>
			    <th>Tipo de usuario</th>
			    <td>${usuario.tipoUsuario.nombreTipoUsuario}</td>
		    </tr>
		    
		    <tr>
			    <th>Nombre</th>
			    <td>${usuario.nombre}</td>
		    </tr>
		    
		    <tr>
			    <th>Apellidos</th>
			    <td>${usuario.apellidos}</td>
		    </tr>
		    
		    <tr>
			    <th>Telefono</th>
			    <td>${usuario.telefono}</td>
		    </tr>
		    
		    <tr>
			    <th>Otro(s) dato(s) de contacto</th>
			    <td>${usuario.otroDatoContacto}</td>
		    </tr>
		    
		    <tr>
			    <td colspan="2">
			        <div style="float:left; margin-right:15px">
			        <a href="<c:url value='/usuario/cambios/${usuario.usuarioId}' />" class="btn btn-success">Cambiar datos de usuario</a>
			        </div>
			        <div style="float:left; margin-left:15px">
			        <form:form method="POST" action="${pageContext.request.contextPath}/usuario/realizarBaja">
			        <input type="hidden" id="usuarioId" name="usuarioId" value="${usuario.usuarioId}" readonly="true" />
			        <input type="submit" value="Dar de baja usuario" onclick="return confirm('�Estas seguro que deseas dar de baja tu usuario?');" class="btn btn-danger" />
			        </form:form>
			        </div>
			    </td>
		    </tr>
	    </table>
	    
	    </div>
	    </div>
	</body>
</html>