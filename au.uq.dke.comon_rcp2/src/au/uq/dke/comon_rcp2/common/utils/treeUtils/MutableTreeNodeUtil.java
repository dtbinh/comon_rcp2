package au.uq.dke.comon_rcp2.common.utils.treeUtils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class MutableTreeNodeUtil {
	
	public static DefaultMutableTreeNode findTreeNode(DefaultMutableTreeNode root, Object userObject){
		List<DefaultMutableTreeNode> descendants = getDesendants(root);
		for(DefaultMutableTreeNode descendant : descendants){
			if(descendant.getUserObject().equals(userObject)){
				return descendant;
			}
		}
		
		return null;
	}

	public static List<DefaultMutableTreeNode> getDesendants(DefaultMutableTreeNode root) {
		List<DefaultMutableTreeNode> descendantsList = new ArrayList<DefaultMutableTreeNode>();
		descendantsList.add(root);
		getDesendantsRecursively(root, descendantsList);
		return descendantsList;
	}

	private static void getDesendantsRecursively(DefaultMutableTreeNode root,
			List<DefaultMutableTreeNode> desendantsList) {
		// get children
		List<DefaultMutableTreeNode> children = new ArrayList<DefaultMutableTreeNode>();
		for (int i = 0; i < root.getChildCount(); i++) {
			children.add((DefaultMutableTreeNode) root.getChildAt(i));
		}

		// add children to global descendants list
		desendantsList.addAll(children);

		// recursively call itself
		for (DefaultMutableTreeNode child : children) {
			getDesendantsRecursively(child, desendantsList);
		}

	}

}
