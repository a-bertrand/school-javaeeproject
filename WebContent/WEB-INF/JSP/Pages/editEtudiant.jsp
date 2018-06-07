<%@ page import="classes.Note" %>
<%@ page import="classes.Groupe" %>
<jsp:useBean id="groupes" scope="request" type="java.util.List<Groupe>"  />
<jsp:useBean id="etudiant" scope="request" type="classes.Etudiant"  />
<jsp:useBean id="list_note" scope="request" type="java.util.List<Note>"  />

<div class="generic_form">
	<h3>Edition de l'Etudiant : <%=etudiant.getNom()%> <%=etudiant.getPrenom()%></h3>
	<form method="POST" action="validationEditionEtudiant">
		<label>Nom : </label>
		<input type="text" value="<%=etudiant.getNom()%>" name="nom">
		<label>Prenom :</label>
		<input type="text" value="<%=etudiant.getPrenom()%>" name="prenom">
		<label>Nombre d'abscence :</label>
		<input type="number" min=0 value="<%=etudiant.getNbAbsences()%>" name="nbabs" ><br>
		<label>Groupe : </label>
		<script>
			// declare data var 
			var globaldata = [];
			var i =0;
		</script>
		<select name="groupe">
			<%for(Groupe groupe : groupes ) 
			{ %>  	
			 	 <option value="<%=groupe.getId()%>"><%=groupe.getNom()%></option>
			<%}%>				
		</select><br><br>
		<input type="hidden" value="<%=etudiant.getId()%>" name="etu_id">
		<a href="consult?etu_id=<%=etudiant.getId()%>"> Voir détails étudiant</a>
		<br><h4> Administration des notes</h4>
		<table class="table table-hover">
			<thead>
	            <tr>
	                <th>Matiere</th>
	                <th>Note / 20</th>
	                <th>Supression</th>
	            </tr>
	        </thead>
	        <tbody>
				<%
				Integer nb = 0;
        		Integer moyenne = 0;
				for(Note note : list_note ) 
				{ %>
				<script>
						console.log(i)
						var data = new Object();
					 	data["label"]	= " <%=note.getMatiere().getNom()%>" ;
						data["nb"]		=	<%= note.getNote()%>;
						globaldata[i] = data;
						i++;
					</script>
					<tr>  	
		 	 			<td>
		 	 				<label><%=note.getMatiere().getNom()%>  </label>
		 	 			</td>
						<td>
							<input type="number" min=0 max=20 value="<%=note.getNote()%>" name="<%=note.getMatiere().getNom()%>" > 
						</td>
						<td>
							<a href="dodeleteNote?note_id=<%=note.getId() %>&etu_id=<%=note.getEtudiant().getId() %>"><span class="glyphicon glyphicon-remove"></span></a>
						</td>
					</tr>	
					<%
						nb = nb + 1;
						moyenne = moyenne + note.getNote();
					%>
				<%}%>
			</tbody>	
		</table>
	<%if(nb > 0){ %>
	  	<div class="pull-right">
	  		<p>Moyenne Géneral : <%=moyenne/nb %> </p>
	  	</div>
  	<%}%>
		<button type="submit">Valider l'édition</button>
	</form>
	<br><hr><br>
	<h4>Ajouter une matiere avec une note : </h4>	
	<form action="ajouterNote" method="POST">
    	<label>Matiere</label>
    	<input type="text" name="matiere">
    	<label>Note</label>
    	<input type="number" min=0 max=20 name="note">
    	<input type="hidden" name="etu_id" value="<%=etudiant.getId()%>"><br><br>
		<button type="submit">Ajouter une note</button>
	</form>
</div><br>
<hr><br>
    <div style="margin-left:25%" class="infos">
    </div>
    <button style="color: black;"class="button-change1">Visualisation des notes  </button>
    <div class="place container" style="margin-left: 12%;">
    </div>
    <script>
    	generatechart(globaldata,".place",".button-change1",20);
    </script>