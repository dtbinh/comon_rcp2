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

	private static Collection<OntologyClass> roots = null;

	private static Collection<MutableTree> trees = null;


	private static Collection<OntologyClass> getRoots() {
		return null;
	}
	
	public static String getNameFromIRI(String iri){
		String name = iri.substring(iri.lastIndexOf("#") + 1, iri.length());
		return name;
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
