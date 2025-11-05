package ma.fstt.ws_rest_api.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.fstt.ws_rest_api.entities.Carburant;
import ma.fstt.ws_rest_api.persistence.PersistenceManager;

import java.util.List;

public class CarburantService {

    public Carburant createCarburant(Carburant carburant) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(carburant);
            transaction.commit();
            return carburant;
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Carburant> getAllCarburants() {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Carburant c", Carburant.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Carburant getCarburantById(Long id) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(Carburant.class, id);
        } finally {
            em.close();
        }
    }

    public void deleteCarburant(Long id) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Carburant carburant = em.find(Carburant.class, id);
            if (carburant != null) {
                em.remove(carburant);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Carburant updateCarburant(Long id, Carburant carburantDetails) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Carburant carburant = em.find(Carburant.class, id);
            if (carburant != null) {
                carburant.setNom(carburantDetails.getNom());
                carburant.setDescription(carburantDetails.getDescription());
                em.merge(carburant);
            }
            transaction.commit();
            return carburant;
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}