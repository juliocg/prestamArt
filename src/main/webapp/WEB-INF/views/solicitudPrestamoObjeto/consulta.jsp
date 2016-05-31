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
    	var dialog = $('#solicitudPrestamoObjetoAceptadaDialog').dialog({
            autoOpen: false,
            height: 230,
            width: 580,
            modal: true,
            close: function() {
                //$('input').val("").removeClass("ui-state-error");
            }
        });
    	
    	var solicitudPrestamoObjetoAceptadaFormAction = "${pageContext.request.contextPath}/solicitudPrestamoObjeto/aceptar";
        
        var solicitudPrestamoObjetoAceptadaDialog = "${solicitudPrestamoObjetoAceptadaDialog}" == "true" ? true : false;
        if (solicitudPrestamoObjetoAceptadaDialog) {
        	formId = 'solicitudPrestamoObjetoAceptadaForm';
                
                var solicitudPrestamoObjetoId = "${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}";
                var params = solicitudPrestamoObjetoId ? '/solicitudPrestamoObjetoId='+solicitudPrestamoObjetoId : '';
                $('form'+(formId ? '#'+formId : '')).attr('action', solicitudPrestamoObjetoAceptadaFormAction+params);
                
                $('#solicitudPrestamoObjetoAceptadaDialog').dialog('open');
        }
        $('.solicitudPrestamoObjetoAceptadaButton').button().click(function() {
            formId = 'solicitudPrestamoObjetoAceptadaForm';
            
            $('form'+(formId ? '#'+formId : '')+' select').val('');
            $('form'+(formId ? '#'+formId : '')+' textarea').val('');
            $('form'+(formId ? '#'+formId : '')+' input').not(':hidden, :checkbox, :radio, :button, :submit, :reset').val('');
            $('form'+(formId ? '#'+formId : '')+' :checkbox, :radio').prop('checked', false);
            
            var solicitudPrestamoObjetoId = "";//"${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}";
            var params = solicitudPrestamoObjetoId ? '/solicitudPrestamoObjetoId='+solicitudPrestamoObjetoId : '';
            $('form'+(formId ? '#'+formId : '')).attr('action', solicitudPrestamoObjetoAceptadaFormAction+params);
            
            $('#solicitudPrestamoObjetoAceptadaDialog').dialog('open');
        });
        
        
        var dialog = $('#solicitudPrestamoObjetoRechazadaDialog').dialog({
            autoOpen: false,
            height: 230,
            width: 580,
            modal: true,
            close: function() {
                //$('input').val("").removeClass("ui-state-error");
            }
        });
    	
    	var solicitudPrestamoObjetoRechazadaFormAction = "${pageContext.request.contextPath}/solicitudPrestamoObjeto/rechazar";
        
        var solicitudPrestamoObjetoRechazadaDialog = "${solicitudPrestamoObjetoRechazadaDialog}" == "true" ? true : false;
        if (solicitudPrestamoObjetoRechazadaDialog) {
        	formId = 'solicitudPrestamoObjetoRechazadaForm';
                
                var solicitudPrestamoObjetoId = "${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}";
                var params = solicitudPrestamoObjetoId ? '/solicitudPrestamoObjetoId='+solicitudPrestamoObjetoId : '';
                $('form'+(formId ? '#'+formId : '')).attr('action', solicitudPrestamoObjetoRechazadaFormAction+params);
                
                $('#solicitudPrestamoObjetoRechazadaDialog').dialog('open');
        }
        $('.solicitudPrestamoObjetoRechazadaButton').button().click(function() {
            formId = 'solicitudPrestamoObjetoRechazadaForm';
            
            $('form'+(formId ? '#'+formId : '')+' select').val('');
            $('form'+(formId ? '#'+formId : '')+' textarea').val('');
            $('form'+(formId ? '#'+formId : '')+' input').not(':hidden, :checkbox, :radio, :button, :submit, :reset').val('');
            $('form'+(formId ? '#'+formId : '')+' :checkbox, :radio').prop('checked', false);
            
            var solicitudPrestamoObjetoId = "";//"${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}";
            var params = solicitudPrestamoObjetoId ? '/solicitudPrestamoObjetoId='+solicitudPrestamoObjetoId : '';
            $('form'+(formId ? '#'+formId : '')).attr('action', solicitudPrestamoObjetoRechazadaFormAction+params);
            
            $('#solicitudPrestamoObjetoRechazadaDialog').dialog('open');
        });
    });
    </script>
    </head>
    <body>
        <div id="solicitudPrestamoObjetoAceptadaDialog" title="Aceptar solicitud de prestamo">
		    <!--p class="validateTips">All form fields are required.</p-->
		    <form:form id="solicitudPrestamoObjetoAceptadaForm" method="POST" action="${pageContext.request.contextPath}/solicitudPrestamoObjeto/aceptar" modelAttribute="solicitudPrestamoObjeto">
		    <div class="dialog">
		    <form:hidden path="solicitudPrestamoObjetoId" value="${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}" readonly="true" class="form-control" />
		    <table>
		        <tr>
		            <td><form:label path="mensajeRespuesta">Respuesta</form:label></td>  
		            <td>
		                <form:textarea path="mensajeRespuesta" value="${solicitudPrestamoObjeto.mensajeRespuesta}" class="form-control" style="height:125px" />
		            </td>
		            <td>
			            <form:errors path="mensajeRespuesta" cssClass="alert alert-warning" element="div" />
		            </td>
		        </tr>
		        
		        <tr>
		            <td></td>
		            <td><input type="submit" value="Aceptar solicitud de prestamo" class="btn btn-success" /></td>  
		        </tr>
		    </table>
		    </div>
		    </form:form>
		</div>
		
		<div id="solicitudPrestamoObjetoRechazadaDialog" title="Rechazar solicitud de prestamo">
		    <!--p class="validateTips">All form fields are required.</p-->
		    <form:form id="solicitudPrestamoObjetoRechazadaForm" method="POST" action="${pageContext.request.contextPath}/solicitudPrestamoObjeto/rechazar" modelAttribute="solicitudPrestamoObjeto">
		    <div class="dialog">
		    <form:hidden path="solicitudPrestamoObjetoId" value="${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}" readonly="true" class="form-control" />
		    <table>
		        <tr>
		            <td><form:label path="mensajeRespuesta">Respuesta</form:label></td>  
		            <td>
		                <form:textarea path="mensajeRespuesta" value="${solicitudPrestamoObjeto.mensajeRespuesta}" class="form-control" style="height:125px" />
		            </td>
		            <td>
			            <form:errors path="mensajeRespuesta" cssClass="alert alert-warning" element="div" />
		            </td>
		        </tr>
		        
		        <tr>
		            <td></td>
		            <td><input type="submit" value="Rechazar solicitud de prestamo" class="btn btn-success" /></td>  
		        </tr>
		    </table>
		    </div>
		    </form:form>
		</div>
    
        <div class="body">
	    <h2>Solicitud de prestamo de objeto</h2>
	    
	    <h3>Datos del objeto</h3>
	    
	    <div class="dialog">
	    <table class="table" style="width:100%; margin-left:0px">
	        <tr>
		        <td rowspan="5" style="text-align:center; vertical-align:middle; min-width:50px">
		        <img src="<c:url value='/resources/images/objetos/${solicitudPrestamoObjeto.objeto.objetoId}-${solicitudPrestamoObjeto.objeto.nombreImagen}' />" style="max-height:250px"/>
		        </td>
	        </tr>
	    
		    <tr>
			    <th style="min-width:100px; max-width:180px">Nombre</th>
			    <td>${solicitudPrestamoObjeto.objeto.nombreObjeto}</td>
			    <td rowspan="4" style="vertical-align:middle; max-width:300px">
			        <sec:authorize access="hasRole('ROLE_PRESTADOR')">
	                <c:choose>
                    <c:when test="${solicitudPrestamoObjeto.solicitudAceptada && (!solicitudPrestamoObjeto.envioPrestamoObjetoPrestador || solicitudPrestamoObjeto.envioPrestamoObjetoPrestador eq null) && !solicitudPrestamoObjeto.objeto.prestado}">
                    <form:form method="POST" action="${pageContext.request.contextPath}/solicitudPrestamoObjeto/confirmarEnvioObjeto">
                        <input type="hidden" id="solicitudPrestamoObjetoId" name="solicitudPrestamoObjetoId" value="${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}" readonly="true" />
                        <input type="submit" name="envioObjeto" value="Confirmar envío del objeto" class="btn btn-success" />
                    </form:form>
                    </c:when>
                    <c:when test="${solicitudPrestamoObjeto.solicitudAceptada && solicitudPrestamoObjeto.devolucionObjetoConsumidor && (!solicitudPrestamoObjeto.devolucionObjetoRecibidaPrestador || solicitudPrestamoObjeto.devolucionObjetoRecibidaPrestador eq null) && solicitudPrestamoObjeto.objeto.prestado}">
                    <form:form method="POST" action="${pageContext.request.contextPath}/solicitudPrestamoObjeto/confirmarRecepcionDevolucionObjeto">
                        <input type="hidden" id="solicitudPrestamoObjetoId" name="solicitudPrestamoObjetoId" value="${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}" readonly="true" />
                        <input type="submit" name="devolucionObjetoRecibida" value="Confirmar recepción del objeto devuelto" class="btn btn-success" />
                    </form:form>
                    </c:when>
                    </c:choose>
                    </sec:authorize>
                    
                    <sec:authorize access="hasRole('ROLE_CONSUMIDOR')">
                    <c:choose>
                    <c:when test="${solicitudPrestamoObjeto.solicitudAceptada && solicitudPrestamoObjeto.envioPrestamoObjetoPrestador && !solicitudPrestamoObjeto.envioPrestamoObjetoRecibidoConsumidor && solicitudPrestamoObjeto.objeto.prestado}">
			        <form:form method="POST" action="${pageContext.request.contextPath}/solicitudPrestamoObjeto/confirmarRecepcionEnvioObjeto">
			            <input type="hidden" id="solicitudPrestamoObjetoId" name="solicitudPrestamoObjetoId" value="${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}" readonly="true" />
			            <input type="submit" name="envioObjetoRecibido" value="Confirmar recepción del objeto enviado" class="btn btn-success" />
			        </form:form>
			        </c:when>
			        <c:when test="${solicitudPrestamoObjeto.solicitudAceptada && solicitudPrestamoObjeto.envioPrestamoObjetoPrestador && solicitudPrestamoObjeto.envioPrestamoObjetoRecibidoConsumidor && !solicitudPrestamoObjeto.devolucionObjetoConsumidor && solicitudPrestamoObjeto.objeto.prestado}">
			        <form:form method="POST" action="${pageContext.request.contextPath}/solicitudPrestamoObjeto/confirmarDevolucionObjeto">
			            <input type="hidden" id="solicitudPrestamoObjetoId" name="solicitudPrestamoObjetoId" value="${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}" readonly="true" />
			            <input type="submit" name="devolucionObjeto" value="Confirmar devolución del objeto" class="btn btn-success" />
			        </form:form>
			        </c:when>
                    </c:choose>
                    </sec:authorize>
			    </td>
		    </tr>
		    
		    <tr>
			    <th>Descripci&oacute;n</th>
			    <td>${solicitudPrestamoObjeto.objeto.descripcion}</td>
		    </tr>
		    
		    <tr>
			    <th>Beneficio esperado</th>
			    <td>${solicitudPrestamoObjeto.objeto.beneficioEsperado}</td>
		    </tr>
		    
		    <tr>
			    <th>Periodo de tiempo de prestamo</th>
			    <td>${solicitudPrestamoObjeto.objeto.periodoTiempoPrestamo}</td>
		    </tr>
	    </table>
	    </div>
	    
	    <div class="dialog">
	    <sec:authorize var="loggedIn" access="isAuthenticated()" />
	    <table class="table" style="width:100%; margin-left:0px">
	        <tr>
	            <th style="width:175px">Mensaje del consumidor</th>
		        <td>${solicitudPrestamoObjeto.mensajeSolicitudPrestamo}</td>
		        
		        <c:if test="${loggedIn}">
		        <sec:authorize access="hasRole('ROLE_PRESTADOR')">
		        <c:choose>
	            <c:when test="${solicitudPrestamoObjeto.solicitudAceptada == null}">
	            <td rowspan="1" style="width:275px">
		            <div style="margin-bottom: 15px;">
	                <button class="solicitudPrestamoObjetoAceptadaButton btn btn-success" solicitudPrestamoObjetoId="${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}">Aceptar solicitud de prestamo</button>
	                </div>
	                <div style="margin-top: 15px;">
	                <button class="solicitudPrestamoObjetoRechazadaButton btn btn-success" solicitudPrestamoObjetoId="${solicitudPrestamoObjeto.solicitudPrestamoObjetoId}">Rechazar solicitud de prestamo</button>
	                </div>
	            </td>
		        </c:when>
	            </c:choose>
	            </sec:authorize>
	            </c:if>
		    </tr>
		    
		    <c:if test="${loggedIn && solicitudPrestamoObjeto.solicitudAceptada != null}">
		    <tr>
		        <th style="width:175px">Solicitud aceptada</th>
		        <td>
		            <c:choose>
	                <c:when test="${solicitudPrestamoObjeto.solicitudAceptada}">Si</c:when>
	                <c:when test="${!solicitudPrestamoObjeto.solicitudAceptada}">No</c:when>
	                </c:choose>
		        </td>
		    </tr>
		    
		    <tr>
		        <th style="width:175px">Respuesta del prestador</th>
		        <td>${solicitudPrestamoObjeto.mensajeRespuesta}</td>
		    </tr>
		    </c:if>
		    
		    <c:if test="${loggedIn && solicitudPrestamoObjeto.solicitudAceptada != null}">
		    <tr>
		        <th style="width:175px">¿El prestador ya envio el objeto?</th>
		        <td>
		            <c:choose>
	                <c:when test="${solicitudPrestamoObjeto.envioPrestamoObjetoPrestador}">Si</c:when>
	                <c:when test="${!solicitudPrestamoObjeto.envioPrestamoObjetoPrestador}">No</c:when>
	                </c:choose>
		        </td>
		    </tr>
		    </c:if>
		    
		    <c:if test="${loggedIn && solicitudPrestamoObjeto.solicitudAceptada != null}">
		    <tr>
		        <th style="width:175px">¿El consumidor ya recibio el objeto?</th>
		        <td>
		            <c:choose>
	                <c:when test="${solicitudPrestamoObjeto.envioPrestamoObjetoRecibidoConsumidor}">Si</c:when>
	                <c:when test="${!solicitudPrestamoObjeto.envioPrestamoObjetoRecibidoConsumidor}">No</c:when>
	                </c:choose>
		        </td>
		    </tr>
		    </c:if>
		    
		    <c:if test="${loggedIn && solicitudPrestamoObjeto.solicitudAceptada != null}">
		    <tr>
		        <th style="width:175px">¿El consumidor ya devolvio el objeto?</th>
		        <td>
		            <c:choose>
	                <c:when test="${solicitudPrestamoObjeto.devolucionObjetoConsumidor}">Si</c:when>
	                <c:when test="${!solicitudPrestamoObjeto.devolucionObjetoConsumidor}">No</c:when>
	                </c:choose>
		        </td>
		    </tr>
		    </c:if>
		    
		    <c:if test="${loggedIn && solicitudPrestamoObjeto.solicitudAceptada != null}">
		    <tr>
		        <th style="width:175px">¿El prestador ya recibio el objeto?</th>
		        <td>
		            <c:choose>
	                <c:when test="${solicitudPrestamoObjeto.devolucionObjetoRecibidaPrestador}">Si</c:when>
	                <c:when test="${!solicitudPrestamoObjeto.devolucionObjetoRecibidaPrestador}">No</c:when>
	                </c:choose>
		        </td>
		    </tr>
		    </c:if>
		</table>
		</div>
	    
	    <h3>Datos del consumidor</h3>
	    
	    <div class="dialog">
	    <table class="table" style="width:100%; margin-left:0px">
	        <tr>
			    <th style="width:175px">Nombre del consumidor</th>
			    <td>${solicitudPrestamoObjeto.consumidor.nombre} ${solicitudPrestamoObjeto.consumidor.apellidos}</td>
		    </tr>
		    
		    <tr>
			    <th>Correo Electr&oacute;nico</th>
			    <td>${solicitudPrestamoObjeto.consumidor.correoElectronico}</td>
		    </tr>
		    
		    <tr>
			    <th>Telefono</th>
			    <td>${solicitudPrestamoObjeto.consumidor.telefono}</td>
		    </tr>
		    
		    <tr>
			    <th>Otro dato de contacto</th>
			    <td>${solicitudPrestamoObjeto.consumidor.otroDatoContacto}</td>
		    </tr>
	    </table>
	    </div>
	    </div>
    </body>
</html>
