package au.uq.dke.comon_rcp2.ontology.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class OntologyItem {
	
	public OntologyItem(){
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long id;
	
	public String toString(){
		return this.getId().toString();
	}
}
