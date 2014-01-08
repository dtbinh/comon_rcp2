package au.uq.dke.comon_rcp2.ontology.graph.model;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import uk.ac.manchester.cs.bhig.util.MutableTree;
import uk.ac.manchester.cs.bhig.util.Tree;
import au.uq.dke.comon_rcp2.ontology.OntologyConstants;
import au.uq.dke.comon_rcp2.ontology.graph.model.arc.BasicGraphArc;
import au.uq.dke.comon_rcp2.ontology.graph.model.facade.IArcUserObject;
import au.uq.dke.comon_rcp2.ontology.graph.model.facade.INodeUserObject;
import au.uq.dke.comon_rcp2.ontology.graph.model.node.BasicGraphNode;
import au.uq.dke.comon_rcp2.ontology.model.OntologyClass;
import au.uq.dke.comon_rcp2.ontology.model.OntologyRelation;
import au.uq.dke.comon_rcp2.ontology.model.OntologyRelationType;
import au.uq.dke.comon_rcp2.ontology.model.persistence.IOntologyModelService;
import au.uq.dke.comon_rcp2.ontology.model.persistence.OntologyModelServiceMockImpl;
import ca.uvic.cs.chisel.cajun.graph.DefaultGraphModel;
import ca.uvic.cs.chisel.cajun.graph.GraphModelListener;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;

public class OntologyGraphModelImpl extends DefaultGraphModel implements
		IOntologyGraphModel{
	
	private static OntologyGraphModelImpl instance = null;

	private MutableTree rootTreeNode = null;
	
	private BasicGraphNode rootGraphNode = null;


	private static IOntologyModelService ontologyModelService = OntologyModelServiceMockImpl
			.getInstance();

	private static Collection<OntologyRelation> ontologyRelations = ontologyModelService
			.getAllOntologyRelations();

	private OntologyGraphModelImpl(){
		super();
		populateData();
	}
	
	public static OntologyGraphModelImpl getInstance(){
		if(instance == null){
			instance = new OntologyGraphModelImpl();
		}
		
		return instance;
	}
	
	private void populateData() {
	    OntologyClass srcObject = new OntologyClass("src");
	    OntologyClass dstObject = new OntologyClass("dst");
	    OntologyRelationType relType = new OntologyRelationType("has subclass");
	    
	    OntologyRelation rel = new OntologyRelation (srcObject, dstObject, relType);
	    
	    IGraphNode srcNode = this.addNode(srcObject);
	    //IGraphNode dstNode = this.addNode(dstObject);
	    //this.addArc(rel, srcNode, dstNode);
	    this.generateTreeInfo();

		
	}

	public MutableTree generateTreeInfo() {
		for (IGraphNode graphNode : this.getAllNodes()) {
			if (graphNode instanceof BasicGraphNode) {

				BasicGraphNode basicGraphNode = (BasicGraphNode) graphNode;
				MutableTree treeParent = (MutableTree) basicGraphNode
						.getTreeNode();
				
				Collection<IGraphArc> outgoingGraphArcs = basicGraphNode.getArcs(false, true);
				for(IGraphArc graphArc : outgoingGraphArcs){
					OntologyRelation relation = (OntologyRelation) graphArc.getUserObject();
					if(relation.getRelationType().getType().equalsIgnoreCase(OntologyConstants.SUB_CLASS_RELTYPE)){
						BasicGraphNode destinationGraphNode = (BasicGraphNode) graphArc.getDestination();
						Tree destinationTreeNode =  destinationGraphNode.getTreeNode();
						
						treeParent.addChild((MutableTree) destinationTreeNode);
					}
				}
				

			}

		}
		
		this.rootTreeNode = (MutableTree) ((BasicGraphNode)this.getAllNodes().toArray()[0]).getTreeNode().getRoot();
		this.rootGraphNode = (BasicGraphNode) this.getNode(rootTreeNode.getUserObject());
		return rootTreeNode;
	}

	
	private Collection<OntologyClass> getOntologyClassChildren(
			OntologyClass parentOntologyClass) {

		Collection<OntologyClass> children = new ArrayList<OntologyClass>();
		for (OntologyRelation ontologyRelation : ontologyRelations) {
			if (ontologyRelation.getSrcClass() == parentOntologyClass
					&& ontologyRelation.getRelationType().getType()
							.equalsIgnoreCase(OntologyConstants.SUB_CLASS_RELTYPE)) {
				
				children.add(ontologyRelation.getDstClass());

			}
		}

		return null;
	}

	@Override
	public IGraphArc addArc(IArcUserObject userObject, IGraphNode src,
			IGraphNode dest) {
		return super.addArc(userObject, src, dest);
	}

	@Override
	public IGraphNode addNode(INodeUserObject userObject) {
		return super.addNode(userObject);
	}

	@Override
	public void removeNode(INodeUserObject userObject) {
		super.removeNode(userObject);

	}

	@Override
	public void removeArc(IArcUserObject userObject) {
		super.removeArc(userObject);
	}

	@Override
	public void clear() {
		super.clear();
	}

	@Override
	public IGraphNode getNode(INodeUserObject userObject) {
		return super.getNode(userObject);
	}

	@Override
	public boolean containsNode(IGraphNode node) {
		return super.containsNode(node);
	}

	@Override
	public IGraphArc getArc(IArcUserObject userObject) {
		return super.getArc(userObject);
	}

	@Override
	public boolean containsArc(IGraphArc arc) {
		return super.containsArc(arc);
	}

	@Override
	public Collection<IGraphNode> getConnectedNodes(
			INodeUserObject nodeUserObject) {
		return super.getConnectedNodes(nodeUserObject);
	}

	@Override
	public IGraphNode getSourceNode(IArcUserObject arcUserObject) {
		return super.getSourceNode(arcUserObject);
	}

	@Override
	public IGraphNode getDestinationNode(IArcUserObject arcUserObject) {
		return super.getDestinationNode(arcUserObject);
	}

	@Override
	public Collection<IGraphNode> getVisibleNodes() {
		return super.getVisibleNodes();
	}

	@Override
	public Collection<IGraphArc> getVisibleArcs() {
		return super.getVisibleArcs();
	}

	@Override
	public Collection<Object> getArcTypes() {
		return super.getArcTypes();
	}

	@Override
	public void addGraphModelListener(GraphModelListener listener) {
		super.addGraphModelListener(listener);
	}

	@Override
	public void removeGraphModelListener(GraphModelListener listener) {
		super.removeGraphModelListener(listener);
	}

	@Override
	public void arrangeArcs(IGraphNode src, IGraphNode dest) {
		super.arrangeArcs(src, dest);
	}

	@Override
	public Collection<IGraphNode> getAllNodes() {
		return super.getAllNodes();
	}

	@Override
	public Collection<IGraphArc> getAllArcs() {
		return super.getAllArcs();
	}

	@Override
	public boolean containsNode(INodeUserObject userObject) {
		IGraphNode graphNode = new BasicGraphNode(userObject);
		return super.containsNode(graphNode);
	}

	@Override
	public boolean containsArc(IArcUserObject userObject) {
		IGraphArc graphArc = new BasicGraphArc(userObject, null, null);
		return super.containsArc(graphArc);
	}

	// ITreeContentProvider

	@Override
	public Collection<IGraphNode> getChildren(
			INodeUserObject parentNodeUserObject) {
		
		BasicGraphNode graphNode = (BasicGraphNode) this.getNode(parentNodeUserObject);
		Collection<Tree> childrenTreeNode = graphNode.getTreeNode().getChildren();
		Collection<IGraphNode> childrenGraphNode = new ArrayList<IGraphNode>();
		for(Tree childTreeNode : childrenTreeNode){
			IGraphNode childGraphNode = this.getNode(childTreeNode.getUserObject());
			childrenGraphNode.add(childGraphNode);
		}
		return childrenGraphNode;
	}

	public MutableTree getRootTreeNode() {
		return rootTreeNode;
	}

	public BasicGraphNode getRootGraphNode() {
		return rootGraphNode;
	}


}
