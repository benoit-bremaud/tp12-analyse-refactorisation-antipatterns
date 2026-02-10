package tp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalogueProduits {
    private final List<Produit> produits = new ArrayList<>();

    public CatalogueProduits() {
        produits.add(new Produit("Livre", 12.99, "Educational", 1.2, 100, "Publisher Inc."));
        produits.add(new Produit("Stylo", 2.50, "Stationery", 0.02, 500, "Stationery Co."));
        produits.add(new Produit("Ordinateur", 599.99, "Electronics", 1.5, 50, "Tech Supplier"));
    }

    public Optional<Produit> trouverProduitParNom(String nomProduit) {
        return produits.stream()
                .filter(produit -> produit.getNom().contains(nomProduit))
                .findFirst();
    }
}
