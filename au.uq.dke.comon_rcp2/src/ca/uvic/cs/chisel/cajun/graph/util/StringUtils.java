package ca.uvic.cs.chisel.cajun.graph.util;

public class StringUtils {
	public static String toFirstLetterLowerCase(String string) {
		return string.substring(0, 1).toLowerCase()
				+ string.substring(1, string.length());
	}

	public static String getTableNameByNodeName(String nodeName) {
		String tableName = nodeName.replace(" of ", "Of").replace(" ", "")
				.replace("&", "And").replace("-", "");
		return tableName;
	}

	public static String getUserFriendlyName(String name) {
		String userFriendlyName = name.replaceAll("([a-z])([A-Z][a-z])",
				"$1 $2").replaceAll("And", "&");
		return userFriendlyName;
	}

	public static void main(String[] args) {
		getUserFriendlyName("RiskIdentificationAndAssessment");
	}

}
