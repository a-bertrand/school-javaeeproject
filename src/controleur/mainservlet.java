package controleur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Etudiant;
import classes.EtudiantDAO;
import classes.GestionFactory;
import classes.Groupe;
import classes.GroupeDAO;
import classes.Matiere;
import classes.MatiereDAO;
import classes.Note;
import classes.NoteDAO;
/**
 * Servlet implementation class mainservlet
 * 
 * TODO LIST 
 * 
 * 		– Consulter les groupes d’étudiants		
 * 				=> OK		
 * 		
 * 		– Editer des notes d’un étudiant ou d’un groupe
 * 				=> OK 
 * 
 * 		– Consulter des notes d’un étudiant ou d’un groupe
 * 				=> Etudiant OK / Groupe OK 
 * 
 * 		– Editer des absences d’un étudiant ou d’un groupe
 * 				=>  OK
 * 
 * 		– Consulter les absences d’un étudiant ou d’un groupe
 * 				=> Etudiant OK  / Groupe OK	
 * 
 * 		– Consulter la fiche d’un étudiant
 * 				=> OK 
 * 		
 * 		- Ajouter un Etudiant 
 * 				=> OK 
 * 
 * 		- Ajout des Chart 
 * 				=> OK 
 */
@WebServlet("/mainservlet")
public class mainservlet extends HttpServlet 
{
	private String homepage	;	 
	private String details	; 
	private String groupes  ;
	private String etubygroupe;
	private String notebyetu;
	private String addetu	;
	private String notebygroupe	;
	private String abscencebygroupe;
	private String editEtudiant;
	// -------------------------------------------------------------------
	// Initialise les Url.
	public void init() throws ServletException 
	{
		homepage 			= getServletConfig().getInitParameter("urlindex");
		details 			= getServletConfig().getInitParameter("urldetails");
		groupes 			= getServletConfig().getInitParameter("urlgroupes");
		etubygroupe			= getServletConfig().getInitParameter("urletudiantByGroupe");
		notebyetu 			= getServletConfig().getInitParameter( "urlnoteByEtudiant");
		addetu				= getServletConfig().getInitParameter("urladdetu");
		notebygroupe		= getServletConfig().getInitParameter("urlnotebygroupe");
		abscencebygroupe	= getServletConfig().getInitParameter("urlabscencebygroupe");
		editEtudiant		= getServletConfig().getInitParameter("urledutEtudiant");
		
		// Création de la factory permettant la création d'EntityManager
		// (gestion des transactions)
		GestionFactory.open();

		///// INITIALISATION DE LA BD
		// Normalement l'initialisation se fait directement dans la base de données
		/*if ((GroupeDAO.getAll().size() == 0) && (EtudiantDAO.getAll().size() == 0)) 
		{
			// -------------------------------------------------------------------
			// Creation des groupes
			Groupe MIAM 	= GroupeDAO.create("miam");
			Groupe SIMO 	= GroupeDAO.create("SIMO");
			Groupe MESSI 	= GroupeDAO.create("MESSI");
			
			// -------------------------------------------------------------------
			// Creation des étudiants
			Etudiant etudiant1 =	EtudiantDAO.create("Francis", "Brunet-Manquat", MIAM);
			Etudiant etudiant2 =	EtudiantDAO.create("Philippe", "Martin", 		MIAM);
			Etudiant etudiant3 =	EtudiantDAO.create("Mario", "Cortes-Cornax", 	MIAM);
			Etudiant etudiant4 =	EtudiantDAO.create("Françoise", "Coat", 		SIMO);
			Etudiant etudiant5 =	EtudiantDAO.create("Laurent", "Bonnaud", 		MESSI);
			Etudiant etudiant6 =	EtudiantDAO.create("Sébastien", "Bourdon",  	MESSI);
			Etudiant etudiant7 =	EtudiantDAO.create("Mathieu", "Gatumel", 		SIMO);
			
			// -------------------------------------------------------------------
			// Un étudiant est dans un groupe -> plusieurs matiére. Il peut avoir une note dans une matiere. 
			Matiere matiere1 = MatiereDAO.create("Français"	, 		MIAM);
			Matiere matiere2 = MatiereDAO.create("Mathématique",	MIAM);
			Matiere matiere3 = MatiereDAO.create("Anglais"	, 		MIAM);
			Matiere matiere4 = MatiereDAO.create("Informatique",	SIMO);
			Matiere matiere5 = MatiereDAO.create("Français"	, 		SIMO);
			Matiere matiere6 = MatiereDAO.create("Informatique",  	MESSI);
			Matiere matiere7 = MatiereDAO.create("Anglais"	,  		MESSI);
		
			// -------------------------------------------------------------------
			// note,matiere,etudiant
			NoteDAO.create(12, matiere1, etudiant1);
			NoteDAO.create(10, matiere2, etudiant1);
			NoteDAO.create(8 , matiere3, etudiant1);
			NoteDAO.create(16, matiere6, etudiant1);
			NoteDAO.create(12, matiere2, etudiant2);
			NoteDAO.create(8 , matiere1, etudiant2);
			NoteDAO.create(12, matiere3, etudiant3);
			NoteDAO.create(12, matiere4, etudiant4);
			NoteDAO.create(12, matiere5, etudiant5);
			NoteDAO.create(12, matiere6, etudiant6);		
			NoteDAO.create(12, matiere7, etudiant7);
			// -------------------------------------------------------------------
		}*/
	} // END OF init
	
	@Override
	public void destroy() 
	{
		super.destroy();

		// Fermeture de la factory
		GestionFactory.close();
	}
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public mainservlet() 
	{
		super();

		// TODO Auto-generated constructor stub
	}
	// -------------------------------------------------------------------
	// GET 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// On récupère la méthode d'envoi de la requête
		String methode = request.getMethod().toLowerCase();
		
		// On récupère l'action à exécuter
		String action = request.getPathInfo();
		// Exécution action
		
//System.out.println(action);
		
		if(action == null)
		{
			//action ="/main";
		}	
		if (methode.equals("get") && action.equals("/consult")) 
		{
			doConsult(request, response);
		} 
		else if (methode.equals("get") && action.equals("/groupes")) 
		{
			doallgroupe(request, response);
		}
		else if (methode.equals("get") && action.equals("/etbyetu")) 
		{
			doetbygroupe(request, response);
		}
		else if (methode.equals("get") && action.equals("/acceuil")) 
		{
			// Load etudiant 
			List<Etudiant> etudiants = EtudiantDAO.getAll();
			//
			request.setAttribute("etudiants", etudiants);
			// TODO Auto-generated method stub
			loadJSP(homepage,request, response);
		}
		else if(methode.equals("get") && action.equals("/addetu"))
		{
			doaddetu(request, response);
		}
		else if(methode.equals("get") && action.equals("/notebyGroupe"))
		{
			donotebygroupe(request, response);
		}
		else if(methode.equals("get") && action.equals("/abscencebyGroupe"))
		{
			doabscencebygroupe(request, response);
		}
		else if (methode.equals("get") && action.equals("/editEtudiant"))
		{
			doeditEtudiant(request, response);
		}
		else if (methode.equals("post") && action.equals("/validationEditionEtudiant"))
		{
			dovalidationEditionEtudiant(request, response);
		}
		else if (methode.equals("post") && action.equals("/ajouterEtudiant"))
		{
			doajouterEtudiant(request, response);
		}
		else if (methode.equals("post") && action.equals("/validationAjoutEtudiant"))
		{
			doajouteretudiant(request, response);
		}
		else if (methode.equals("post") && action.equals("/ajouterNote"))
		{
			doajouterNote(request, response);
		}
		else if	(methode.equals("get") && action.equals("/dodeleteEtudiant"))
		{
			dodeleteEtudiant(request, response);
		}
		else if	(methode.equals("get") && action.equals("/dodeleteNote"))
		{
			dodeleteNote(request, response);
		}
		else 
		{
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/JSP/redirect.jsp");
			rd.forward(request, response);
		}
	}
	// -------------------------------------------------------------------
	// POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	//  ------------------------------------------------------------------
	//  -------------------------------  Function     --------------------
	private void doConsult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Consultation des détails d'un étudiant
		
		String id_etudiant = request.getParameter("etu_id");
		Integer idetu = Integer.parseInt(id_etudiant);
		Etudiant etudiant = EtudiantDAO.retrieveById(idetu);
		request.setAttribute("etudiant", etudiant);
		
		List<Note> list_note = NoteDAO.getAllbyEtudiant(etudiant);
		request.setAttribute("list_note", list_note);
		
		List<Etudiant> list_etudiant = EtudiantDAO.getAll();
		request.setAttribute("list_etudiant", list_etudiant);
		// boucle -> trouver etudiant -> prendre celui d'apres et celui d'avant
		
		
		loadJSP("/WEB-INF/JSP/Pages/details.jsp",request, response);
	}
	// -------------------------------------------------------------------
	// ETUDIANT
	private void doaddetu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		// Load groupe 
		List<Groupe> groupes = GroupeDAO.getAll();
		//
		request.setAttribute("groupes", groupes);
		// TODO Auto-generated method stub
		loadJSP(addetu,request, response);
	}
	private void doajouteretudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		// Load groupe 
		List<Groupe> groupes = GroupeDAO.getAll();
		//
		request.setAttribute("groupes", groupes);
		// TODO Auto-generated method stub
		loadJSP(addetu,request, response);
	}
	private void doeditEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer etu_id = Integer.parseInt(request.getParameter("etu_id"));
		Etudiant etudiant = EtudiantDAO.retrieveById(etu_id);
		request.setAttribute("etudiant", etudiant);
		List<Groupe> groupes = GroupeDAO.getAll();
		request.setAttribute("groupes", groupes);
		
		List<Note> list_note = NoteDAO.getAllbyEtudiant(etudiant);
		request.setAttribute("list_note", list_note);
		
		loadJSP(editEtudiant,request, response);
	}
	private void dovalidationEditionEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer etu_id = Integer.parseInt(request.getParameter("etu_id"));
		Etudiant etudiant = EtudiantDAO.retrieveById(etu_id);
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		Integer nbabscence = Integer.parseInt(request.getParameter("nbabs"));
		Integer id_groupe = Integer.parseInt(request.getParameter("groupe"));
		
		Groupe groupe = GroupeDAO.retrieveById(id_groupe);
		
		etudiant.setGroupe(groupe); 
		etudiant.setNbAbsences(nbabscence);
		etudiant.setNom(nom);
		etudiant.setPrenom(prenom);
		
		EtudiantDAO.update(etudiant);
		
		request.setAttribute("etudiant", etudiant);
		List<Groupe> groupes = GroupeDAO.getAll();
		request.setAttribute("groupes", groupes);
		
		List<Note> list_note = NoteDAO.getAllbyEtudiant(etudiant);
		request.setAttribute("list_note", list_note);
		
		for(Note note : list_note )
		{
			if(request.getParameter(""+note.getMatiere().getNom()) != null)
			{
				Integer value_note = Integer.parseInt(request.getParameter(""+note.getMatiere().getNom() ) );
				note.setNote(value_note);
				NoteDAO.update(note);
			}
		}
		loadJSP(editEtudiant,request, response);
	}
	// -------------------------------------------------------------------
	// Note
	private void doajouterNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		Integer etu_id = Integer.parseInt(request.getParameter("etu_id"));
		Etudiant etudiant = EtudiantDAO.retrieveById(etu_id);
		request.setAttribute("etudiant", etudiant);
		
		List<Groupe> groupes = GroupeDAO.getAll();
		request.setAttribute("groupes", groupes);
		
		// ---- 
		// Ajout de la note 
		String nom_matiere = request.getParameter("matiere");
		List<Matiere> list_matiere = MatiereDAO.getAll();
		Matiere found_matiere = new Matiere();
		for(Matiere matiere : list_matiere)
		{
//System.out.println(name_groupe);
//System.out.println(note.getEtudiant().getGroupe().getNom());
			if (matiere.getNom().equals(nom_matiere) )
			{
				found_matiere=matiere;;
			}
		}
		if(found_matiere.getId() == null)
		{
			found_matiere = MatiereDAO.create(nom_matiere,etudiant.getGroupe());
			//System.out.println("azeaze"+found_matiere);
		}
		
		Integer new_note = Integer.parseInt(request.getParameter("note"));
		
		NoteDAO.create(new_note, found_matiere, etudiant);
		
		
		List<Note> list_note = NoteDAO.getAllbyEtudiant(etudiant);
		request.setAttribute("list_note", list_note);
		
		loadJSP(editEtudiant,request, response);
	}
	// -------------------------------------------------------------------
	// GROUPE
	private void doetbygroupe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//
		String groupe = request.getParameter("groupe");
		// Load etudiant 
		List<Etudiant> etudiants = EtudiantDAO.getAll();
		//
		//List<Etudiant> new_listetudiants = new ArrayList<Etudiant>();

		//System.out.println(new_listetudiants);
		request.setAttribute("etudiant", etudiants);
		request.setAttribute("groupe", groupe);
		loadJSP("/WEB-INF/JSP/Pages/etudiantByGroupe.jsp",request, response);
	}
	private void doallgroupe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		// Load groupe 
		List<Groupe> groupes = GroupeDAO.getAll();
		//
		request.setAttribute("groupes", groupes);
		// TODO Auto-generated method stub
		loadJSP("/WEB-INF/JSP/Pages/groupes.jsp",request, response);
	}
	private void  donotebygroupe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO recupere les note pour un groupe. 
		// all note si etudiant.groupe = groupe. 
		String name_groupe = request.getParameter("groupe");
		//Groupe groupe2 = GroupeDAO.getByName(name_groupe);
		
		List<Note> list_note = NoteDAO.getAll();
				
		list_note = list_note.stream().filter(u -> 
		{
			Etudiant e =  u.getEtudiant();
			Groupe  g = e.getGroupe();
			return g.getNom().equalsIgnoreCase(name_groupe);
		}).collect(Collectors.toList());
		
		
		request.setAttribute("list_note", list_note);
		loadJSP(notebygroupe,request, response);
	}
	private void doabscencebygroupe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO recupere les note pour un groupe. 
		// all note si etudiant.groupe = groupe. 
		String name_groupe = request.getParameter("groupe");
		//Groupe groupe2 = GroupeDAO.getByName(name_groupe);

		List<Etudiant> all_etudiant = EtudiantDAO.getAll();
		List<Etudiant> list_etudiant = new ArrayList<Etudiant>();
		for(Etudiant etudiant : all_etudiant)
		{
			//System.out.println(name_groupe);
			//System.out.println(note.getEtudiant().getGroupe().getNom());
			if (etudiant.getGroupe().getNom().equals(name_groupe) )
			{
				list_etudiant.add(etudiant);
			}
		}
		request.setAttribute("list_etudiant", list_etudiant);
		loadJSP(abscencebygroupe,request, response);
	}
	// -------------------------------------------------------------------
	private void  doajouterEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Etudiant etudiant = new Etudiant();
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		List<Groupe> all_groupe= GroupeDAO.getAll();
		String name_groupe = request.getParameter("groupe");
		Groupe found_groupe = new Groupe();
		for(Groupe groupe : all_groupe)
		{
			if(groupe.getNom().equals(name_groupe))
			{
				found_groupe = groupe;
			}
		}
		//Groupe groupe = GroupeDAO.getByName(name_groupe);  ne marche pas... 

		EtudiantDAO.create(nom,prenom,found_groupe);
		
		// Load etudiant 
		List<Etudiant> etudiants = EtudiantDAO.getAll();
		//
		request.setAttribute("etudiants", etudiants);
		// TODO Auto-generated method stub
		loadJSP(homepage,request, response);
	}
	// -------------------------------------------------------------------
	// DELETE
	private void dodeleteNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// DELETE note 
		String note_id = request.getParameter("note_id");
//System.out.println(note_id);
		NoteDAO.remove(Integer.parseInt(note_id));

		Integer etu_id = Integer.parseInt(request.getParameter("etu_id"));
		
		// --- RELOAD
		Etudiant etudiant = EtudiantDAO.retrieveById(etu_id);
		request.setAttribute("etudiant", etudiant);
		
		  
		List<Groupe> groupes = GroupeDAO.getAll();
		request.setAttribute("groupes", groupes);
		
		List<Note> list_note = NoteDAO.getAllbyEtudiant(etudiant);
		request.setAttribute("list_note", list_note);
		
		loadJSP(editEtudiant,request, response);
	}
	private void dodeleteEtudiant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer id_etudiant = Integer.parseInt(request.getParameter("etu_id"));
		EtudiantDAO.remove(id_etudiant);
		
		List<Etudiant> etudiants = EtudiantDAO.getAll();
		request.setAttribute("etudiants", etudiants);
		loadJSP(homepage,request, response);
	}
	// -------------------------------------------------------------------
	public void loadJSP(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		//		L'interface RequestDispatcher permet de transférer le contrôle à une autre servlet
		//		Deux méthodes possibles :
		//		- forward() : donne le contrôle à une autre servlet. Annule le flux de sortie de la servlet courante
		//		- include() : inclus dynamiquement une autre servlet
		//			+ le contrôle est donné à une autre servlet puis revient à la servlet courante (sorte d'appel de fonction). 
		//			+ Le flux de sortie n'est pas supprimé et les deux se cumulent
		
		String add_url = url;
		request.setAttribute("url", add_url);
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/JSP/template.jsp");
		rd.forward(request, response);
	}
}