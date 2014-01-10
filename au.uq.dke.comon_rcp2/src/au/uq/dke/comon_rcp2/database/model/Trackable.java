package au.uq.dke.comon_rcp2.database.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.metawidget.inspector.annotation.UiHidden;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Trackable{
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Trackable(){
		
	}
	public Trackable(String name){
		this.setName(name);
	}
	@UiHidden
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(unique = true, nullable = false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return "id: " + id + "," + "name: " + name ;
	}
	
	
	/** update itself using the new object
	 * @param o
	 */
	public void update(Trackable o){
		this.setName(o.name);
	}

	@Override
	public boolean equals(Object obj) {
		if(((Trackable)obj).getId() == this.getId()){
			return true;
		}
		return false;
	}

	private Timestamp creationDateTime;
	private Timestamp modificationDateTime;

	private Long creationUserId;
	private Long modificationUserId;

	private Long databaseVersionId;

	public int compareTo(Trackable o) {
		return this.getName().compareToIgnoreCase(o.getName());
	}
	
	public int compareById(Trackable o){
		return (int)(this.getId() - o.getId());
	}
	
}
