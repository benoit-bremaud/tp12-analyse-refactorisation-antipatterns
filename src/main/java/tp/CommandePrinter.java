package tp;

import java.util.Map;

public class CommandePrinter {
    public void afficher(Commande commande) {
        afficherClient(commande);
        afficherLignesCommande(commande);
        afficherDetailsCommande(commande);
    }

    private void afficherClient(Commande commande) {
        System.out.println("Client : " + commande.getNomClient());
    }

    private void afficherLignesCommande(Commande commande) {
        for (Map.Entry<Produit, Integer> entry : commande.getProduitsCommandes().entrySet()) {
            Produit produit = entry.getKey();
            int quantite = entry.getValue();

            System.out.println("Produit : " + produit.getNom());
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
    }

    private void afficherDetailsCommande(Commande commande) {
        System.out.println("Total avec remise : " + formatMontant(commande.getTotalApresRemise()));
        System.out.println("Taxes : " + formatMontant(commande.getTaxes()));
        System.out.println("Frais de livraison : " + formatMontant(commande.getFraisLivraison()));
    }

    private String formatMontant(double montant) {
        return String.format("%.2f", montant);
    }
}
