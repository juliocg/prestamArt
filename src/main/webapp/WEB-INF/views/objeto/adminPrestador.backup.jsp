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
        
        <div class="list">
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
			            <c:choose>
                        <c:when test="${solicitudPrestamoObjeto.solicitudAceptada && !solicitudPrestamoObjeto.envioPrestamoObjetoPrestador && !objeto.prestado}">
                        <form:form method="POST" action="${pageContext.request.contextPath}/solicitudPrestamoObjeto/confirmarEnvioObjeto">
                            <input type="hidden" id="solicitudPrestamoObjetoId" name="solicitudPrestamoObjetoId" value="${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}" readonly="true" />
                            <input type="submit" name="envioObjeto" value="Confirmar envío del objeto" class="btn btn-success" />
                        </form:form>
                        </c:when>
                        <c:when test="${solicitudPrestamoObjeto.solicitudAceptada && solicitudPrestamoObjeto.devolucionObjetoConsumidor && objeto.prestado}">
                        <form:form method="POST" action="${pageContext.request.contextPath}/solicitudPrestamoObjeto/confirmarRecepcionDevolucionObjeto">
                            <input type="hidden" id="solicitudPrestamoObjetoId" name="solicitudPrestamoObjetoId" value="${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}" readonly="true" />
                            <input type="submit" name="devolucionObjetoRecibida" value="Confirmar devolución recibida" class="btn btn-success" />
                        </form:form>
                        </c:when>
                        </c:choose>
			            </td>
			        </tr>
			        </c:forEach>
			        </table>
			    </td>
			    <td>
			        <div style="margin-bottom: 15px;">
			        <a href="<c:url value='/objeto/cambios/${objeto.objetoId}' />" class="btn btn-success">Cambiar datos de objeto</a>
			        </div>
			        <div style="margin-top: 15px;">
			        <input type="submit" value="Dar de baja objeto" onclick="return confirm('¿Estas seguro de eliminar?');" class="btn btn-danger" />
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