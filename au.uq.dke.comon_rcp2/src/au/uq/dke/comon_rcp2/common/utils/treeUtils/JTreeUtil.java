package au.uq.dke.comon_rcp2.common.utils.treeUtils;

import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import au.uq.dke.comon_rcp2.ontology.graph.model.node.BasicGraphNode_back;


public class JTreeUtil {

	public static void filterNode(JTree jTree, String regex) {
		if (regex.length() <= 5 && !regex.endsWith(" ")) {
				return;
		}
		
		if(regex.contains(" ")) {
			regex = regex.trim();
		}
		
		DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) jTree
				.getModel().getRoot();

		jTree.collapsePath(new TreePath(rootNode.getPath()));
		List<DefaultMutableTreeNode> descendants = MutableTreeNodeUtil
				.getDesendants(rootNode);

		for (DefaultMutableTreeNode node : descendants) {

			if (((BasicGraphNode_back) node.getUserObject()).getText()
					.startsWith(regex)) {
				// jTree.scrollPathToVisible(new TreePath(node.getPath()));
				jTree.setSelectionPath(new TreePath(node.getPath()));
			}
		}
	}
}
