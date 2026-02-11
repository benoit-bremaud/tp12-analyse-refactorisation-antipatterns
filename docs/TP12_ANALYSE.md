# TP12 — Audit des Antipatterns et Code Smells

## Contexte
Projet Java de gestion de boutique en ligne. Le code exécute un scénario simple mais contient volontairement des problèmes de conception et de maintenabilité.

## Inventaire des problèmes identifiés

| # | Antipattern / Smell | Localisation | Pourquoi c'est problématique | Impacts potentiels |
|---|---|---|---|---|
| 1 | Long Method | `Commande.traiterCommande()` | La méthode mélange affichage, calcul, application de remise/taxes et orchestration. | Faible lisibilité, tests unitaires difficiles, modifications risquées. |
| 2 | Large Class | `Commande` (responsabilités métier + formatage + affichage détaillé) | Une classe qui fait trop de choses devient difficile à tester et à faire évoluer. | Couplage fort entre calcul métier et présentation, maintenance coûteuse. |
| 3 | God Object | `Boutique` | Point central qui pilote presque tout le scénario (`lancerBoutique`, commandes, revenu, infos boutique). | Architecture rigide, effet domino à chaque changement. |
| 4 | Duplicated Code | `Commande` / `Paiement` / `BoutiqueInformations` | Multiples `println` et formats de messages répétés sans centralisation. | Maintenance coûteuse, risque d'incohérence lors d'une correction. |
| 5 | Feature Envy | `Commande.afficherLignesCommande()` dépend fortement de `Produit` | La commande connaît trop de détails de présentation de produit (fournisseur, dimensions, etc.). | Mauvaise répartition des responsabilités, modèle peu cohérent. |
| 6 | Primitive Obsession | `Produit`, `Commande`, `BoutiqueInformations` | Prix, taux, devise, quantité manipulés via primitives au lieu de Value Objects. | Erreurs de calcul/formatage, faible expressivité métier. |
| 7 | Temporary Fields | `Commande.fraisLivraison` (constante métier stockée en état mutable de classe) | Donnée transversale de calcul conservée comme champ plutôt que paramètre/politique dédiée. | Évolution compliquée (livraison variable), état de classe moins explicite. |
| 8 | Golden Hammer | Usage systématique de logique impérative monolithique | Même style de traitement appliqué partout (if/else, println, calculs inline) sans abstraction adaptée. | Code verbeux, peu extensible, forte dette technique. |
| 9 | Cargo Cult (Singleton abusif) | `Notification.getInstance()` | Singleton appliqué sans besoin démontré de contrainte d'instance unique. | Couplage global, testabilité faible, rigidité de l'architecture. |

## Détails analytiques par classe

### 1) `Boutique`
- **Smells majeurs**: God Object.
- **Indices**:
  - Orchestration complète du flux métier dans `lancerBoutique()`.
- **Conséquences**:
  - La classe devient un centre de contrôle unique.
  - Chaque évolution (catalogue, facturation, reporting) augmente la complexité globale.

### 2) `Commande`
- **Smells majeurs**: Long Method, Large Class, Duplicated Code, Feature Envy, Temporary Fields.
- **Indices**:
  - `traiterCommande()` orchestre affichage et calcul.
  - `afficherLignesCommande()` connaît beaucoup de détails de `Produit`.
  - formatage monétaire et affichage disséminés.
  - `fraisLivraison` est un champ de classe utilisé uniquement dans le calcul du total.
- **Conséquences**:
  - Méthode difficile à tester en isolation.
  - Mauvaise séparation domaine / présentation.

### 3) `Produit`
- **Smells majeurs**: Primitive Obsession.
- **Indices**:
  - Constructeur riche en primitives (`double`, `int`, `String`) pour représenter des concepts métier.
- **Conséquences**:
  - Le domaine reste peu expressif et difficile à sécuriser.
  - Les invariants métier (montant, devise, poids) sont diffus.

### 4) `Notification` / `Paiement`
- **Smells majeurs**: Cargo Cult Singleton, Golden Hammer, Duplicated Code.
- **Indices**:
  - Singleton non justifié pour `Notification`.
  - Enchaînement de nombreux appels `envoyerNotification(...)` en dur dans `Paiement`.
  - Cascade `if/else` basée sur `message.contains(...)`.
- **Conséquences**:
  - Testabilité faible et dépendance globale.
  - Évolution des types de notifications coûteuse.

## Étape 2 — Propositions de refactorisation (exigence TP)

### 1) Long Method — `Commande.traiterCommande()`
- **Action proposée**:
  - Garder `traiterCommande()` comme orchestrateur court.
  - Extraire explicitement : `calculerTotaux()`, `genererLignesAffichage()`, `afficherRecapitulatif(Totaux)`.
- **Bénéfices attendus**:
  - Lecture plus simple, responsabilités claires, tests unitaires ciblés.

### 2) Large Class — `Commande`
- **Action proposée**:
  - Introduire un composant dédié au rendu console (ex. `CommandePrinter`).
  - Laisser `Commande` centré sur les données et calculs métier.
- **Bénéfices attendus**:
  - Meilleure séparation des préoccupations (SRP), réutilisabilité du calcul métier.

### 3) God Object — `Boutique`
- **Action proposée**:
  - Introduire un service de scénario (ex. `ServiceBoutique` / `CasUsagePasserCommande`).
  - Limiter `Boutique` au rôle de façade de haut niveau.
- **Bénéfices attendus**:
  - Couplage réduit, architecture plus modulable.

### 4) Duplicated Code — affichage et notifications
- **Action proposée**:
  - Centraliser les formats de message dans un composant dédié (`MessageFormatter`).
  - Regrouper les notifications dans une collection puis itérer.
- **Bénéfices attendus**:
  - Moins de duplication, maintenance simplifiée, cohérence des sorties.

### 5) Feature Envy — `Commande.afficherLignesCommande()`
- **Action proposée**:
  - Déplacer le formatage détaillé produit vers `Produit` (méthode dédiée) ou vers `CommandePrinter`.
- **Bénéfices attendus**:
  - Responsabilités mieux réparties, couplage réduit.

### 6) Primitive Obsession — prix/taux/devise/quantité
- **Action proposée**:
  - Introduire des Value Objects (`Money`, `Quantite`, éventuellement `TauxTVA`).
  - Remplacer progressivement les signatures trop primitives.
- **Bénéfices attendus**:
  - Code métier plus expressif, réduction d’erreurs de manipulation.

### 7) Temporary Fields — `Commande.fraisLivraison`
- **Action proposée**:
  - Sortir la politique de frais de livraison dans un objet/service (ex. `PolitiqueLivraison`).
  - Passer les frais calculés en variable locale si constants au scénario.
- **Bénéfices attendus**:
  - État d’objet allégé, comportement plus prévisible.

### 8) Golden Hammer — impératif monolithique
- **Action proposée**:
  - Introduire des abstractions ciblées : stratégie de notification, service de calcul, printer.
  - Conserver l’impératif là où il est pertinent, sans tout centraliser dans les mêmes méthodes.
- **Bénéfices attendus**:
  - Code extensible, réduction de la dette technique.

### 9) Cargo Cult (Singleton abusif) — `Notification`
- **Action proposée**:
  - Remplacer le Singleton par injection d’instance (`new Notification()` injecté dans `Paiement` ou via constructeur).
  - Introduire une interface `NotificationService` si besoin de variantes.
- **Bénéfices attendus**:
  - Testabilité accrue, moindre couplage global.

## Backlog priorisé de mise en œuvre

### Priorité P1 (immédiat)
1. Finaliser la documentation de refactorisation (ce document) pour les 9 smells.
2. Refactorer `Notification`/`Paiement` (suppression Singleton + type explicite de notification).
3. Séparer calcul métier et affichage dans `Commande`.

### Priorité P2 (court terme)
4. Réduire le rôle d’orchestrateur de `Boutique` via un service d’application.
5. Introduire progressivement des Value Objects (`Money`, `Quantite`).

### Priorité P3 (qualité durable)
6. Ajouter des tests unitaires (`Commande`, `GestionCommandes`, `CatalogueProduits`).
7. Renforcer le `pom.xml` (JaCoCo + Checkstyle/PMD/SpotBugs).

## Conclusion de l'audit
Le projet est exécutable mais cumule des problèmes de conception majeurs qui dégradent la maintenabilité. Les priorités de refactorisation recommandées sont:
1. découpage de `Commande.traiterCommande()`,
2. séparation des responsabilités de `Boutique`,
3. amélioration de la modélisation métier (réduction des primitives),
4. suppression de l'abus de Singleton et rationalisation de la notification.
