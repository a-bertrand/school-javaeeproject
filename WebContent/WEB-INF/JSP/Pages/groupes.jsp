<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<%@ page import="classes.Groupe" %>
<jsp:useBean id="groupes" scope="request" type="java.util.List<Groupe>"  />
<div>
	<table class="table table-hover">
			<tr>
				<th>Nom		</th>
				<th>Note 	</th>
				<th>Abscence 	</th>
				<th>Etudiants 	</th>
			</tr>	
			<%
			for(Groupe groupe : groupes ) 
			{ %>
				<tr>  	
			 	 	<td><%=groupe.getNom()%></td>
			 	 	<td><a href="notebyGroupe?groupe=<%=groupe.getNom()%>">Voir les notes du groupe</a></td>
			 	 	<td><a href="abscencebyGroupe?groupe=<%=groupe.getNom()%>">Voir les abscences des etuduant groupe</a></td>
			 	 	<td><a href="etbyetu?groupe=<%=groupe.getNom()%>">Listes des Etudiants</a></td>
				</tr>
			<%}%>
	</table>	
</div>