# EduGuideAI

**Plateforme intelligente de suivi de la vie estudiantine**, combinant un backend Spring Boot / Spring AI et un frontend Angular pour offrir cinq services propulsés par l'IA générative (Google Gemini) : analyse prédictive de la réussite, assistant virtuel conversationnel, détection de fraude académique, orientation professionnelle et simulation des efforts d'apprentissage.

> Projet développé dans le cadre d'un stage (IBAM / UJKZ), pensé comme plateforme autonome de portfolio.

---

## Sommaire

- [Fonctionnalités](#fonctionnalités)
- [Stack technique](#stack-technique)
- [Architecture](#architecture)
- [Prérequis](#prérequis)
- [Installation](#installation)
  - [1. Cloner le dépôt](#1-cloner-le-dépôt)
  - [2. Base de données PostgreSQL](#2-base-de-données-postgresql)
  - [3. Backend (Spring Boot)](#3-backend-spring-boot)
  - [4. Frontend (Angular)](#4-frontend-angular)
- [Variables d'environnement](#variables-denvironnement)
- [Comptes de démonstration](#comptes-de-démonstration)
- [Structure du projet](#structure-du-projet)
- [Principaux endpoints API](#principaux-endpoints-api)
- [Jeu de données de démo](#jeu-de-données-de-démo)
- [Docker (optionnel)](#docker-optionnel)
- [Licence](#licence)

---

## Fonctionnalités

EduGuideAI propose 5 services IA accessibles depuis l'interface, chacun s'appuyant sur un modèle de langage (Google Gemini via l'API compatible OpenAI de Spring AI) :

| Service | Description |
|---|---|
| **Analyse Prédictive** | Prédit les risques de décrochage scolaire à partir des notes, absences et de l'engagement de l'étudiant. |
| **Assistant Virtuel** | Chatbot pédagogique répondant aux questions académiques des étudiants. |
| **Détection de Fraude** | Repère les anomalies statistiques suspectes dans les résultats (notes incohérentes, patterns anormaux). |
| **Orientation Professionnelle** | Recommande des filières et métiers adaptés au profil, aux intérêts et compétences de l'étudiant. |
| **Simulation des Efforts** | Simule l'impact d'un plan de travail (heures d'étude, méthodes) sur les résultats futurs. |

Autour de ces services : gestion des utilisateurs et profils (admin, enseignant, étudiant, parent/tuteur), gestion des inscrits/niveaux/notes, liaison parent-étudiant, et un back-office de configuration (services, templates de prompts, cinématiques d'affichage).

## Stack technique

**Backend**
- Java 17
- Spring Boot 3.3.4
- Spring AI 1.0.0-M2 (intégration Google Gemini via l'API compatible OpenAI)
- Spring Data JPA / Hibernate
- Spring Security
- PostgreSQL 15+
- Maven

**Frontend**
- Angular 13
- Angular Material
- Bootstrap 4
- TypeScript

**Infra**
- Docker / Docker Compose (PostgreSQL)

## Architecture

```
eduguideai/
├── backend/    → API REST Spring Boot (port 8181)
└── frontend/   → SPA Angular (port 4200, dev server)
```

Le frontend consomme l'API REST du backend (CORS configuré pour `http://localhost:4200`). Le backend expose aussi un endpoint Actuator (`/actuator`) pour le monitoring (Prometheus).

## Prérequis

- **Java 17** (JDK)
- **Maven 3.9+**
- **Node.js 16 ou 18** + npm
- **PostgreSQL 15+** (local ou via Docker)
- Une **clé API Google Gemini** (obtenue sur [Google AI Studio](https://aistudio.google.com/))

## Installation

### 1. Cloner le dépôt

```bash
git clone https://github.com/misterjc1/eduguideai.git
cd eduguideai
```

### 2. Base de données PostgreSQL

**Option A — via Docker (recommandé)**

```bash
docker compose up -d
```

Cela démarre un conteneur PostgreSQL (`postgres:15-alpine`) avec la base `wouti` et le schéma `ia_schema` déjà initialisé (`init-schema.sql`).

**Option B — PostgreSQL installé localement**

Créez une base `wouti` et un schéma `ia_schema` :

```sql
CREATE DATABASE wouti;
\c wouti
CREATE SCHEMA IF NOT EXISTS ia_schema;
```

### 3. Backend (Spring Boot)

```bash
cd backend

# Definir les variables d'environnement (voir section dediee)
export GEMINI_API_KEY="votre-cle-api-gemini"
export DB_PASSWORD="root"          # optionnel si different du defaut

mvn spring-boot:run
```

Le backend démarre sur **http://localhost:8181**.

Au premier démarrage, le schéma est créé automatiquement (`ddl-auto=update`) et le jeu de données de démonstration est chargé (~1000 étudiants, profils, services IA, etc. — voir [Jeu de données de démo](#jeu-de-données-de-démo)).

### 4. Frontend (Angular)

```bash
cd frontend
npm install
ng serve
```

Le frontend démarre sur **http://localhost:4200** et communique avec le backend sur `http://localhost:8181`.

## Variables d'environnement

| Variable | Description | Obligatoire | Défaut |
|---|---|---|---|
| `GEMINI_API_KEY` | Clé API Google Gemini (Spring AI, endpoint OpenAI-compatible) | Oui | — |
| `DB_PASSWORD` | Mot de passe PostgreSQL | Non | `root` |

Ces variables sont référencées dans `backend/src/main/resources/application.properties` sous la forme `${GEMINI_API_KEY}` et `${DB_PASSWORD:root}` — aucune clé n'est en dur dans le dépôt.

## Comptes de démonstration

| Identifiant | Mot de passe | Profil |
|---|---|---|
| `admin` | `admin123` | Administrateur |
| `prof.ouedraogo` | `prof123` | Enseignant |
| `alice.kabore` | `etud123` | Étudiant |
| `moussa.sawadogo` | `etud123` | Étudiant |
| `traore.parent` | `parent123` | Parent / Tuteur |

## Structure du projet

```
backend/
├── src/main/java/com/wouti/zoom_academia/
│   ├── controllers/     → Endpoints REST (AiAnalyseController, ChatBotController, ...)
│   ├── entities/        → Entites JPA (Utilisateur, Inscrit, Note, Service, ...)
│   ├── repositories/    → Spring Data JPA repositories
│   ├── config/          → Configuration (DataLoader, securite, ...)
│   ├── transverse/      → Enums partages (TypeService, TypeUtilisateur, ...)
│   └── vo/               → Value objects / DTOs
└── src/main/resources/
    ├── application.properties
    └── data.sql          → Jeu de donnees de demonstration

frontend/
└── src/app/
    ├── ui/               → Composants metier (analyse-pred, chatbot, orient-pro, ...)
    ├── user-module/      → Authentification, gestion utilisateurs/profils
    ├── components/       → Navbar, sidebar, footer
    └── services/         → Services HTTP Angular
```

## Principaux endpoints API

| Méthode | Endpoint | Description |
|---|---|---|
| `POST` | `/ai/analyse` | Analyse prédictive de la réussite académique |
| `POST` | `/ai/orientation` | Recommandations d'orientation professionnelle |
| `POST` | `/ai/simulation` | Simulation des efforts d'apprentissage |
| `POST` | `/ai/fraud-analysis` | Détection d'anomalies / fraude académique |
| `GET`  | `/chatbot/predefinedQuestions` | Questions suggérées pour l'assistant virtuel |
| `POST` | `/chatbot/ask` | Poser une question à l'assistant virtuel |
| `POST` | `/career/generateCareerRecommendations` | Génération de recommandations de carrière |
| `POST` | `/simulation/generateEffortResults` | Génération des résultats de simulation |
| `GET`  | `/inscrit/findNotes?matricule=` | Notes d'un étudiant par matricule |

## Jeu de données de démo

`data.sql` charge automatiquement au démarrage (via `spring.sql.init.mode=always`) :

- 4 profils, 5 utilisateurs de démo
- **1000 étudiants** (inscrits) répartis sur ~29 niveaux/filières
- Plusieurs milliers de notes associées
- 5 services IA, 5 templates de prompts, cinématiques et paramètres associés

Le script est idempotent (`ON CONFLICT ... DO NOTHING`), il peut être rejoué sans dupliquer les données.

## Docker (optionnel)

Un `docker-compose.yml` à la racine permet de lancer uniquement la base PostgreSQL :

```bash
docker compose up -d       # démarre PostgreSQL
docker compose down        # arrête et retire le conteneur (les données persistent dans le volume)
```

## Licence

Projet personnel / portfolio — usage éducatif et démonstratif.
