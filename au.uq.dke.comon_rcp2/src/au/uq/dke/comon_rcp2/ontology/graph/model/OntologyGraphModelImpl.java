package au.uq.dke.comon_rcp2.ontology.graph.model;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import uk.ac.manchester.cs.bhig.util.MutableTree;
import uk.ac.manchester.cs.bhig.util.Tree;
import au.uq.dke.comon_rcp2.ontology.Constants;
import au.uq.dke.comon_rcp2.ontology.graph.model.arc.BasicGraphArc;
import au.uq.dke.comon_rcp2.ontology.graph.model.facade.IArcUserObject;
import au.uq.dke.comon_rcp2.ontology.graph.model.facade.INodeUserObject;
import au.uq.dke.comon_rcp2.ontology.graph.model.node.BasicGraphNode;
import au.uq.dke.comon_rcp2.ontology.model.OntologyClass;
import au.uq.dke.comon_rcp2.ontology.model.OntologyRelation;
import au.uq.dke.comon_rcp2.ontology.model.RelationType;
import au.uq.dke.comon_rcp2.ontology.model.persistence.IOntologyModelService;
import au.uq.dke.comon_rcp2.ontology.model.persistence.OntologyModelServiceMockImpl;
import ca.uvic.cs.chisel.cajun.graph.DefaultGraphModel;
import ca.uvic.cs.chisel.cajun.graph.GraphModelListener;
import ca.uvic.cs.chisel.cajun.graph.arc.IGraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.IGraphNode;

public class OntologyGraphModelImpl extends DefaultGraphModel implements
		IOntologyGraphModel, ITreeContentProvider {

	private MutableTree root = null;

	private static IOntologyModelService ontologyModelService = OntologyModelServiceMockImpl
			.getInstance();

	private static Collection<OntologyClass> ontologyClasses = ontologyModelService
			.getAllOntologyClasses();
	private static Collection<OntologyRelation> ontologyRelations = ontologyModelService
			.getAllOntologyRelations();
	private static Collection<RelationType> relationTypes = ontologyModelService
			.getAllRelationTypes();

	public MutableTree generateTreeInfo() {
		for (IGraphNode graphNode : this.getAllNodes()) {
			if (graphNode instanceof BasicGraphNode) {

				BasicGraphNode basicGraphNode = (BasicGraphNode) graphNode;
				MutableTree treeParent = (MutableTree) basicGraphNode
						.getTreeNode();
				Collection<OntologyClass> ontologyClassChildren = this
						.getOntologyClassChildren((OntologyClass) basicGraphNode
								.getUserObject());

				for (OntologyClass ontologyClassChild : ontologyClassChildren) {
					BasicGraphNode basicGraphNodeChild = (BasicGraphNode) this
							.getNode(ontologyClassChild);
					MutableTree treeChild = (MutableTree) basicGraphNodeChild
							.getTreeNode();

					// add relationship
					treeParent.addChild(treeChild);

				}
			}

		}
		return (MutableTree) ((MutableTree) this.getAllNodes().toArray()[0])
				.getRoot();
	}

	private Collection<OntologyClass> getOntologyClassChildren(
			OntologyClass parentOntologyClass) {

		Collection<OntologyClass> children = new ArrayList<OntologyClass>();
		for (OntologyRelation ontologyRelation : ontologyRelations) {
			if (ontologyRelation.getSrcClass() == parentOntologyClass
					&& ontologyRelation.getRelationType().getType()
							.equalsIgnoreCase(Constants.SUB_CLASS_RELTYPE)) {
				
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
		// TODO search relation table and get all the qualified
		return null;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
		
		return new Object[] {this.getNode(root.getUserObject())};
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof BasicGraphNode){
			BasicGraphNode parentBasicGraphNode = (BasicGraphNode) parentElement;
			Collection<BasicGraphNode> childrenTreeNode = parentBasicGraphNode.getTreeNode().getChildren();
			Collection<BasicGraphNode> childrenBasicGraphNode = new ArrayList();
			for(BasicGraphNode childTreeNode : childrenTreeNode){
				BasicGraphNode childBasicGraphNode = (BasicGraphNode) this.getNode(childTreeNode.getUserObject());
				childrenBasicGraphNode.add(childBasicGraphNode);
			}
			
			return childrenBasicGraphNode.toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object parentElement) {
		if(parentElement instanceof BasicGraphNode){
			BasicGraphNode parentBasicGraphNode = (BasicGraphNode) parentElement;
			return !parentBasicGraphNode.getTreeNode().isLeaf();
		}
		
		return false;
	}

}
