package au.uq.dke.comon_rcp2.ontology.graph.model.facade;

import javax.persistence.ManyToOne;

import au.uq.dke.comon_rcp2.ontology.model.OntologyClass;

public interface INodeUserObject {

	public String getText();

	public boolean isRoot();

	public void setRoot(boolean isRoot);

	public boolean isBranch();

	public void setBranch(boolean isBranch);

	public boolean isHasChildren();

	public void setHasChildren(boolean hasChildren);

	public OntologyClass getBranchNode();

	public void setBranchNode(OntologyClass branchNode);

	public int getLevel();

	public void setLevel(int level);

}
