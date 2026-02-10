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
        produitsCommandes.merge(produit, quantite, Integer::sum);
    }

    public void traiterCommande() {
        afficherClient();
        afficherLignesCommande();
        afficherDetailsCommande();
    }

    public double getTotal() {
        double total = calculerTotal();
        double totalApresRemise = appliquerRemise(total);
        double totalTaxes = appliquerTaxes(totalApresRemise);
        return totalApresRemise + totalTaxes + fraisLivraison;
    }

    private void afficherClient() {
        System.out.println("Client : " + nomClient);
    }

    private void afficherLignesCommande() {
        for (Map.Entry<Produit, Integer> entry : produitsCommandes.entrySet()) {
            Produit produit = entry.getKey();
            int quantite = entry.getValue();

            System.out.println("Produit : " + produit.getNom());
            System.out.println("Catégorie : " + produit.getCategorie() + " (fournisseur : " + produit.getFournisseur() + ")");
            System.out.println("Quantité : " + quantite);
            System.out.println("Prix unitaire : " + formatMontant(produit.getPrix()) + " EUR");
            System.out.println("Sous-total : " + formatMontant(calculerSousTotalLigne(produit, quantite)) + " EUR");
            System.out.println("Poids : " + produit.getPoids() + " kg");
            System.out.println("Stock : " + produit.getStock() + " unités");
            System.out.println("Garantie : " + produit.getGarantieMois() + " mois");
            System.out.println("Couleur : " + produit.getCouleur());
            System.out.println("Dimensions : " + produit.getDimensions());
        }
    }

    private double calculerSousTotalLigne(Produit produit, int quantite) {
        return produit.getPrix() * quantite;
    }

    private double calculerTotal() {
        double total = 0.0;
        for (Map.Entry<Produit, Integer> entry : produitsCommandes.entrySet()) {
            total += entry.getKey().getPrix() * entry.getValue();
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

        System.out.println("Total avec remise : " + formatMontant(totalApresRemise));
        System.out.println("Taxes : " + formatMontant(totalTaxes));
        System.out.println("Frais de livraison : " + formatMontant(fraisLivraison));
    }

    private String formatMontant(double montant) {
        return String.format("%.2f", montant);
    }
}
