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
	    <h2>Alta de objeto</h2>
	    
		<form:form method="POST" action="${pageContext.request.contextPath}/objeto/realizarAlta" modelAttribute="objeto" enctype="multipart/form-data">
		    <div class="dialog">
		    <table>
		        <tr>
		            <td><form:label path="tipoObjeto">Tipo de Objeto</form:label></td>
		            <td>
		                <form:select multiple="single" path="tipoObjeto.tipoObjetoId" class="form-control" >
		                <!--form:option selected="true" value="${objeto.tipoObjeto.tipoObjetoId}" /-->
                        <form:option value="" label="-Elige-" />
                        <form:options items="${tiposObjeto}" itemValue="tipoObjetoId" itemLabel="nombreTipoObjeto" />
                        </form:select>
		            </td>
		            <td>
		                <form:errors path="tipoObjeto" cssClass="alert alert-warning" element="div" />
		            </td>
		        </tr>
		    
		        <tr>
		            <td><form:label path="nombreObjeto">Nombre</form:label></td>  
		            <td>
		                <form:input path="nombreObjeto" value="${nombre.nombreObjeto}" class="form-control" />
		            </td>
		            <td>
			            <form:errors path="nombreObjeto" cssClass="alert alert-warning" element="div" />
		            </td>
		        </tr>
		        
		        <tr>
		            <td><form:label path="descripcion">Descripción</form:label></td>  
		            <td>
		                <form:textarea path="descripcion" value="${objeto.descripcion}" class="form-control" />
		            </td>
		            <td>
		                <form:errors path="descripcion" cssClass="alert alert-warning" element="div" />
		            </td>
		        </tr>
		        
		        <tr>
		            <td><form:label path="beneficioEsperado">Beneficio esperado</form:label></td>  
		            <td>
		                <form:input path="beneficioEsperado" value="${objeto.beneficioEsperado}" class="form-control" />
		            </td>
		            <td>
		                <form:errors path="beneficioEsperado" cssClass="alert alert-warning" element="div" />
			        </td>
		        </tr>
		        
		        <tr>
		            <td><form:label path="periodoTiempoPrestamo">Periodo de tiempo de prestamo</form:label></td>  
		            <td>
		                <form:input path="periodoTiempoPrestamo" value="${objeto.periodoTiempoPrestamo}" class="form-control" />
		            </td>
		            <td>
		                <form:errors path="periodoTiempoPrestamo" cssClass="alert alert-warning" element="div" />
		            </td>
		        </tr>
		        
		        <tr>
		            <td><form:label path="imagenObjetos">Imagen</form:label></td>  
		            <td>
		                <!--c:forEach items="${imagnees}" var="imagen" varStatus="status"-->
		                <!--input type="file" name="imagenes[${status.index}].nombreImagen" value="${imagen.name}" /-->
		                <!--/c:forEach-->
		                <input type="file" name="imagen" value="" />
		            </td>
		            <td>
		                <form:errors path="imagenObjetos" cssClass="alert alert-warning" element="div" />
		            </td>
		        </tr>
		        
		        <tr>
		            <td></td>
		            <td><input type="submit" value="Dar de alta objeto" class="btn btn-success" /></td>  
		        </tr>  
            </table>
            </div>
        </form:form>
        </div>
	</body>
</html>
