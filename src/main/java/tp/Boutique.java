package tp;

import java.util.ArrayList;
import java.util.List;

public class Boutique {
    private List<Produit> produits = new ArrayList<>();
    private List<Commande> commandes = new ArrayList<>();
    private double revenu = 0.0;
    private String boutiqueName = "La Boutique Géniale";
    private String boutiqueAdresse = "123 Rue de Commerce";
    private String boutiqueTelephone = "01 23 45 67 89";
    private String boutiqueEmail = "contact@boutique.com";
    private int nombreEmployes = 10;
    private double tauxTVA = 20.0;
    private String devise = "EUR";
    private String nomGerant = "Monsieur Martin";
    private String heureOuverture = "09:00";
    private String heureFermeture = "18:00";
    private String politiqueRetour = "14 jours après achat";

    public Boutique() {
        produits.add(new Produit("Livre", 12.99, "Educational", 1.2, 100, "Publisher Inc."));
        produits.add(new Produit("Stylo", 2.50, "Stationery", 0.02, 500, "Stationery Co."));
        produits.add(new Produit("Ordinateur", 599.99, "Electronics", 1.5, 50, "Tech Supplier"));
    }

    public void lancerBoutique() {
        ajouterCommande("Client1", "Livre", 2);
        ajouterCommande("Client2", "Stylo", 5);
        traitementCommandes();
        afficherDetailsBoutique();
        afficherRevenu();
        afficherPolitiqueRetour();
        afficherHoraires();
    }

    public void ajouterCommande(String nomClient, String nomProduit, int quantite) {
        Commande commande = new Commande(nomClient);
        for (Produit produit : produits) {
            if (produit.getNom().equals(nomProduit)) {
                commande.ajouterProduit(produit, quantite);
                commandes.add(commande);
                break;
            }
        }
    }

    public void traitementCommandes() {
        for (Commande commande : commandes) {
            commande.traiterCommande();
            revenu += commande.getTotal();
        }
    }

    public void afficherDetailsBoutique() {
        System.out.println("Nom : " + boutiqueName);
        System.out.println("Adresse : " + boutiqueAdresse);
        System.out.println("Téléphone : " + boutiqueTelephone);
        System.out.println("Email : " + boutiqueEmail);
        System.out.println("Nombre d'employés : " + nombreEmployes);
        System.out.println("Devise : " + devise);
        System.out.println("Taux de TVA : " + tauxTVA + "%");
        System.out.println("Gérant : " + nomGerant);
    }

    public void afficherPolitiqueRetour() {
        System.out.println("Politique de retour : " + politiqueRetour);
    }

    public void afficherHoraires() {
        System.out.println("Heures d'ouverture : " + heureOuverture);
        System.out.println("Heures de fermeture : " + heureFermeture);
    }

    public void afficherRevenu() {
        System.out.println("Revenu total : " + revenu);
    }
}
