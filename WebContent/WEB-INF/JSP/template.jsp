<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="classes.Etudiant" %>
<%@ page import="classes.Note" %>

<jsp:useBean id="url" scope="request" type="java.lang.String"  />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../css/main.css" >
		<script  type="text/javascript" src="../js/d3.min.js"></script>
    <script  type="text/javascript" src="../js/pie.js"></script>
    <script  type="text/javascript" src="../js/barchart.js"></script>
    <script  type="text/javascript" src="../js/main-chart.js"></script>
		<title>Gestion des notes</title>
	</head>
	
	<body>
	
		<header class="nav_bar">
			<div class="container">
				<nav id="navbar">
					<ul class="nav nav-pills">
						<li role="presentation"><a href="acceuil">Les Etudiants</a></li>
						<li role="presentation"><a href="groupes">Les Groupes</a></li>
					</ul>
				</nav>
			</div>
		</header>
		
		<div class=" main_content"> 
			<div class="container">
				<jsp:include page="<%=url%>" />
			</div>
		</div> <!-- END OF CONTAINER -->
		
	</body>
</html>	
	