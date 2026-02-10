package tp;

import java.util.HashMap;
import java.util.Map;

public class Commande {
    private String nomClient;
    private Map<Produit, Integer> produitsCommandes = new HashMap<>();
    private boolean estTraitee = false;
    private double total = 0.0;
    private double totalTaxes = 0.0;
    private double totalApresRemise = 0.0;
    private double fraisLivraison = 5.0;

    public Commande(String nomClient) {
        this.nomClient = nomClient;
    }

    public void ajouterProduit(Produit produit, int quantite) {
        produitsCommandes.merge(produit, quantite, Integer::sum);
        recalculerMontants();
    }

    public void traiterCommande() {
        afficherClient();
        afficherLignesCommande();
        estTraitee = true;
        recalculerMontants();
        afficherDetailsCommande();
    }

    public double getTotal() {
        return totalApresRemise + totalTaxes + fraisLivraison;
    }

    private void afficherClient() {
        System.out.println("Client : " + nomClient);
    }

    private void afficherLignesCommande() {
        for (Map.Entry<Produit, Integer> entry : produitsCommandes.entrySet()) {
            Produit produit = entry.getKey();
            int quantite = entry.getValue();

            System.out.println(produit.getNom());
            System.out.println(produit.getCategorie());
            System.out.println("Quantité : " + quantite);
            System.out.println(produit.getPrix());
            System.out.println("Sous-total : " + calculerSousTotalLigne(produit, quantite));
            System.out.println(produit.getPoids());
            System.out.println(produit.getStock());
            System.out.println(produit.getGarantie());
            System.out.println(produit.getCouleur());
            System.out.println(produit.getDimensions());
        }
    }

    private void recalculerMontants() {
        calculerTotal();
        appliquerRemise();
        appliquerTaxes();
    }

    private double calculerSousTotalLigne(Produit produit, int quantite) {
        return parsePrix(produit) * quantite;
    }

    private double parsePrix(Produit produit) {
        String prixNettoye = produit.getPrix().replaceAll("[^0-9,\\.]", "").replace(',', '.');
        return Double.parseDouble(prixNettoye);
    }

    private void calculerTotal() {
        total = 0.0;
        for (Map.Entry<Produit, Integer> entry : produitsCommandes.entrySet()) {
            total += parsePrix(entry.getKey()) * entry.getValue();
        }
    }

    private void appliquerRemise() {
        totalApresRemise = total * 0.9;
    }

    private void appliquerTaxes() {
        totalTaxes = totalApresRemise * 0.2;
    }

    private void afficherDetailsCommande() {
        System.out.println("Total avec remise : " + totalApresRemise);
        System.out.println("Taxes : " + totalTaxes);
        System.out.println("Frais de livraison : " + fraisLivraison);
    }
}
