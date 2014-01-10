package au.uq.dke.comon_rcp2.data.model.data.cultureManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ChangeManagement extends ComplianceDriver {

	private String timeLine;
	private String targetGroup;

	public void setTargetGroup(String targetGroup) {
		this.targetGroup = targetGroup;
	}

	public ChangeManagement(String name) {
		super(name);
	}

	public ChangeManagement(){
		
	}
	public String getTimeLine() {
		return timeLine;
	}

	public void setTimeLine(String timeLine) {
		this.timeLine = timeLine;
	}

	public String getTargetGroup() {
		return targetGroup;
	}
	
	

}
