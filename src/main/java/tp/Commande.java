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
        calculerTotal();
    }

    public void traiterCommande() {
        System.out.println("Client : " + nomClient);
        for (Map.Entry<Produit, Integer> entry : produitsCommandes.entrySet()) {
            Produit produit = entry.getKey();
            int quantite = entry.getValue();
            System.out.println("tp.Produit : " + produit.getNom());
            System.out.println("Catégorie : " + produit.getCategorie() + " (fournisseur : " + produit.getFournisseur() + ")");
            System.out.println("Quantité : " + quantite);
            System.out.println("Prix unitaire : " + formatMontant(produit.getPrix()) + " EUR");
            System.out.println("Sous-total : " + formatMontant(produit.getPrix() * quantite) + " EUR");
            System.out.println("Poids : " + produit.getPoids() + " kg");
            System.out.println("Stock : " + produit.getStock() + " unités");
            System.out.println("Garantie : " + produit.getGarantieMois() + " mois");
            System.out.println("Couleur : " + produit.getCouleur());
            System.out.println("Dimensions : " + produit.getDimensions());
        }
        estTraitee = true;
        calculerTotal();
        appliquerRemise();
        appliquerTaxes();
        afficherDetailsCommande();
    }

    public double getTotal() {
        return total + totalTaxes + fraisLivraison;
    }

    private void calculerTotal() {
        total = 0.0;
        for (Map.Entry<Produit, Integer> entry : produitsCommandes.entrySet()) {
            total += entry.getKey().getPrix() * entry.getValue();
        }
    }

    private void appliquerRemise() {
        totalApresRemise = total * 0.9;
    }

    private void appliquerTaxes() {
        totalTaxes = totalApresRemise * 0.2;
    }

    private void afficherDetailsCommande() {
        System.out.println("Total avec remise : " + formatMontant(totalApresRemise));
        System.out.println("Taxes : " + formatMontant(totalTaxes));
        System.out.println("Frais de livraison : " + formatMontant(fraisLivraison));
    }

    private String formatMontant(double montant) {
        return String.format("%.2f", montant);
    }
}
