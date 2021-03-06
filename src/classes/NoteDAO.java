package classes;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class NoteDAO 
{
	public static Note create(Integer note, Matiere matiere, Etudiant etudiant) 
	{
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		// create
		em.getTransaction().begin();
		
		// create new matiere
		Note new_note = new Note();
		new_note.setNote(note);
		new_note.setMatiere(matiere);
		new_note.setEtudiant(etudiant);
		em.persist(new_note);
		
		// Commit
		em.getTransaction().commit();
		// Close the entity manager
		em.close();
		return new_note;
	}
	public static int removeAll() 
	{
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		em.getTransaction().begin();
		
		// RemoveAll
		int deletedCount = em.createQuery("DELETE FROM Note").executeUpdate();
		// Commit
		em.getTransaction().commit();		
		// Close the entity manager
		em.close();
		return deletedCount;
	}
	public static int removeAllNoteByEtudiant(Integer etu_id) 
	{
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		em.getTransaction().begin();
		
		// RemoveAll
		int deletedCount = em.createQuery("DELETE FROM Note n WHERE n.etudiant.id=:id").setParameter("id", etu_id).executeUpdate();
		// Commit
		em.getTransaction().commit();		
		// Close the entity manager
		em.close();
		return deletedCount;
	}
	public static void remove(Integer note_id) {
		
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
	
		//
		em.getTransaction().begin();
		
		// Retrouver l'entité persistante et ses liens avec d'autres entités en vue de la suppression
		Note note = em.find(Note.class, note_id);
		em.remove(note);
		
		// Commit
		em.getTransaction().commit();
				
		// Close the entity manager
		em.close();
		
		// if EclipseLink cache enable -->
		// GestionFactory.factory.getCache().evictAll();
	}
	// besoin d'un nom de groupe pour faire apparaitre la liste de matiere
	public static List<Note> getAll() 
	{
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
				
		// Recherche 
		Query q = em.createQuery("SELECT n FROM Note n"); 
			
		@SuppressWarnings("unchecked")
		List<Note> listNote = q.getResultList();
		
		return listNote;
	}
	
	public static List<Note> getAllbyEtudiant(Etudiant etudiant) 
	{
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
				
		// Recherche 
		Query q = em.createQuery("SELECT n FROM Note n WHERE n.etudiant.id="+etudiant.getId());  // TODO VERIFIER dANS BD SI ID groupe suffit
			
		@SuppressWarnings("unchecked")
		List<Note> listNote = q.getResultList();
		
		return listNote;
	}
	public static Note update(Note note) 
	{
		
		// Creation de l'entity manager
		EntityManager em = GestionFactory.factory.createEntityManager();
		
		//
		em.getTransaction().begin();

		// Attacher une entité persistante (etudiant) à l’EntityManager courant  pour réaliser la modification
		em.merge(note);
		
		// Commit
		em.getTransaction().commit();

		// Close the entity manager
		em.close();
		
		return note;
	}
}
