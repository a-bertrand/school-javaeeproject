<%@page import="classes.Etudiant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<%@ page import="classes.Etudiant" %>
<jsp:useBean id="etudiants" scope="request" type="java.util.List<Etudiant>"  />

<%-- Element d'action : jsp:include --%>
<h3>Choisir un étudiant : </h3>
<%-- ----------------------------------- --%>
<%-- Formulaire --%>
<%-- ----------------------------------- --%>
<script>
		// declare data var 
		var globaldata = [];
		var i =0;
		var last_groupe;
		var new_data = new Object;
		var nb_etudiant = 0;
</script>
<table class="table table-hover">
	<tr>
		<th>Nom		</th>
		<th>Prenom	</th>
		<th>Groupe 	</th>
		<th>Détails	</th>
		<th>Edition</th>
		<th>Supression</th>
	</tr>
	<% 
	for(Etudiant etudiant : etudiants ) 
	{ %>
		<script>
			if(new_data["<%=etudiant.getGroupe().getNom()%>"] == null)
				{
					new_data["<%=etudiant.getGroupe().getNom()%>"] = 1;
				}
			else
				{
					new_data["<%=etudiant.getGroupe().getNom()%>"] = new_data["<%=etudiant.getGroupe().getNom()%>"] + 1;
				}
			nb_etudiant++ ;
		</script>
		<tr>  	
	 	 	<td><%=etudiant.getNom()%> 		</td>
	 	 	<td><%=etudiant.getPrenom()%>	</td>
	 	 	<td><%=etudiant.getGroupe().getNom()%>	</td>
	 	 	<td><a href="consult?etu_id=<%=etudiant.getId()%>"> Voir détails </a></td>
	 	 	<td><a href="editEtudiant?etu_id=<%=etudiant.getId()%>">Edition</a></td>
	 	 	<td>
				<a href="dodeleteEtudiant?etu_id=<%=etudiant.getId() %>"><span class="glyphicon glyphicon-remove"></span></a>
			</td>
		</tr>
	<%}%>
</table>
<div class="pull-right">
	<a href="addetu"><button>AJOUTER ETUDIANT</button><a>
</div>
	
<%-- ----------------------------------- --%>
<br><br>
<hr><br>
    <div style="margin-left:25%" class="infos">
    </div>
    <button style="color: black;"class="button-change1">Visualisation des notes  </button>
    <div class="place container" style="margin-left: 12%;">
    </div>
    <script>
    	var nb = 0;
    	for(var i in new_data)
   		{
    		var data = new Object();
    		data["label"] = i;
    		data["nb"]	= new_data[i];
    		globaldata[nb] = data;
    		nb++;
   		}
    	generatechart(globaldata,".place",".button-change1",nb_etudiant);
    </script>