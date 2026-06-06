# 🌿 API REST pour la gestion des plantes, huiles et affiliation

Une **API REST complète, sécurisée, scalable et monétisable** pour gérer les plantes, les huiles essentielles, les articles de blog, et l'affiliation (Amazon, etc.).  
Conçue avec **Spring Boot 3.x**, **JWT**, **PostgreSQL**, **Swagger**, et bien plus.

---

## 📌 **Fonctionnalités**

✅ **Authentification** : JWT (Access + Refresh Tokens), Spring Security 6, RBAC.  
✅ **Gestion des plantes** : CRUD, recherche, filtrage, pagination.  
✅ **Gestion des huiles** : CRUD, association aux plantes, recherche par bénéfices.  
✅ **Favoris** : Ajout/suppression de plantes et huiles en favoris.  
✅ **Articles de blog** : CRUD, slugs uniques, catégories, temps de lecture.  
✅ **Affiliation** : Tracking des clics, produits affiliés (Amazon, etc.), statistiques.  
✅ **Dashboard Admin** : Statistiques globales, gestion des utilisateurs, analytics.  
✅ **Optimisations** : Cache (Caffeine/Redis), Rate Limiting, Compression GZIP, ETags.  
✅ **Sécurité** : CORS, CSP, HSTS, XSS Protection, Logging structuré (JSON).  
✅ **Documentation** : Swagger/OpenAPI 3, exemples de requêtes, schémas.

---

## 🛠 **Prérequis**

- **Java 17+**
- **Maven 3.8+**
- **PostgreSQL 14+**
- **Redis** (optionnel, pour le cache en production)
- **Docker** (optionnel, pour le déploiement)

---

## 🚀 **Installation et Configuration**

### 1. Cloner le projet

```bash
git clone https://github.com/votre-utilisateur/api-plantes-huiles.git
cd api-plantes-huiles
```

### 2. Configurer la base de données

Créez une base de données PostgreSQL et mettez à jour les informations dans `application.yml` :

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/votre_base_de_donnees
    username: votre_utilisateur
    password: votre_mot_de_passe
```

### 3. Configurer JWT

Générez une clé secrète pour JWT (au moins 256 bits) et mettez à jour `application.yml` :

```yaml
jwt:
  secret: votre_clé_secrète_256_bits
  access-token-expiration: 900000 # 15 minutes
  refresh-token-expiration: 86400000 # 24 heures
```

### 4. Configurer Redis (optionnel)

Si vous utilisez Redis pour le cache en production, configurez-le dans `application.yml` :

```yaml
spring:
  cache:
    type: redis
    redis:
      host: localhost
      port: 6379
      time-to-live: 600000 # 10 minutes
```

### 5. Configurer CORS

Mettez à jour les origines autorisées dans `application.yml` :

```yaml
cors:
  allowed-origins: https://votre-domaine.com,http://localhost:3000
```

---

## 🏃 **Exécution**

### En développement

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

L'API sera accessible à : `http://localhost:8080`

### En production

```bash
mvn clean package
java -jar target/api-1.0.0.jar --spring.profiles.active=prod
```

---

## 📡 **Endpoints API**

### 🔐 **Authentification**


| Méthode | Endpoint             | Description                  | Accès  |
| ------- | -------------------- | ---------------------------- | ------ |
| POST    | `/api/auth/register` | Inscription d'un utilisateur | Public |
| POST    | `/api/auth/login`    | Connexion                    | Public |
| POST    | `/api/auth/refresh`  | Rafraîchir le token          | Public |


### 🌿 **Plantes**


| Méthode | Endpoint             | Description                 | Accès  |
| ------- | -------------------- | --------------------------- | ------ |
| GET     | `/api/plants`        | Lister toutes les plantes   | Public |
| GET     | `/api/plants/{id}`   | Récupérer une plante par ID | Public |
| POST    | `/api/plants`        | Créer une plante            | Admin  |
| PUT     | `/api/plants/{id}`   | Mettre à jour une plante    | Admin  |
| DELETE  | `/api/plants/{id}`   | Supprimer une plante        | Admin  |
| GET     | `/api/plants/search` | Recherche avancée           | Public |


### 💧 **Huiles**


| Méthode | Endpoint         | Description                | Accès  |
| ------- | ---------------- | -------------------------- | ------ |
| GET     | `/api/oils`      | Lister toutes les huiles   | Public |
| GET     | `/api/oils/{id}` | Récupérer une huile par ID | Public |
| POST    | `/api/oils`      | Créer une huile            | Admin  |
| PUT     | `/api/oils/{id}` | Mettre à jour une huile    | Admin  |
| DELETE  | `/api/oils/{id}` | Supprimer une huile        | Admin  |


### ❤️ **Favoris**


| Méthode | Endpoint                     | Description                      | Accès |
| ------- | ---------------------------- | -------------------------------- | ----- |
| POST    | `/api/favorites/plants/{id}` | Ajouter une plante aux favoris   | User  |
| POST    | `/api/favorites/oils/{id}`   | Ajouter une huile aux favoris    | User  |
| GET     | `/api/favorites`             | Lister tous les favoris          | User  |
| DELETE  | `/api/favorites/plants/{id}` | Supprimer une plante des favoris | User  |
| DELETE  | `/api/favorites/oils/{id}`   | Supprimer une huile des favoris  | User  |


### 📝 **Articles**


| Méthode | Endpoint                    | Description                   | Accès  |
| ------- | --------------------------- | ----------------------------- | ------ |
| GET     | `/api/articles`             | Lister tous les articles      | Public |
| GET     | `/api/articles/{id}`        | Récupérer un article par ID   | Public |
| GET     | `/api/articles/slug/{slug}` | Récupérer un article par slug | Public |
| POST    | `/api/articles`             | Créer un article              | Admin  |
| PUT     | `/api/articles/{id}`        | Mettre à jour un article      | Admin  |
| DELETE  | `/api/articles/{id}`        | Supprimer un article          | Admin  |


### 💰 **Affiliation**


| Méthode | Endpoint                  | Description                       | Accès  |
| ------- | ------------------------- | --------------------------------- | ------ |
| POST    | `/api/affiliate/click`    | Enregistrer un clic d'affiliation | Public |
| GET     | `/api/affiliate/products` | Lister les produits affiliés      | Public |


### 📊 **Admin**


| Méthode | Endpoint                            | Description           | Accès |
| ------- | ----------------------------------- | --------------------- | ----- |
| GET     | `/api/admin/stats`                  | Statistiques globales | Admin |
| GET     | `/api/admin/stats/clicks-by-vendor` | Clics par vendeur     | Admin |


---

## 🧪 **Tests**

### Exécuter les tests

```bash
mvn test
```

### Couverture de code

Pour générer un rapport de couverture avec **JaCoCo** :

```bash
mvn test jacoco:report
```

Le rapport sera disponible dans `target/site/jacoco/index.html`.

---

## 🚀 **Déploiement**

### Avec Docker

1. Construisez l'image Docker :
  ```bash
   docker build -t api-plantes-huiles .
  ```
2. Exécutez le conteneur :
  ```bash
   docker run -p 8080:8080 --env SPRING_PROFILES_ACTIVE=prod api-plantes-huiles
  ```

### Avec Kubernetes

Un exemple de déploiement Kubernetes est disponible dans le dossier `k8s/` (à créer) :

```yaml
# k8s/deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: api-plantes-huiles
spec:
  replicas: 2
  selector:
    matchLabels:
      app: api-plantes-huiles
  template:
    metadata:
      labels:
        app: api-plantes-huiles
    spec:
      containers:
        - name: api
          image: api-plantes-huiles:latest
          ports:
            - containerPort: 8080
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "prod"
            - name: DB_HOST
              value: "postgres-service"
            - name: DB_NAME
              value: "api_prod"
            - name: DB_USERNAME
              valueFrom:
                secretKeyRef:
                  name: db-secrets
                  key: username
            - name: DB_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: db-secrets
                  key: password
---
# k8s/service.yaml
apiVersion: v1
kind: Service
metadata:
  name: api-service
spec:
  selector:
    app: api-plantes-huiles
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
  type: LoadBalancer
```

---

### Sur un serveur (ex: AWS, DigitalOcean)

1. **Construisez le JAR** :
  ```bash
   mvn clean package
  ```
2. **Copiez le JAR** sur le serveur :
  ```bash
   scp target/api-1.0.0.jar utilisateur@votre-serveur:/opt/api/
  ```
3. **Exécutez le JAR** avec `nohup` :
  ```bash
   nohup java -jar /opt/api/api-1.0.0.jar --spring.profiles.active=prod > /var/log/api.log 2>&1 &
  ```
4. **Utilisez un reverse proxy** (Nginx, Apache) pour gérer le trafic HTTPS.

---

## 📜 **Licence**

Ce projet est sous licence **MIT**. Voir le fichier [LICENSE](LICENSE) pour plus de détails.

---

## 🤝 **Contribution**

Les contributions sont les bienvenues ! Ouvrez une **Pull Request** ou un **Issue** pour proposer des améliorations.

---

## 📧 **Contact**

Pour toute question, contactez-moi à : **[abdelkader.taftaf@example.com](mailto:abdelkader.taftaf@example.com)**