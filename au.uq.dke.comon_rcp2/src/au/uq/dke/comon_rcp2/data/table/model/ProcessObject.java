package au.uq.dke.comon_rcp2.data.table.model;

import java.util.HashSet;
import java.util.Set;

public class ProcessObject extends Parent {
	private static int number = 0;
	
	public ProcessObject(){
		name = "object " + number ++;
		level = "level " + (10 - number);
		
//		for(int i = 0; i < 2; i++){
//			this.associatedProcessRules.add(new ProcessRule());
//		}
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

	private String name;
	
	private String level;
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
