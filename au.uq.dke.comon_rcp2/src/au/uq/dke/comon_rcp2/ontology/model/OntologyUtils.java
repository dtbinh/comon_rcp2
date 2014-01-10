package au.uq.dke.comon_rcp2.ontology.model;

public class OntologyUtils {
	public static String getNameFromIRI(String iri){
		String name = iri.substring(iri.lastIndexOf("#") + 1, iri.length());
		return name;
	}

}
