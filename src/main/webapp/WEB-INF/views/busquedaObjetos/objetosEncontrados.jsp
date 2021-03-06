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
        <h2>Objetos encontrados</h2>
        
        <div class="list">
        <c:choose>
        <c:when test="${objetos.size() > 0}">
        <table class="table table-striped" style="width:auto">
            <thead>
            <tr>
                <th></th>
                <th>Tipo</th>
                <th>Nombre</th>
                <th>Descripci&oacute;n</th>
            </tr>
            </thead>
            
            <tbody>
            <c:forEach items="${objetos}" var="objeto" varStatus="status">
	        <tr>
			    <td><img src="<c:url value='/resources/images/objetos/${objeto.objetoId}-${objeto.nombreImagen}' />" style="max-height:120px"/></td>
			    <td>${objeto.tipoObjeto.nombreTipoObjeto}</td>
			    <td><a href="<c:url value='/consultaObjeto/${objeto.objetoId}' />">${objeto.nombreObjeto}</a></td>
			    <td>${objeto.descripcion}</td>
			</tr>
		    </c:forEach>
		    </tbody>
		</table>
		</c:when>
		<c:otherwise>
		<div style="text-align:center">Ningun objeto encontrado</div>
		</c:otherwise>
		</c:choose>
		</div>
        </div>
    </body>
</html>