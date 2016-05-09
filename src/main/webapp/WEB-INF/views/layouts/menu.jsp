<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Menú</title>
  </head>
<body>
   <center>
   <p> <h3>Menú</h3></p>

      <a href="<c:url value="contactos.html"/>">
        <img src="${pageContext.request.contextPath}/img/agenda.jpg" border="0" 
		     title="Agenda"/>
		<br>Agenda</a>   
	  	  
	  <a href="<c:url value="/materiales.html"/>">	 
		<img src="${pageContext.request.contextPath}/img/patin.jpg" border="0" 
		     title="Materiales"/></a>
	  Materiales
				  
      <a href="<c:url value="embarques.html"/>">	 
        
		<img src="${pageContext.request.contextPath}/img/embarque.jpg" border="0" 
		     title="Embarques"/><br>Embarques</a>	  
   </center>
</body>
</html>