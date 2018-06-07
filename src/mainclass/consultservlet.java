package mainclass;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

/**
 * CONSULTER LES NOTES D4UN ELEVE 
 * Servlet implementation class consultservlet
 */
@WebServlet("/consultservlet")
public class consultservlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	//private String details = getServletConfig().getInitParameter("urldetails");   
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public consultservlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }
;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id_etudiant = request.getParameter("etu_id");
		Integer idetu = Integer.parseInt(id_etudiant);
		Etudiant etudiant = EtudiantDAO.retrieveById(idetu);
		request.setAttribute("etudiant", etudiant);
		loadJSP("/WEB-INF/JSP/Pages/details.jsp",request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void loadJSP(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{	
//		L'interface RequestDispatcher permet de transférer le contrôle à une autre servlet
//		Deux méthodes possibles :
//		- forward() : donne le contrôle à une autre servlet. Annule le flux de sortie de la servlet courante
//		- include() : inclus dynamiquement une autre servlet
//			+ le contrôle est donné à une autre servlet puis revient à la servlet courante (sorte d'appel de fonction). 
//			+ Le flux de sortie n'est pas supprimé et les deux se cumulent
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
