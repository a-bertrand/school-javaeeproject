<%@ page import="classes.Note" %>
<jsp:useBean id="list_note" scope="request" type="java.util.List<Note>"  />
<div class="generic_form">

<script>
		// declare data var 
		var globaldata = [];
		var i =0;
		var matiere = new Object()
	</script>

<h3>Note du groupe : <%= request.getParameter("groupe") %> </h3>
	
	<table class="table table-hover">
        <thead>
            <tr>
                <th>Matiere</th>
                <th>Etudiant</th>
                <th>Note / 20</th>
            </tr>
        </thead>
        <tbody>
        	<% 
				for(Note note : list_note ) 
				{ %>
					<script>
						
						if (matiere["<%=note.getMatiere().getNom()%>"] == null )
						{
							matiere["<%=note.getMatiere().getNom()%>"] == <%= note.getNote()%>;
						} 
						
						matiere["<%=note.getMatiere().getNom()%>"] = matiere["<%=note.getMatiere().getNom()%>"] +','+ <%= note.getNote()%>; 
						
					</script>
					<tr>  	
	 	 				<td><%= note.getMatiere().getNom()%></td>
	 	 				<td><%= note.getEtudiant().getNom()%></td>
						<td><%= note.getNote()%></td>
					</tr>
					
			<%	}%>
			
        </tbody>
    </table>
    
</div>
 <hr><br>
<div style="margin-left:25%" class="infos">
</div>
<button style="color: black;"class="button-change1">Visualisation des notes  </button>
<div class="place container" style="margin-left: 12%;">
</div>
<script>
	var j = 0;
	for(var el in matiere )
	{
		var data = new Object();
		var nb = 0;
		data["label"]	= el;
		var tab = matiere[el].split(",");
		var i = 1;
		while ( i < tab.length )
		{	
			//console.log(tab[i]);
			nb = nb + parseInt(tab[i]);
			i++;
		}
		i = i -1;
		var moy =  nb / i;
		data["nb"]	= moy.toFixed(2);
		globaldata[j] = data;
		j++;
	}
	generatechart(globaldata,".place",".button-change1",20);
</script>