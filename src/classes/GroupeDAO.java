package classes;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GroupeDAO {

	public static Groupe create(String nom) {
		
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		
		// create
		em.getTransaction().begin();

		// create new groupe
		Groupe groupe = new Groupe();
		groupe.setNom(nom);
		em.persist(groupe);

		// Commit
		em.getTransaction().commit();

		// Close the entity manager
		em.close();
		
		return groupe;
	}
	
	
	public static int removeAll() {
		
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();

		//
		em.getTransaction().begin();
		
		// RemoveAll
		int deletedCount = em.createQuery("DELETE FROM Groupe").executeUpdate();

		// Commit
		em.getTransaction().commit();
		
		// Close the entity manager
		em.close();
		
		return deletedCount;
	}
	public static List<Groupe> getAll() 
	{

		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
				
		// Recherche 
		Query q = em.createQuery("SELECT g FROM Groupe g");

		@SuppressWarnings("unchecked")
		List<Groupe> listGroupe = q.getResultList();
		
		return listGroupe;
	}
	
	//--------------------------------
	// CUSTOM
	
	public static Groupe getByName(String name) 
	{
		// NE FONCTIONNE PAS ....
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
				
		// Recherche 
		Query q = em.createQuery("SELECT g FROM Groupe g WHERE g.name="+name+";");

		@SuppressWarnings("unchecked")
		Groupe groupe= (Groupe) q.getSingleResult();
		
		return groupe;
	}
	public static Groupe retrieveById(int id) 
	{
		
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
			
		//
		Groupe groupe = em.find(Groupe.class, id);
		
	    // Close the entity manager
	    em.close();
				
		return groupe;
	}
}
