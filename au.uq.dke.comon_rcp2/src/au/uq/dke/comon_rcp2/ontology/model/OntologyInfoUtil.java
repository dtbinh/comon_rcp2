package au.uq.dke.comon_rcp2.ontology.model;

import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import uk.ac.manchester.cs.bhig.util.MutableTree;
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

	private static Collection<MutableTree> trees = null;

	public static void parseOntology() {

		// build trees
		roots = getRoots();

		for (OntologyClass root : roots) {
			MutableTree tree = generateMutableTree(root);
			trees.add(tree);
		}

		// set values in ontologyClass

		for (MutableTree tree : trees) {
			for (@SuppressWarnings("unchecked")
			Enumeration<MutableTree> e = tree
					.breadthFirstEnumeration(); e.hasMoreElements();) {
				MutableTree node = e.nextElement();
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

	private static MutableTree generateMutableTree(OntologyClass root) {

		MutableTree rootTreeNode = new MutableTree(root);

		generateMutableTreeRecursively(rootTreeNode);

		return rootTreeNode;

	}

	private static void generateMutableTreeRecursively(
			MutableTree rootTreeNode) {
		Collection<OntologyClass> children = getChildren((OntologyClass) rootTreeNode
				.getUserObject());
		for (OntologyClass child : children) {
			MutableTree childNode = new MutableTree(child);
			rootTreeNode.add(childNode);
			generateMutableTreeRecursively(childNode);
		}
	}

	private static Collection<OntologyClass> getChildren(OntologyClass parent) {
		return null;
	}

}
