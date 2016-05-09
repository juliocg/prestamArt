<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>
    <tiles:insertAttribute name="title" ignore="true" />
    </title>
    </head>
    <body style="margin:0px">
        <table align="center" style="width:100%">
	        <tr>
		        <td colspan="2" style="width:100%; background-color:#232f3e"><tiles:insertAttribute name="header" /></td>
	        </tr>
	        <tr>
		        <td style="height:20%; width:20%"><tiles:insertAttribute name="menu" /></td>
		        <td style="height:20%; width:80%"><tiles:insertAttribute name="body" /></td>
	        </tr>
	        <tr>
		        <td colspan="2" style="height:10%;"><tiles:insertAttribute name="footer" /></td>
	        </tr>
        </table>
  
  
  <div id="spinner" class="spinner" style="display:none;">
            
        </div>
      <div id="bodyPop">
        <div id="topMenu">
          <div class="topLogin">
            <div id="loginButton">
            </div>
          </div>
        </div>
        <div id="logo">
        </div>
        <!--tiles:insertAttribute name="body" /-->
      </div>
</body>
</html>