<%@page import="classes.Etudiant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="classes.Etudiant" %>
<jsp:useBean id="etudiant" scope="request" type="java.util.List<Etudiant>"  />
<jsp:useBean id="groupe" scope="request" type="java.lang.String"  />
<div class="container">
	<h3>Groupe <%=groupe %></h3>
	<table class="table table-hover">
		<tr>
			<th>Nom		</th>
			<th>Prenom	</th>
			<th>Groupe 	</th>
			<th>Détails	</th>
		</tr>
		<% 
			for(Etudiant etu : etudiant ) 
			{ 
				String grp = groupe.replaceAll(" ","");
				String etugrp =  etu.getGroupe().getNom().replaceAll(" ","");
				System.out.println(grp);
				System.out.println(etugrp);
				
				if ( etu.getGroupe().getNom().equals(grp))
				{
		%>
					<tr>  	
				 	 	<td><%=etu.getNom()%> 		</td>
				 	 	<td><%=etu.getPrenom()%>	</td>
				 	 	<td><%=etu.getGroupe().getNom()%>	</td>
				 	 	<td><a href="consult?etu_id=<%=etu.getId()%>"> Voir détails </a>			</td>
					</tr>
		<%		}
			}
		%>
	</table>	
<%-- ----------------------------------- --%>
</div> <!-- END OF CONTAINER -->
	