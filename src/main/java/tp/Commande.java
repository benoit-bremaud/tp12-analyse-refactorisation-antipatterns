package tp;

import java.util.HashMap;
import java.util.Map;

public class Commande {
    private String nomClient;
    private Map<Produit, Integer> produitsCommandes = new HashMap<>();
    private double fraisLivraison = 5.0;

    public Commande(String nomClient) {
        this.nomClient = nomClient;
    }

    public void ajouterProduit(Produit produit, int quantite) {
        produitsCommandes.put(produit, quantite);
    }

    public void traiterCommande() {
        System.out.println("Client : " + nomClient);
        for (Map.Entry<Produit, Integer> entry : produitsCommandes.entrySet()) {
            Produit produit = entry.getKey();
            int quantite = entry.getValue();
            System.out.println("tp.Produit : " + produit.getNom());
            System.out.println("Catégorie : " + produit.getCategorie());
            System.out.println("Quantité : " + quantite);
            System.out.println("Prix unitaire : " + produit.getPrix());
            System.out.println("Sous-total : " + (Double.parseDouble(produit.getPrix()) * quantite));
            System.out.println("Poids : " + produit.getPoids());
            System.out.println("Stock : " + produit.getStock());
            System.out.println("Garantie : " + produit.getGarantie());
            System.out.println("Couleur : " + produit.getCouleur());
            System.out.println("Dimensions : " + produit.getDimensions());
        }
        afficherDetailsCommande();
    }

    public double getTotal() {
        double total = calculerTotal();
        double totalApresRemise = appliquerRemise(total);
        double totalTaxes = appliquerTaxes(totalApresRemise);
        return total + totalTaxes + fraisLivraison;
    }

    private double calculerTotal() {
        double total = 0.0;
        for (Map.Entry<Produit, Integer> entry : produitsCommandes.entrySet()) {
            total += Double.parseDouble(entry.getKey().getPrix()) * entry.getValue();
        }
        return total;
    }

    private double appliquerRemise(double total) {
        return total * 0.9;
    }

    private double appliquerTaxes(double totalApresRemise) {
        return totalApresRemise * 0.2;
    }

    private void afficherDetailsCommande() {
        double total = calculerTotal();
        double totalApresRemise = appliquerRemise(total);
        double totalTaxes = appliquerTaxes(totalApresRemise);
        System.out.println("Total avec remise : " + totalApresRemise);
        System.out.println("Taxes : " + totalTaxes);
        System.out.println("Frais de livraison : " + fraisLivraison);
    }
}
