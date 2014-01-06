package au.uq.dke.comon_rcp2.ontology.graph.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

import au.uq.dke.comon_rcp2.EntryPoint;
import ca.uvic.cs.chisel.cajun.graph.DefaultGraphModel;
import ca.uvic.cs.chisel.cajun.graph.arc.GraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.DefaultGraphNode;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;

public class GraphModelBak extends DefaultGraphModel {

	OntologyModelListener databaseModelListener = new OntologyModelListener() {

		@Override
		public void databaseCleared() {
			// TODO Auto-generated method stub
			super.databaseCleared();
		}

		@Override
		public void databaseRelationshipAdded(OntologyRelationship relationship) {
			// TODO Auto-generated method stub
			super.databaseRelationshipAdded(relationship);
			GraphModelBak.this.createArc(relationship);
		}

		@Override
		public void databaseRelationshipUpdated(
				OntologyRelationship relationship) {
			// TODO Auto-generated method stub
			super.databaseRelationshipUpdated(relationship);
			// NewGraphModel.this.u(relationship);
		}

		@Override
		public void databaseRelationshipRemoved(
				OntologyRelationship relationship) {
			// TODO Auto-generated method stub
			super.databaseRelationshipRemoved(relationship);
			GraphModelBak.this.removeArc(relationship);
		}

		@Override
		public void databaseClassAdded(OntologyClass cls) {
			// TODO Auto-generated method stub
			super.databaseClassAdded(cls);
			GraphModelBak.this.createNode(cls);
		}

		@Override
		public void databaseClassUpdated(OntologyClass cls) {
			// TODO Auto-generated method stub
			super.databaseClassUpdated(cls);
			GraphModelBak.this.updateNode(cls);
		}

		@Override
		public void databaseClassRemoved(OntologyClass cls) {
			// TODO Auto-generated method stub
			super.databaseClassRemoved(cls);
			GraphModelBak.this.removeNode(cls);
		}

		@Override
		public void databaseAxiomAdded(Object axiom) {
			// TODO Auto-generated method stub
			super.databaseAxiomAdded(axiom);
		}

		@Override
		public void databaseAxiomUpdated(Object axiom) {
			// TODO Auto-generated method stub
			super.databaseAxiomUpdated(axiom);
		}

		@Override
		public void databaseAxiomRemoved(Object axiom) {
			// TODO Auto-generated method stub
			super.databaseAxiomRemoved(axiom);
		}

	};
	EntryPoint entryPoint = new EntryPoint();
	private static OntologyClassService ontologyClassService = EntryPoint
			.getOntologyClassService();
	private static OntologyAxiomService ontologyAxiomService = EntryPoint
			.getOntologyAxiomService();
	private static OntologyRelationshipService ontologyRelationshipService = EntryPoint
			.getOntologyRelationshipService();

	private Map<Object, GraphNode> nodes;

	private Map<Object, GraphArc> arcs;

	public GraphModelBak() {
	}

	public void init() {
		this.nodes = super.getNodes();
		this.arcs = super.getArcs();
		this.loadNodesFromDB();
		this.loadArcsFromDB();

	}

	// CRUD of node
	// tested
	public GraphNode findGraphNode(OntologyClass ontologyClass) {
		for (Map.Entry<Object, GraphNode> entry : nodes.entrySet()) {
			if (((OntologyClass) entry.getKey()).equals(ontologyClass)) {
				return entry.getValue();
			}
		}

		return null;
	}

	// tested
	private void loadNodesFromDB() {

		List<OntologyClass> ontologyClassList = this.ontologyClassService
				.findAll();
		for (OntologyClass ontologyClass : ontologyClassList) {
			super.addNode(ontologyClass, ontologyClass.getName(), null);
		}
	}

	private void createNode(OntologyClass ontologyClass) {
		super.addNode(ontologyClass, ontologyClass.getName(), null);
	}

	private void removeNode(OntologyClass ontologyClass) {
		GraphNode graphNode = this.findGraphNode(ontologyClass);
		if (graphNode != null) {
			super.removeArc(graphNode.getUserObject());
		}
	}

	private void updateNode(OntologyClass ontologyClass) {
		DefaultGraphNode graphNode = (DefaultGraphNode) this
				.findGraphNode(ontologyClass);
		if (graphNode != null) {
			graphNode.setName(ontologyClass.getName());
		}

	}

	// CRUD of arc

	// tested
	private GraphArc findGraphArc(OntologyRelationship ontologyRelationship) {
		for (Map.Entry<Object, GraphArc> entry : arcs.entrySet()) {
			if (((OntologyRelationship) entry.getKey()).getId() == ontologyRelationship
					.getId()) {
				return entry.getValue();
			}

		}

		return null;
	}

	private void loadArcsFromDB() {

		List<OntologyRelationship> ontologyRelationshipList = this.ontologyRelationshipService
				.findAll();
		for (OntologyRelationship ontologyRelationship : ontologyRelationshipList) {
			createArc(ontologyRelationship);
		}

	}

	public GraphArc createArc(OntologyRelationship ontologyRelationship) {
		OntologyClass sourceOntologyClass = this.ontologyRelationshipService
				.findSourceOntologyClass(ontologyRelationship);
		OntologyClass destinationOntologyClass = this.ontologyRelationshipService
				.findDestinationOntologyClass(ontologyRelationship);
		GraphNode sourceNode = findGraphNode(sourceOntologyClass);
		GraphNode destinationNode = findGraphNode(destinationOntologyClass);
		return super.addArc(ontologyRelationship, sourceNode, destinationNode,
				ontologyRelationship.getName());

	}

	public void removeArc(OntologyRelationship ontologyRelationship) {
		GraphArc arc = this.findGraphArc(ontologyRelationship);
		if (arc != null) {
			super.removeArc(arc.getUserObject());
		}
	}

	/*
	 * CRUD of arcTypes, to be added or don't need? since arcTypes are updated
	 * by tranverse all arcs and get their types for database, axiom crud is
	 * needed, but for model, we may not need it
	 */

	// other methods
	
	
	public List<GraphNode> getChildren(GraphNode graphNode) {

		List<GraphNode> childrenGraphNodeList = getGraphNodesFromClasses(this.ontologyRelationshipService
				.findChildren((OntologyClass) graphNode.getUserObject()));
		return childrenGraphNodeList;
	}
	
	public boolean isParentChildRelation(GraphNode graphNodeA, GraphNode graphNodeB){
		List<GraphNode> childrenOfA = getChildren(graphNodeA);
		List<GraphNode> childrenOfB = getChildren(graphNodeB);
		for(GraphNode child : childrenOfA){
			if(child.equals(graphNodeB)){
				return true;
			}
		}
		
		for(GraphNode child : childrenOfB){
			if(child.equals(graphNodeA)){
				return true;
			}
		}
		
		return false;
	}

	public List<GraphNode> getRelationSrcNodes(GraphNode graphNode) {
		List<OntologyClass> relationSrcClassList = this.ontologyRelationshipService
				.findRelSrcNeighbourClasses((OntologyClass) graphNode
						.getUserObject());
		List<GraphNode> graphNodeList = getGraphNodesFromClasses(relationSrcClassList);
		return graphNodeList;
	}

	public List<GraphNode> getRelationDestNodes(GraphNode graphNode) {
		List<OntologyClass> relationDestClassList = this.ontologyRelationshipService
				.findRelDestNeighbourClasses((OntologyClass) graphNode
						.getUserObject());
		List<GraphNode> graphNodeList = getGraphNodesFromClasses(relationDestClassList);
		return graphNodeList;
	}

	public List<GraphNode> getDesendants(GraphNode graphNode) {
		List<OntologyClass> desendantsClassList = this.ontologyRelationshipService
				.findDesendants((OntologyClass) graphNode.getUserObject());
		List<GraphNode> graphNodeList = getGraphNodesFromClasses(desendantsClassList);
		return graphNodeList;
	}

	public GraphNode getBranchGraphNode(GraphNode graphNode) {
		Long branchId = ((OntologyClass) graphNode.getUserObject())
				.getBranchId();
		OntologyClass branchClass = this.ontologyClassService
				.findById(branchId);
		GraphNode branchGraphNode = this.findGraphNode(branchClass);
		return branchGraphNode;
	}

	private List<GraphNode> getGraphNodesFromClasses(
			List<OntologyClass> classList) {
		List<GraphNode> graphNodeList = new ArrayList<GraphNode>();
		for (OntologyClass cls : classList) {
			GraphNode node = this.findGraphNode(cls);
			if (node == null) {
				//throw new NullPointerException(
				//		"can't find peer graphNode by ontology class");
			} else {
				graphNodeList.add(node);
			}
		}
		return graphNodeList;

	}

	// to be tested

	public DefaultMutableTreeNode generateMutableTree() {

		DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode(
				this.findRoot());

		generateMutableTreeRecursively(rootTreeNode);

		return rootTreeNode;

	}

	public GraphNode findRoot() {
		return this.findGraphNode(this.ontologyRelationshipService.findRoot());
	}

	// to be tested

	private void generateMutableTreeRecursively(
			DefaultMutableTreeNode rootTreeNode) {
		List<GraphNode> children = this.getChildren((GraphNode) rootTreeNode
				.getUserObject());
		for (GraphNode child : children) {
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
			rootTreeNode.add(childNode);
			generateMutableTreeRecursively(childNode);
		}
	}

	public void test() {
		loadNodesFromDB();
		loadArcsFromDB();
		return;
	}

	public static void main(String args[]) {
		GraphModelBak ngm = new GraphModelBak();
		ngm.test();

	}

	public void addListeners() {
		// TODO Auto-generated method stub

	}

}
