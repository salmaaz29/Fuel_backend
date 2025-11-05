package ma.fstt.ws_rest_api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ma.fstt.ws_rest_api.entities.Station;

public class TestCreationTables {
    public static void main(String[] args) {
        System.out.println("🚀 Début du test de création des tables...");

        EntityManagerFactory factory = null;
        EntityManager em = null;

        try {
            // 1. Créer l'EntityManagerFactory
            System.out.println("📦 Création de l'EntityManagerFactory...");
            factory = Persistence.createEntityManagerFactory("stationpu");

            // 2. Créer l'EntityManager
            System.out.println("🔧 Création de l'EntityManager...");
            em = factory.createEntityManager();

            // 3. Tester la connexion et création des tables
            System.out.println("🗃️ Début de la transaction...");
            em.getTransaction().begin();

            // Créer une station de test
            Station station = new Station();
            station.setNom("Station Test Tanger");
            station.setVille("Tanger");
            station.setAdresse("Avenue Mohammed VI");

            System.out.println("💾 Persistance de la station...");
            em.persist(station);

            em.getTransaction().commit();
            System.out.println("✅ Transaction commitée !");

            System.out.println("🎉 SUCCÈS ! Tables créées et station insérée avec ID: " + station.getId());

        } catch (Exception e) {
            System.err.println("❌ ERREUR: " + e.getMessage());
            e.printStackTrace();

            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                System.out.println("🔁 Transaction rollback !");
            }

        } finally {
            // Fermeture propre
            if (em != null && em.isOpen()) {
                em.close();
                System.out.println("🔒 EntityManager fermé !");
            }
            if (factory != null && factory.isOpen()) {
                factory.close();
                System.out.println("🔒 EntityManagerFactory fermé !");
            }
        }

        System.out.println("🏁 Test terminé !");
    }
}