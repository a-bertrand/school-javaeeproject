package classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Matiere  implements Serializable
{
	// lié à un groupe. 
	
		@Id
		@GeneratedValue
		private Integer id;
		
		@Column(nullable=false)
		private String nom;
		
		@ManyToOne
		private Groupe groupe;

		public Matiere() 
		{
			super();
		}  
		
		public Integer getId() {
			return this.id;
		}

		public void setId(Integer id) {
			this.id = id;
		}   

		public String getNom() {
			return this.nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}
		
		public Groupe getGroupe() {
			return this.groupe;
		}
		
		public void setGroupe(Groupe groupe) 
		{
	        this.groupe = groupe;
	        if (!groupe.getMatiere().contains(this)) 
	        {
	        	groupe.getMatiere().add(this);
	        }
	    }
	   
		@Override
		public String toString() {
			return "[" + this.getId() + "] " + this.getNom();
		}
}


