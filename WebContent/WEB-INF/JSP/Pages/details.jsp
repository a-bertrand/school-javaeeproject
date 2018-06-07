<%@ page import="classes.Note" %>
<%@ page import="classes.Etudiant" %>
<%@ page import="classes.EtudiantDAO" %>
<jsp:useBean id="list_note" scope="request" type="java.util.List<Note>"  />
<jsp:useBean id="etudiant" scope="request" type="classes.Etudiant"  />
<jsp:useBean id="list_etudiant" scope="request" type="java.util.List<Etudiant>"  />


<div class="generic_form">
<p>Informations concernant  l'étudiant :<br> <%=etudiant.getNom()%> <%=etudiant.getPrenom()%></p>
	<ul>
		<li>Nombre d'abscence : <%=etudiant.getNbAbsences()%>   </li>
		<li>Groupe : <%=etudiant.getGroupe().getNom()%>		</li>
	</ul>
	<script>
		// declare data var 
		var globaldata = [];
		var i =0;
	</script>
	<table class="table table-hover">
        <thead>
            <tr>
                <th>Matiere</th>
                <th>Note / 20</th>
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
	 	 				<td><%= note.getMatiere().getNom()%></td>
						<td><%= note.getNote()%></td>
					</tr>
					<%
						nb = nb + 1;
						moyenne = moyenne + note.getNote();
					%>
			<%	}%>
			
        </tbody>
    </table>
    <div class="pull-right">
  		Moyenne Géneral : <%=moyenne/nb %><br>
    </div>
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