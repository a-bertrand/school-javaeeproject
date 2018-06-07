package classes;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
public class MatiereDAO 
{
		public static Matiere create(String nom, Groupe groupe) 
		{
			
			// Creation de l'entity manager
			EntityManager em = GestionFactory.factory.createEntityManager();
			
			// create
			em.getTransaction().begin();

			// create new matiere
			Matiere matiere = new Matiere();
			matiere.setNom(nom);
			matiere.setGroupe(groupe);
			em.persist(matiere);

			// Commit
			em.getTransaction().commit();

			// Close the entity manager
			em.close();
			
			return matiere;
		}
		
		
		public static int removeAll() {
			
			// Creation de l'entity manager
			EntityManager em = GestionFactory.factory.createEntityManager();

			//
			em.getTransaction().begin();
			
			// RemoveAll
			int deletedCount = em.createQuery("DELETE FROM Matiere").executeUpdate();

			// Commit
			em.getTransaction().commit();
			
			// Close the entity manager
			em.close();
			
			return deletedCount;
		}
		
		
		// besoin d'un nom de groupe pour faire apparaitre la liste de matiere
		public static List<Matiere> getAllbygroupe(Groupe groupe) 
		{

			// Creation de l'entity manager
			EntityManager em = GestionFactory.factory.createEntityManager();
					
			// Recherche 
			Query q = em.createQuery("SELECT * FROM Matiere WHERE groupe="+groupe.getId() );  // TODO VERIFIER dANS BD SI ID groupe suffit

			@SuppressWarnings("unchecked")
			List<Matiere> listMatiere = q.getResultList();
			
			return listMatiere;
		}
		// besoin d'un nom de groupe pour faire apparaitre la liste de matiere
		public static List<Matiere> getAll() 
		{

			// Creation de l'entity manager
			EntityManager em = GestionFactory.factory.createEntityManager();
					
			// Recherche 
			Query q = em.createQuery("SELECT m FROM Matiere m");  // TODO VERIFIER dANS BD SI ID groupe suffit

			@SuppressWarnings("unchecked")
			List<Matiere> listMatiere = q.getResultList();
			
			return listMatiere;
		}

}
