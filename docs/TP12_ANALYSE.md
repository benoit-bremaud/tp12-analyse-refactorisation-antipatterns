# TP12 — Audit des Antipatterns et Code Smells

## Contexte
Projet Java de gestion de boutique en ligne. Le code exécute un scénario simple mais contient volontairement des problèmes de conception et de maintenabilité.

## Inventaire des problèmes identifiés

| # | Antipattern / Smell | Localisation | Pourquoi c'est problématique | Impacts potentiels |
|---|---|---|---|---|
| 1 | Long Method | `Commande.traiterCommande()` | La méthode mélange affichage, calcul, application de remise/taxes et orchestration. | Faible lisibilité, tests unitaires difficiles, modifications risquées. |
| 2 | Large Class | `Boutique` | Trop d'attributs métier, config, reporting, orchestration commandes/produits dans une même classe. | Couplage élevé, classe difficile à faire évoluer, responsabilités floues. |
| 3 | God Object | `Boutique` | Point central qui pilote presque tout le scénario (`lancerBoutique`, commandes, revenu, infos boutique). | Architecture rigide, effet domino à chaque changement. |
| 4 | Duplicated Code | `Commande.traiterCommande()` + méthodes de calcul/affichage | Multiples blocs de formatage et calcul dispersés, logique répétitive de transformation prix/affichage. | Maintenance coûteuse, risque d'incohérence lors d'une correction. |
| 5 | Feature Envy | `Commande` dépend fortement des détails de `Produit` | `Commande` exploite beaucoup de getters formatés de `Produit` pour produire son affichage. | Mauvaise répartition des responsabilités, modèle peu cohérent. |
| 6 | Primitive Obsession | `Produit.getPrix()` retourne `String`, parsing dans `Commande` | Prix/poids/monnaie manipulés sous forme de primitives/chaînes au lieu d'objets dédiés. | Erreurs de conversion, logique métier fragile, lisibilité dégradée. |
| 7 | Temporary Fields | `Commande` (`totalTaxes`, `totalApresRemise`, `fraisLivraison`) | Champs intermédiaires conservés au niveau classe alors qu'ils servent surtout au traitement ponctuel. | État interne difficile à raisonner, effets de bord possibles. |
| 8 | Golden Hammer | Usage systématique de logique impérative monolithique | Même style de traitement appliqué partout (if/else, println, calculs inline) sans abstraction adaptée. | Code verbeux, peu extensible, forte dette technique. |
| 9 | Cargo Cult (Singleton abusif) | `Notification.getInstance()` | Singleton appliqué sans besoin démontré de contrainte d'instance unique. | Couplage global, testabilité faible, rigidité de l'architecture. |

## Détails analytiques par classe

### 1) `Boutique`
- **Smells majeurs**: Large Class, God Object.
- **Indices**:
  - Grand nombre d'attributs hétérogènes (coordonnées boutique, RH, horaires, devise, politique retour, revenu, listes produits/commandes).
  - Orchestration complète du flux métier dans `lancerBoutique()`.
- **Conséquences**:
  - La classe devient un centre de contrôle unique.
  - Chaque évolution (catalogue, facturation, reporting) augmente la complexité globale.

### 2) `Commande`
- **Smells majeurs**: Long Method, Temporary Fields, Duplicated Code, Feature Envy.
- **Indices**:
  - `traiterCommande()` enchaîne affichage détaillé, calcul, remise, taxes, restitution.
  - Conversion répétée du prix (`Double.parseDouble(produit.getPrix())`) car le prix est formaté en String.
  - Multiplication de champs intermédiaires pour des étapes de calcul.
- **Conséquences**:
  - Méthode difficile à tester en isolation.
  - Risque d'erreur sur les conversions/formatages.

### 3) `Produit`
- **Smells majeurs**: Primitive Obsession / séparation donnée-présentation dégradée.
- **Indices**:
  - Les getters renvoient du texte formaté (ex: `"... EUR"`, `"Poids : ..."`) au lieu de valeurs métier brutes.
- **Conséquences**:
  - Le domaine est mélangé à la présentation.
  - Les classes clientes doivent parser des chaînes pour recalculer des valeurs numériques.

### 4) `Notification` / `Paiement`
- **Smells majeurs**: Cargo Cult Singleton, Golden Hammer, Duplicated Code.
- **Indices**:
  - Singleton non justifié pour `Notification`.
  - Enchaînement de nombreux appels `envoyerNotification(...)` en dur dans `Paiement`.
  - Cascade `if/else` basée sur `message.contains(...)`.
- **Conséquences**:
  - Testabilité faible et dépendance globale.
  - Évolution des types de notifications coûteuse.

## Conclusion de l'audit
Le projet est exécutable mais cumule des problèmes de conception majeurs qui dégradent la maintenabilité. Les priorités de refactorisation recommandées sont:
1. découpage de `Commande.traiterCommande()`,
2. séparation des responsabilités de `Boutique`,
3. correction de la modélisation prix/poids (éviter parsing de String),
4. suppression de l'abus de Singleton et rationalisation de la notification.
