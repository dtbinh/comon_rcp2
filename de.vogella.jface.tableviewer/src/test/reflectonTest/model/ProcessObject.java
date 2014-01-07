package test.reflectonTest.model;

import java.util.HashSet;
import java.util.Set;

public class ProcessObject extends Parent {
	private static int increment = 0;
	
	public static int getIncrement() {
		return increment;
	}

	public static void setIncrement(int increment) {
		ProcessObject.increment = increment;
	}

	private String name = "object " + increment ++;
	
	private String level = "level " + (10 - increment);
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
