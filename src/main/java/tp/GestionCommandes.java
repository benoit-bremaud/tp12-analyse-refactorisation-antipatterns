package tp;

import java.util.ArrayList;
import java.util.List;

public class GestionCommandes {
    private final List<Commande> commandes = new ArrayList<>();
    private double revenu = 0.0;

    public void ajouterCommande(String nomClient, Produit produit, int quantite) {
        Commande commande = new Commande(nomClient);
        commande.ajouterProduit(produit, quantite);
        commandes.add(commande);
    }

    public void traiterCommandes() {
        for (Commande commande : commandes) {
            commande.traiterCommande();
            revenu += commande.getTotal();
        }
    }

    public void afficherRevenu() {
        System.out.println("Revenu total : " + revenu);
    }
}
