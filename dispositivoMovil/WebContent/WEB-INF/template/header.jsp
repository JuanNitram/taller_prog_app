<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.culturarte.controllers.Login"%>
<%@ page import="javax.swing.tree.DefaultMutableTreeNode"%>
<%@ page import="javax.swing.tree.TreeNode"%>
<%@ page import="javax.swing.tree.TreeModel"%>
<%@ page import="javax.swing.JTree"%>
<%@page import="com.culturarte.controllers.Propuestas"%>
<!DOCTYPE html>
<link rel="stylesheet" href="/MobileDevice/media/Data-picker/css/bootstrap-datepicker.css" />
<style>
	input[type=number]::-webkit-inner-spin-button, 
	input[type=number]::-webkit-outer-spin-button { 
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    margin: 0; 
}
</style>
<div id="header sinflecha">
	<div class="subtitulo"></div>

	<%
		servidor.DtColaborador usr = Login.getUsuarioLogueado(request);
		if(request.getAttribute("excepcion") != null) {
	%>
		<script type="text/javascript">
			$(document).ready(function(){
			    $("#excepcion").modal();
			});
		</script>
	<% 
			request.setAttribute("excepcion", false);
		} 
	%>

	<jsp:include page="/WEB-INF/errorPages/excepcion.jsp" />

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<img style="width: 40px; height: 40px;"
				src="/MobileDevice/media/images/logo_icon.png"></img> <a class="navbar-brand"
				href="/MobileDevice">Culturarte Movil</a>

			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">

				<% if(usr != null) { 
					String bienvenido= "Bienvenido ";
					%>
					<ul class="navbar-nav ml-auto">
						<li class="nav-item active"><a class="nav-link" href="/MobileDevice">Inicio
								<span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="propuestas">Propuestas</a></li>
						<li class="nav-item"><a class="nav-link" href="colaboraciones?action=pagar">Colaboraciones</a></li>
						
						<li id="divisor" class="nav-item"><a class="nav-link"
							href="/MobileDevice/login?action=cerrar">Cerrar sesión</a></li>
						<li class="nav-item"><a class="nav-link"><%= bienvenido.concat(usr.getNombre()) %></a></li>
					</ul>
				<% } else { %>
					<ul class="navbar-nav ml-auto">
						<li id="space" class="nav-item active"><a class="nav-link"
							href="/MobileDevice">Inicio <span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item"><a class="nav-link" href="propuestas">Propuestas</a></li>
						<li class="nav-item"><a href="#iniciarsesion"
							class="nav-link">Iniciar sesión</a></li>
					</ul>
				<% } %>
			</div>
		</div>
	</nav>
	
	<% if(request.getSession().getAttribute("usuario_logueado") == null){ %>
	
	<script type="text/javascript">
    	$(window).on('load',function(){
        	$('#iniciarsesion').modal('show');
    	});
	</script>
	
	<% } %>

	<script src="/media/Data-picker/js/bootstrap-datepicker.min.js"></script>
	<script src="/media/app.js"></script>
</div>
