# Fuel Management Backend - API REST

API REST complète pour la gestion des stations-service et des prix des carburants, développée avec Java EE et JAX-RS.

## 🛠 Technologies Utilisées

- **Java EE ** - Plateforme entreprise
- **JAX-RS** - API RESTful Jakarta
- **JPA/Hibernate** - Persistance des données
- **MySQL** - Base de données
- **Maven** - Gestion des dépendances

## 🏗 Architecture du Projet
```
backend/
├── src/
│ ├── entities/ # Entités JPA
│ │ ├── Station.java
│ │ ├── Carburant.java
│ │ └── HistCarb.java
│ ├── persistence/ # Gestion de la persistance
│ │ └── PersistenceManager.java
│ ├── services/ # Logique métier
│ │ ├── StationService.java
│ │ ├── CarburantService.java
│ │ └── HistCarbService.java
│ ├── resources/ # Web Services REST
│ │ ├── StationResource.java
│ │ ├── CarburantResource.java
│ │ └── PrixResource.java
│ └── configuration/ # Configuration
│ ├── JAXRSConfiguration.java
│ └── CorsFilter.java
│ ├── resources/
│ │ ├── persistence.xml # Configuration JPA
└── pom.xml # Dépendances Maven
```

##  Modèle de Données

### Entités JPA
- **Station** : Stations-service (id, nom, localisation)
- **Carburant** : Types de carburants (id, nom, description)  
- **HistCarb** : Historique des prix (id, dateValidite, prix, station, carburant)

### Relations
- `HistCarb` ↔ `Station` : ManyToOne
- `HistCarb` ↔ `Carburant` : ManyToOne

##  API REST Endpoints
```
### Stations (`/api/stations`)
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/stations` | Liste toutes les stations |
| GET | `/stations/{id}` | Récupère une station par ID |
| POST | `/stations` | Crée une nouvelle station |
| PUT | `/stations/{id}` | Met à jour une station |
| DELETE | `/stations/{id}` | Supprime une station |

### Carburants (`/api/carburants`)
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/carburants` | Liste tous les carburants |
| GET | `/carburants/{id}` | Récupère un carburant par ID |
| POST | `/carburants` | Crée un nouveau carburant |
| PUT | `/carburants/{id}` | Met à jour un carburant |
| DELETE | `/carburants/{id}` | Supprime un carburant |

### Prix (`/api/prix`)
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| POST | `/prix` | Ajoute un nouveau prix |
| GET | `/prix/station/{stationId}` | Historique d'une station |
| GET | `/prix/station/{stationId}/carburant/{carburantId}` | Prix actuel |

```
### Configuration

1. **Cloner le projet**
```
git clone https://github.com/salmaaz29/Fuel_backend.git
cd Fuel_backend
```
2. **Configurer la base de données**

```
CREATE DATABASE station_db;
```
3. **Configurer persistence.xml**

xml
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/station_db"/>
<property name="javax.persistence.jdbc.user" value="votre_utilisateur"/>
<property name="javax.persistence.jdbc.password" value="votre_mot_de_passe"/>
Compiler et déployer

```
mvn clean install
# Déployer le fichier .war généré sur wildfly
```
4. **Utilisation**
L'API sera disponible sur : http://localhost:8080/api

Exemple de requête :

```
# Récupérer toutes les stations
curl -X GET http://localhost:8080/api/stations

# Créer une nouvelle station
curl -X POST http://localhost:8080/api/stations \
  -H "Content-Type: application/json" \
  -d '{"nom": "Station Centre", "adresse": "Centre-ville","ville": "ville"}'

```
