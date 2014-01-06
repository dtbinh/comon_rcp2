package au.uq.dke.comon_rcp2.ontology.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import au.uq.dke.comon_rcp2.ontology.graph.model.facade.INodeUserObject;

@Entity
public class OntologyClass extends OntologyItem implements INodeUserObject{
	
	public String name = "hehe";
	
	public boolean isRoot = false;;
	
	public boolean isBranch = false;
	
	public boolean hasChildren = false;;
	
	public OntologyClass branchNode = null;
	
	public int level;
	
	public Set <OntologyClass> children = null;
	
	
	
	public OntologyClass(){
		
	}
	
	public OntologyClass(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	@Transient
	public String getText() {
		// TODO Auto-generated method stub
		return this.getName();
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public boolean isBranch() {
		return isBranch;
	}

	public void setBranch(boolean isBranch) {
		this.isBranch = isBranch;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	@Transient
	public OntologyClass getBranchNode() {
		return branchNode;
	}

	public void setBranchNode(OntologyClass branchNode) {
		this.branchNode = branchNode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Transient
	public Set<OntologyClass> getChildren() {
		return children;
	}

	public void setChildren(Set<OntologyClass> children) {
		this.children = children;
	}

}
