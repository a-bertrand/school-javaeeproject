package classes;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Table 
@Entity
public class Note implements Serializable
{
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne 
	private Etudiant etudiant;
	
	@ManyToOne
	private Matiere matiere;
	
	@Column(nullable=false)
	private int note;

	public Integer  getId()
	{
		return this.id;
	}
	
	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

}
