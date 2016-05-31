<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <title>Insert title here</title>
    <script type="text/javascript">
    $(document).ready(function() {
    	var dialog = $('#solicitudPrestamoObjetoAltaDialog').dialog({
            autoOpen: false,
            height: 230,
            width: 580,
            modal: true/*,
            buttons: {
                Cancel: function() {
                  dialog.dialog( "close" );
                }
            }*/,
            close: function() {
                //$('input').val("").removeClass("ui-state-error");
            }
        });
    	
    	var solicitudPrestamoObjetoAltaFormAction = "${pageContext.request.contextPath}/solicitudPrestamoObjeto/realizarAlta";
        var solicitudPrestamoObjetoCambiosFormAction = "${pageContext.request.contextPath}/solicitudPrestamoObjeto/realizarCambios";
        
        var solicitudPrestamoObjetoAltaDialog = "${solicitudPrestamoObjetoAltaDialog}" == "true" ? true : false;
        if (solicitudPrestamoObjetoAltaDialog) {
        	formId = 'solicitudPrestamoObjetoAltaForm';
                
                var objetoId = "${objeto.objetoId}";
                var params = objetoId ? '/objetoId='+objetoId : '';
                $('form'+(formId ? '#'+formId : '')).attr('action', solicitudPrestamoObjetoAltaFormAction+params);
                
                $('#solicitudPrestamoObjetoAltaDialog').dialog('open');
        }
        $('.solicitudPrestamoObjetoAltaButton').button().click(function() {
            formId = 'solicitudPrestamoObjetoAltaForm';
            
            $('form'+(formId ? '#'+formId : '')+' select').val('');
            $('form'+(formId ? '#'+formId : '')+' textarea').val('');
            $('form'+(formId ? '#'+formId : '')+' input').not(':hidden, :checkbox, :radio, :button, :submit, :reset').val('');
            $('form'+(formId ? '#'+formId : '')+' :checkbox, :radio').prop('checked', false);
            
            var objetoId = "";//"${objeto.objetoId}";
            var params = objetoId ? '/objetoId='+objetoId : '';
            $('form'+(formId ? '#'+formId : '')).attr('action', solicitudPrestamoObjetoAltaFormAction+params);
            
            $('#solicitudPrestamoObjetoAltaDialog').dialog('open');
        });
    });
    </script>
    </head>
    <body>
        <div id="solicitudPrestamoObjetoAltaDialog" title="Solicitud de prestamo">
		    <!--p class="validateTips">All form fields are required.</p-->
		    <form:form id="solicitudPrestamoObjetoAltaForm" method="POST" action="${pageContext.request.contextPath}/solicitudPrestamoObjeto/realizarAlta" modelAttribute="solicitudPrestamoObjeto">
		    <div class="dialog">
		    <input type="hidden" id="objeto.objetoId" name="objeto.objetoId" value="${objeto.objetoId}" readonly="true" class="form-control" />
		    <table>
		        <tr>
		            <td><form:label path="mensajeSolicitudPrestamo">Mensaje</form:label></td>  
		            <td>
		                <form:textarea path="mensajeSolicitudPrestamo" value="${solicitudPrestamoObjeto.mensajeSolicitudPrestamo}" class="form-control" style="height:125px" />
		            </td>
		            <td>
			            <form:errors path="mensajeSolicitudPrestamo" cssClass="alert alert-warning" element="div" />
		            </td>
		        </tr>
		        
		        <tr>
		            <td></td>
		            <td><input type="submit" value="Enviar solicitud de prestamo" class="btn btn-success" /></td>  
		        </tr>
		    </table>
		    </div>
		    </form:form>
		</div>
    
        <div class="body">
	    <h2>Consulta de objeto</h2>
        
        <div class="dialog">
	    <table class="table" style="width:auto">
	        <tr>
		        <td rowspan="5" style="text-align:center; vertical-align:middle">
		        <img src="<c:url value='/resources/images/objetos/${objeto.objetoId}-${objeto.nombreImagen}' />" style="max-height:250px"/>
		        </td>
	        </tr>
	    
		    <tr>
			    <th>Nombre</th>
			    <td>${objeto.nombreObjeto}</td>
			    <td rowspan="4" style="vertical-align:middle; max-width:300px">
			    <sec:authorize var="loggedIn" access="isAuthenticated()" />
                <c:choose>
                <c:when test="${loggedIn}">
                <button class="solicitudPrestamoObjetoAltaButton btn btn-success" objetoId="${objeto.objetoId}">Solicitar prestamo</button>
                </c:when>
                </c:choose>
			    </td>
		    </tr>
		    
		    <tr>
			    <th>Descripci&oacute;n</th>
			    <td>${objeto.descripcion}</td>
		    </tr>
		    
		    <tr>
			    <th>Beneficio esperado</th>
			    <td>${objeto.beneficioEsperado}</td>
		    </tr>
		    
		    <tr>
			    <th>Periodo de tiempo de prestamo</th>
			    <td>${objeto.periodoTiempoPrestamo}</td>
		    </tr>
	    </table>
	    
	    </div>
	    </div>
    </body>
</html>