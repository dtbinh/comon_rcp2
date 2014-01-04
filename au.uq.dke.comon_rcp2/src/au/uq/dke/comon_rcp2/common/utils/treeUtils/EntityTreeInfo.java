package au.uq.dke.comon_rcp2.common.utils.treeUtils;


import java.util.List;

import org.semanticweb.owlapi.model.OWLEntity;

import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import uk.ac.manchester.cs.bhig.util.Tree;

public class EntityTreeInfo {
	
	private OWLEntity owlEntity;
	
	/**
	 * the tree node this entity related with
	 */
	private Tree treeNode;
	/**
	 * refer to the tree branch it belongs, so as to get the color
	 */
	private OWLEntity branchObject;
	/**
	 * the order of this node among its siblings to arrange the order on the graph
	 */
	private int siblingNumber = 0;
	
	/**
	 * the level of it in the tree it belongs. used to determine the size of the node and the arc
	 */
	private int level = 0;
	
	private GraphNode graphNode;
	
	public OWLEntity getOwlEntity() {
		return owlEntity;
	}
	public void setOwlEntity(OWLEntity owlEntity) {
		this.owlEntity = owlEntity;
	}
	public Tree getTreeNode() {
		return treeNode;
	}
	public void setTreeNode(Tree tree) {
		this.treeNode = tree;
	}
	public OWLEntity getBranchObject() {
		return branchObject;
	}
	public void setBranchObject(OWLEntity branchObject) {
		this.branchObject = branchObject;
	}
	public int getSiblingNumber() {
		return siblingNumber;
	}
	public void setSiblingNumber(int siblingNumber) {
		this.siblingNumber = siblingNumber;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public GraphNode getGraphNode() {
		return graphNode;
	}
	public void setGraphNode(GraphNode graphNode) {
		this.graphNode = graphNode;
	}
	public EntityTreeInfo(OWLEntity owlEntity, Tree treeNode, OWLEntity branchObject, int level, int siblingNumber){
		this.owlEntity = owlEntity;
		this.treeNode = treeNode;
		this.branchObject = branchObject;
		this.level = level;
		this.siblingNumber = siblingNumber;
	}
	

}
