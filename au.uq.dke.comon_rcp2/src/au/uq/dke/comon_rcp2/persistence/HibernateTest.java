package au.uq.dke.comon_rcp2.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import au.uq.dke.comon_rcp2.ontology.model.OntologyClass;
import au.uq.dke.comon_rcp2.ontology.model.OntologyRelation;
import au.uq.dke.comon_rcp2.ontology.model.RelationType;
 
public class HibernateTest {
 
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
 
        System.out.println("Hibernate One-To-One example (Annotation)");
         
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
 
        OntologyClass cls1  = new OntologyClass("class1");
         
        OntologyClass cls2 = new OntologyClass("class2");
        
        RelationType type1 = new RelationType("has");
        
        OntologyRelation relation1 = new OntologyRelation(cls1, cls2, type1);
        
         
        session.save(cls1);
        session.save(cls2);
        session.save(type1);
        session.save(relation1);
 
         
        List<OntologyClass> classes = session.createQuery("from OntologyClass").list();
        for (OntologyClass cls : classes) {
            System.out.println(cls.getName());
        }
 
        session.getTransaction().commit();
        session.close();
 
    }
}