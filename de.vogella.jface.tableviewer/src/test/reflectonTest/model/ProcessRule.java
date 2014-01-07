package test.reflectonTest.model;

public class ProcessRule {
	private static int number = 0;
	private String name;

	public ProcessRule() {

		name = "rule " + number++;

	}

	public static int getNumber() {
		return number;
	}

	public static void setNumber(int number) {
		ProcessRule.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return this.name;
	}

}
