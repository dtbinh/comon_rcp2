package au.uq.dke.comon_rcp2.database.model.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.metawidget.inspector.annotation.UiComesAfter;
import org.metawidget.inspector.annotation.UiHidden;
import org.metawidget.inspector.annotation.UiLarge;

import au.uq.dke.comon_rcp2.database.model.Trackable;
import au.uq.dke.comon_rcp2.database.utils.DatabaseUtils;
import au.uq.dke.comon_rcp2.database.utils.ReflectionUtils;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class BasicRecord extends Trackable implements Comparable<BasicRecord>{

	@Column(columnDefinition = "TEXT")
	private String discription = "";
	
	public BasicRecord(){
		
	}
	public BasicRecord(String name){
		super(name);
	}

	@UiComesAfter ("name")
	@UiLarge
	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	@Override
	public int compareTo(BasicRecord o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@UiHidden
	public String getfieldNameByClass(){
		return ReflectionUtils.getfieldNameByClass(this.getClass());
	}
	
	public Serializable persist(){
		return DatabaseUtils.getSession().save(this);
	}
	
	/**inherit classes should rewrite this
	 * 
	 * @return
	 */
	@UiHidden
	public boolean isTable(){
		return false;
	}
	
	public String toString(){
		return this.getName();
	}

}
