package au.uq.dke.comon_rcp2.data.utils;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import au.uq.dke.comon_rcp2.common.CustomRuntimeException;
import au.uq.dke.comon_rcp2.ontology.model.OntologyClass;
import ca.uvic.cs.chisel.cajun.graph.node.DefaultGraphNode;

public class DatabaseUtils {

	static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	private static SessionFactory sessionFactory = ctx
			.getBean(SessionFactory.class);
	private static Session session = sessionFactory.openSession();

	public static Session getSession() {
		return session;
	}

	public static Object findById(Class recordType, Serializable id) {
		return session.get(recordType, id);

	}

	public static Object findByUniqueName(Class recordType, String name) {
		return session.createCriteria(recordType)
				.add(Restrictions.eq("name", name)).uniqueResult();
	}
	

	public static String getBranchNodeName(DefaultGraphNode graphNode) {

		for (Object record : findAll(OntologyClass.class)) {
			OntologyClass ontologyClass = (OntologyClass) record;
			if (ontologyClass.getId() == graphNode.getBranchId()) {
				return ontologyClass.getName();
			}
		}

		throw new CustomRuntimeException("no branch name found");

	}

	public static List findAll(Class recordType) {
		return session.createCriteria(recordType).list();
	}
	
	public static void deleteAll(Class recordType){
		Query query = session.createQuery("delete from " + recordType.getSimpleName());
		query.executeUpdate();
	}

	public static void importFromOldTable() {
		SessionFactory sf = ctx.getBean(SessionFactory.class);

		Session session = sf.openSession();

		session.beginTransaction();

		// List classes = session.createCriteria(OntologyClass2.class).list();
		//
		// Query query =
		// session.createSQLQuery("select ontologyClass.id as id, trackable.name, level, iri, discription, branchid from ontologyClass inner join trackable on ontologyClass.id=trackable.id");
		// // Query query =
		// session.createSQLQuery("select * from ontologyClass inner join trackable where ontologyClass.id = trackable.id").addEntity(OntologyClass2.class);
		//
		// List result = query.list();
		return;

	}

	public static void main(String[] args) {
		DatabaseUtils.importFromOldTable();
	}
}
