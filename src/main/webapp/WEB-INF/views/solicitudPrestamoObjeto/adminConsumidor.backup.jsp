<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>Insert title here</title>
	</head>
	<body>
	    <div class="body">
        <h2>Administraci&oacute;n de solicitudes de prestamo</h2>
        
        <div class="list">
        <table class="table table-striped" style="width:auto">
            <thead>
            <tr>
                <th></th>
                <th>Nombre del objeto</th>
                <!--th>Descripci&oacute;n del objeto</th-->
                <th></th>
                <th>Mensaje</th>
                <th>Respuesta</th>
                <th></th>
            </tr>
            </thead>
            
            <tbody>
            <c:forEach items="${solicitudesPrestamoObjeto}" var="solicitudPrestamoObjeto" varStatus="status">
	        <tr>
	            <td><img src="<c:url value='/resources/images/objetos/${solicitudPrestamoObjeto.objeto.objetoId}-${solicitudPrestamoObjeto.objeto.nombreImagen}' />" style="max-height:120px"/></td>
			    <td>${solicitudPrestamoObjeto.objeto.nombreObjeto}</td>
			    <!--td>${solicitudPrestamoObjeto.objeto.descripcion}</td-->
			    <td>
	            <a href="<c:url value='/solicitudPrestamoObjeto/consulta/${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}' />" >Consultar solicitud de prestamo</a>
	            </td>
			    <td>${solicitudPrestamoObjeto.mensajeSolicitudPrestamo}</td>
			    <td>${solicitudPrestamoObjeto.mensajeRespuesta}</td>
			    <td>
			    <c:if test="${solicitudPrestamoObjeto.solicitudAceptada==true && solicitudPrestamoObjeto.envioPrestamoObjetoPrestador==true}">
			        <form:form method="POST" action="${pageContext.request.contextPath}/solicitudPrestamoObjeto/confirmarRecepcionObjeto">
			        <input type="submit" value="Confirmar recepci&oacute;n del objeto" class="btn btn-success" />
			        </form:form>
			    </c:if>
			    </td>
			</tr>
		    </c:forEach>
		    </tbody>
		</table>
		</div>
		
		</div>
	</body>
</html>