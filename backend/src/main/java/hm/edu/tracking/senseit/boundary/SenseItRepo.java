package hm.edu.tracking.senseit.boundary;


import hm.edu.tracking.senseit.entity.SenseitMessage;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

/**
 * This bean offers persistence services for Books
 */
@Stateless
@Transactional(Transactional.TxType.SUPPORTS)
public class SenseItRepo {
    @PersistenceContext(unitName = "db") //Specify persistence context, since there are two separate ones
    private EntityManager em;

    /**
     * Creates an instance of SenseItRepo
     */
    public SenseItRepo() {
        // Empty constructor for Bean instantiation
    }

    /**
     * Create or update a given senseitMessage in the database
     *
     * @param senseitMessage to create or update
     * @return instance of the created or updated senseitMessage
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public SenseitMessage createOrUpdate(SenseitMessage senseitMessage) {
        return em.merge(senseitMessage);
    }

    /**
     * Remove a given senseitMessage from the database
     *
     * @param senseitMessage to be removed
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public void remove(SenseitMessage senseitMessage) {
        em.remove(em.merge(senseitMessage));
    }

    /**
     * Check if the service contains an entity with the given ID
     *
     * @param id to search for
     * @return true if the entity was found, otherwise false
     */
    public boolean hasEntity(long id) {
        return find(id) != null;
    }

    /**
     * Find a book in the database
     *
     * @param id to search for
     * @return book if found, otherwise null
     */
    public SenseitMessage find(Object id) {
        return em.find(SenseitMessage.class, id);
    }

    /**
     * Delete all books from the database
     */
    public void deleteAllBooks() {
        em.createQuery("DELETE FROM SenseitMessage").executeUpdate();
    }

    /**
     * Get all books from the database
     *
     * @return List of books available in the database
     */
    public List<SenseitMessage> getAllBooks() {
        CriteriaQuery<SenseitMessage> cq = em.getCriteriaBuilder().createQuery(SenseitMessage.class);
        Root<SenseitMessage> rootEntry = cq.from(SenseitMessage.class);
        CriteriaQuery<SenseitMessage> all = cq.select(rootEntry);
        TypedQuery<SenseitMessage> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    /**
     * Get the amount of stored books
     *
     * @return amount of stored books
     */
    public Long getBookCount() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(b) FROM SenseitMessage b", Long.class);
        return query.getSingleResult();
    }
}
