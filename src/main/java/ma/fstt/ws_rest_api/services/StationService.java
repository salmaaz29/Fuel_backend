package ma.fstt.ws_rest_api.services;

import jakarta.persistence.EntityManager;
import ma.fstt.ws_rest_api.entities.Station;
import ma.fstt.ws_rest_api.persistence.PersistenceManager;

import java.util.List;

public class StationService {

    public Station createStation(Station station) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(station);
            em.getTransaction().commit();
            return station;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Station> getAllStations() {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Station s", Station.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Station getStationById(Long id) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(Station.class, id);
        } finally {
            em.close();
        }
    }

    public Station updateStation(Long id, Station stationDetails) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            Station station = em.find(Station.class, id);
            if (station != null) {
                station.setNom(stationDetails.getNom());
                station.setVille(stationDetails.getVille());
                station.setAdresse(stationDetails.getAdresse());
                em.merge(station);
            }
            em.getTransaction().commit();
            return station;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void deleteStation(Long id) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            Station station = em.find(Station.class, id);
            if (station != null) {
                em.remove(station);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}