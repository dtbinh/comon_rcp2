package au.uq.dke.comon_rcp2.ontology.model;

import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import au.uq.dke.comon_rcp2.ontology.model.persistence.IOntologyModelService;
import au.uq.dke.comon_rcp2.ontology.model.persistence.OntologyModelServiceMockImpl;

/**
 * @author uqwwan10 initially generate info like level, children, branch and so
 *         on by parse the ontology class and their relations
 */
public class OntologyInfoUtil {

	private static IOntologyModelService ontologyModelService = OntologyModelServiceMockImpl
			.getInstance();

	private static Collection<OntologyClass> ontologyClasses = ontologyModelService
			.getAllOntologyClasses();
	private static Collection<OntologyRelation> ontologyRelations = ontologyModelService
			.getAllOntologyRelations();
	private static Collection<RelationType> relationTypes = ontologyModelService
			.getAllRelationTypes();

	private static Collection<OntologyClass> roots = null;

	private static Collection<DefaultMutableTreeNode> trees = null;

	public static void parseOntology() {

		// build trees
		roots = getRoots();

		for (OntologyClass root : roots) {
			DefaultMutableTreeNode tree = generateMutableTree(root);
			trees.add(tree);
		}

		// set values in ontologyClass

		for (DefaultMutableTreeNode tree : trees) {
			for (@SuppressWarnings("unchecked")
			Enumeration<DefaultMutableTreeNode> e = tree
					.breadthFirstEnumeration(); e.hasMoreElements();) {
				DefaultMutableTreeNode node = e.nextElement();
				OntologyClass ontologyClass = (OntologyClass) node
						.getUserObject();

				ontologyClass.setRoot(node.isRoot());
				ontologyClass.setHasChildren(!node.isLeaf());
				ontologyClass.setLevel(node.getLevel());
				ontologyClass.getChildren().addAll(node.get)

			}
		}

	}

	private static Collection<OntologyClass> getRoots() {
		return null;
	}

	private static DefaultMutableTreeNode generateMutableTree(OntologyClass root) {

		DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode(root);

		generateMutableTreeRecursively(rootTreeNode);

		return rootTreeNode;

	}

	private static void generateMutableTreeRecursively(
			DefaultMutableTreeNode rootTreeNode) {
		Collection<OntologyClass> children = getChildren((OntologyClass) rootTreeNode
				.getUserObject());
		for (OntologyClass child : children) {
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
			rootTreeNode.add(childNode);
			generateMutableTreeRecursively(childNode);
		}
	}

	private static Collection<OntologyClass> getChildren(OntologyClass parent) {
		return null;
	}

}
