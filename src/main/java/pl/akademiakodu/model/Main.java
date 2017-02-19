package pl.akademiakodu.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Lenovo on 2016-12-06.
 */
public class Main {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        Contact contact = new Contact("Zdzisław", "Python", "python@o2.pl");
//        Contact contact = new Contact("Adam", "Kowalski", "aa@wp.pl", 1122121l);
        save(contact);
        Contact contactFinded = findContactById(1);
        System.out.println(contactFinded);
        delete(contact);
//        System.out.println(fetchAllContacts());
    }
/*
    private static List<Contact> fetchAllContacts() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
// UPDATED: Create CriteriaQuery
        CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);
// UPDATED: Specify criteria root
        criteria.from(Contact.class);
// UPDATED: Execute query
        List<Contact> contacts = session.createQuery(criteria).getResultList();
// Close the session
        session.close();
        return contacts;
    }
*/
    private static void save(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(contact);
        session.getTransaction().commit();
        session.close();
    }

    private static Contact findContactById(int id) {
        Session session = sessionFactory.openSession();
        Contact contact = session.get(Contact.class, id);
        session.close();
        return contact;
    }

    private  static void delete(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(contact);
        session.getTransaction().commit();
        session.close();
    }
}
