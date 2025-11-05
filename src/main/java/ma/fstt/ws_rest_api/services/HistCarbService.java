package ma.fstt.ws_rest_api.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import ma.fstt.ws_rest_api.entities.HistCarb;
import ma.fstt.ws_rest_api.persistence.PersistenceManager;

import java.time.LocalDate;
import java.util.List;

public class HistCarbService {

    public HistCarb ajouterPrix(HistCarb histCarb) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            // Forcer la date si non fournie
            if (histCarb.getDate() == null) {
                histCarb.setDate(LocalDate.now());
            }
            em.persist(histCarb);
            transaction.commit();
            // Recharger l'entité avec ses relations
            HistCarb saved = em.find(HistCarb.class, histCarb.getId());
            em.refresh(saved); // Force le rechargement depuis la DB

            return saved;
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    public List<HistCarb> getHistoriqueByStation(Long stationId) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery(
                            "SELECT h FROM HistCarb h WHERE h.station.id = :stationId ORDER BY h.date DESC",
                            HistCarb.class)
                    .setParameter("stationId", stationId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<HistCarb> getHistoriqueByCarburant(Long carburantId) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery(
                            "SELECT h FROM HistCarb h WHERE h.carburant.id = :carburantId ORDER BY h.date DESC",
                            HistCarb.class)
                    .setParameter("carburantId", carburantId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public HistCarb getPrixActuel(Long stationId, Long carburantId) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        try {
            List<HistCarb> result = em.createQuery(
                            "SELECT h FROM HistCarb h WHERE h.station.id = :stationId AND h.carburant.id = :carburantId ORDER BY h.date DESC",
                            HistCarb.class)
                    .setParameter("stationId", stationId)
                    .setParameter("carburantId", carburantId)
                    .setMaxResults(1)
                    .getResultList();

            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }
}