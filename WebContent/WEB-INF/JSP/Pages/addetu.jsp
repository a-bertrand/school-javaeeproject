<%@ page import="java.util.List" %>
<%@ page import="classes.Groupe" %>
<jsp:useBean id="groupes" scope="request" type="java.util.List<Groupe>"  />
<div class="generic_form">
	<form method="POST" action="ajouterEtudiant">
	
		<label>Nom :</label>	<input name="nom" type="text"></input>
		
		<label>Prenom :</label>	<input name="prenom"  type="text"></input>
		
		<label>Groupe :</label>	
		<select name="groupe">
			<%for(Groupe groupe : groupes ) 
			{ %>  	
			 	 <option value="<%=groupe.getNom()%>"><%=groupe.getNom()%></option>
			<%}%>				
		</select>
		<div class="pull-right">
			<button type="submit">Ajouter Etudiant</button>
		</div>
		
		
	</form>
</div>	
