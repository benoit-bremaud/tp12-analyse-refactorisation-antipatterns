# TP12 — Analyse & Refactorisation d’Anti-Patterns (Java)

Ce projet illustre un TP de **détection** puis **refactorisation progressive** d’anti-patterns et code smells dans une application Java de boutique.

## Objectifs du TP

- Identifier les anti-patterns présents dans le code initial
- Prioriser les corrections (maintenabilité, lisibilité, robustesse)
- Refactoriser par étapes via des branches/PR dédiées
- Conserver un projet exécutable et compilable à chaque étape

## Anti-patterns traités

- Long Method (`Commande`)
- Primitive Obsession (`Produit`)
- Temporary Fields (`Commande`)
- Large Class / God Object (`Boutique`)

Le détail de l’audit est documenté dans :

- `docs/TP12_ANALYSE.md`

## Structure du projet

- `src/main/java/tp/`
  - `Boutique.java` : point d’orchestration principal
  - `CatalogueProduits.java` : gestion du catalogue
  - `GestionCommandes.java` : gestion des commandes/revenus
  - `BoutiqueInformations.java` : informations et politiques de boutique
  - `Commande.java` / `Produit.java` : modèle métier

## Exécution

### Compiler

```bash
mvn -q -DskipTests compile
```

### Lancer l’application

```bash
java -cp target/classes tp.Main
```

### Lancer les tests

```bash
mvn -q test
```

## Workflow de livraison

Le travail a été mené par étapes via PR successives :

1. Audit initial
2. Refactor Long Method
3. Refactor Primitive Obsession
4. Refactor Temporary Fields
5. Refactor Large Class / God Object

Toutes les PR associées au TP12 ont été traitées et intégrées dans `main`.
