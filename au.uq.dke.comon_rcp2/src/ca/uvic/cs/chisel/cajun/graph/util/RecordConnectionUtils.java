package ca.uvic.cs.chisel.cajun.graph.util;

import uk.ac.manchester.cs.bhig.util.MutableTree;
import au.uq.dke.comon_rcp2.data.model.data.BasicRecord;
import au.uq.dke.comon_rcp2.ontology.graph.model.node.BasicGraphNode;

public class RecordConnectionUtils {
	public static String getBranchNodeName(BasicGraphNode graphNode) {

		MutableTree branchTreeNode = (MutableTree) graphNode.getTreeNode()
				.getPathToRoot().get(1);
		return ((BasicGraphNode) branchTreeNode.getUserObject()).getName();

	}

	public static String getTableNameByNodeName(String nodeName) {
		String tableName = nodeName.replace(" of ", "Of").replace(" ", "")
				.replace("&", "And").replace("-", "");
		return tableName;
	}

	public static Class<BasicRecord> getRecordType(BasicGraphNode graphNode) {
		String branchName = getBranchNodeName(graphNode);
		String parentPackageName = StringUtils
				.getTableNameByNodeName(branchName);
		String modelClassNamePrefix = "au.uq.dke.comon_rcp2.data.model.data."
				+ StringUtils.toFirstLetterLowerCase(parentPackageName) + ".";

		String tableName = StringUtils.getTableNameByNodeName(graphNode
				.getText());

		if (tableName.equalsIgnoreCase("RiskIdentification")
				|| tableName.equalsIgnoreCase("RiskAnalysis")
				|| tableName.equals("RiskEvaluation")
				|| tableName.equals("ComplianceManagement")) {
			return null;
		}

		if (tableName.equalsIgnoreCase("ProcessActivity")) {
			int a = 1;
		}

		String fullClassName = modelClassNamePrefix + tableName;
		Class recordType = null;
		try {
			recordType = Class.forName(fullClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (recordType != null) {
			int a = 1;
		}
		return recordType;

	}

}
