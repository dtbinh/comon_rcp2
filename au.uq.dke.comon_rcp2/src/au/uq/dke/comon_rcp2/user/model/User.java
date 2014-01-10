package au.uq.dke.comon_rcp2.user.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import org.metawidget.inspector.annotation.UiHidden;

import au.uq.dke.comon_rcp2.data.model.data.BasicRecord;
import au.uq.dke.comon_rcp2.data.model.data.BasicRecordSet;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class User extends BasicRecord{

	@UiHidden
	@ManyToMany (cascade = CascadeType.PERSIST)
	private Set<UserRole> associatedRoles = new BasicRecordSet<UserRole>();
	
	
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UserRole> getAssociatedRoles() {
		return associatedRoles;
	}

	public void setAssociatedRoles(Set<UserRole> associatedRoles) {
		this.associatedRoles = associatedRoles;
	}
	
	public User(){
		
	}
	
	public User(String name){
		super(name);
	}

	@UiHidden
	@Override
	public String getDiscription() {
		// TODO Auto-generated method stub
		return super.getDiscription();
	}
	
	
}

