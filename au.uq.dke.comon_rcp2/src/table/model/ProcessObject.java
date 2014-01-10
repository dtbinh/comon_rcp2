package table.model;

import java.util.HashSet;
import java.util.Set;

public class ProcessObject extends Parent {
	private static int number = 0;
	
	public ProcessObject(){
		name = "object " + number ++;
		level = "level " + (10 - number);
		
		for(int i = 0; i < 2; i++){
			this.associatedProcessRules.add(new ProcessRule());
		}
	}
	
	
	public static int getNumber() {
		return number;
	}

	public static void setNumber(int number) {
		ProcessObject.number = number;
	}


	private Set<ProcessRule> associatedProcessRules = new HashSet<ProcessRule>();

	public Set<ProcessRule> getAssociatedProcessRules() {
		return associatedProcessRules;
	}

	public void setAssociatedProcessRules(Set<ProcessRule> associatedProcessRules) {
		this.associatedProcessRules = associatedProcessRules;
	}

	private String name = "object " + number ++;
	
	private String level = "level " + (10 - number);
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
