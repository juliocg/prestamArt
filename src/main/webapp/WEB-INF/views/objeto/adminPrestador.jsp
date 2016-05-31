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
        <h2>Administraci&oacute;n de objetos</h2>
        
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
        
        <div class="list">
        <div>
        <a href="<c:url value='/objeto/alta' />" class="btn btn-success">Alta de objeto</a>
        </div>
        
        <table class="table table-striped" style="width:auto">
            <thead>
            <tr>
                <th></th>
                <th>Tipo</th>
                <th>Nombre</th>
                <th>Descripci&oacute;n</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            
            <tbody>
            <c:forEach items="${objetos}" var="objeto" varStatus="status">
	        <tr>
			    <td><img src="<c:url value='/resources/images/objetos/${objeto.objetoId}-${objeto.nombreImagen}' />" style="max-height:120px"/></td>
			    <td>${objeto.tipoObjeto.nombreTipoObjeto}</td>
			    <td>${objeto.nombreObjeto}</td>
			    <td>${objeto.descripcion}</td>
			    <td>
			        <table>
			        <c:forEach items="${objeto.solicitudPrestamoObjetos}" var="solicitudPrestamoObjeto" varStatus="status1">
			        <tr>
			            <td>
			            <!--c:if test="${!solicitudPrestamoObjeto.solicitudAceptada}"-->
			            <a href="<c:url value='/solicitudPrestamoObjeto/consulta/${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}' />" >Consultar solicitud de prestamo</a>
			            <!--/c:if-->
			            </td>
			            
                        <td>
			            <c:if test="${solicitudPrestamoObjeto.solicitudAceptada != null}">
			            <c:choose>
		                <c:when test="${solicitudPrestamoObjeto.solicitudAceptada}">Aceptaste esta solicitud</c:when>
		                <c:when test="${!solicitudPrestamoObjeto.solicitudAceptada}">No aceptaste esta solicitud</c:when>
		                </c:choose>
		                </c:if>
			            </td>
			        </tr>
			        </c:forEach>
			        </table>
			    </td>
			    <td>
			        <div style="margin-bottom:15px">
			        <a href="<c:url value='/objeto/cambios/${objeto.objetoId}' />" class="btn btn-success">Cambiar datos de objeto</a>
			        </div>
			        <div style="margin-top:15px">
			        <form:form method="POST" action="${pageContext.request.contextPath}/objeto/realizarBaja">
			        <input type="hidden" id="objetoId" name="objetoId" value="${objeto.objetoId}" readonly="true" />
			        <input type="submit" value="Dar de baja objeto" onclick="return confirm('¿Estas seguro que deseas dar de baja el objeto?');" class="btn btn-danger" />
			        </form:form>
			        </div>
			    </td>
			</tr>
		    </c:forEach>
		    </tbody>
		</table>
		</div>
		
		</div>
	</body>
</html>