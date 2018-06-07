<%@page import="classes.Etudiant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="classes.Etudiant" %>
<jsp:useBean id="list_etudiant" scope="request" type="java.util.List<Etudiant>"  />
<div class="container">

	<h3>Note du groupe : <%= request.getParameter("groupe") %> </h3>
	<table class="table table-hover">
		<tr>
			<th>Nom		</th>
			<th>Prenom	</th>
			<th>Abscence	</th>
		</tr>
		<% 
			for(Etudiant etu : list_etudiant ) 
			{ 
		%>
				<tr>  	
			 	 	<td><%=etu.getNom()%> 		</td>
			 	 	<td><%=etu.getPrenom()%>	</td>
			 	 	<td><%=etu.getNbAbsences()%>	</td>
				</tr>
		<%		
			}
		%>
	</table>	
<%-- ----------------------------------- --%>
</div> <!-- END OF CONTAINER -->
	