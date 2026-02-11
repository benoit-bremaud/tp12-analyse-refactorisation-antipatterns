package tp;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class Commande {
    private static final double TAUX_REMISE = 0.10;
    private static final double TAUX_TAXES = 0.20;
    private static final double FRAIS_LIVRAISON = 5.0;

    private final String nomClient;
    private final Map<Produit, Integer> produitsCommandes = new HashMap<>();

    public Commande(String nomClient) {
        this.nomClient = nomClient;
    }

    public void ajouterProduit(Produit produit, int quantite) {
        produitsCommandes.merge(produit, quantite, Integer::sum);
    }

    public void traiterCommande() {
        new CommandePrinter().afficher(this);
    }

    public double getTotal() {
        return getTotalApresRemise() + getTaxes() + getFraisLivraison();
    }

    public String getNomClient() {
        return nomClient;
    }

    public Map<Produit, Integer> getProduitsCommandes() {
        return Collections.unmodifiableMap(produitsCommandes);
    }

    public double getSousTotal() {
        double total = 0.0;
        for (Map.Entry<Produit, Integer> entry : produitsCommandes.entrySet()) {
            total += entry.getKey().getPrix() * entry.getValue();
        }
        return total;
    }

    public double getTotalApresRemise() {
        return getSousTotal() * (1 - TAUX_REMISE);
    }

    public double getTaxes() {
        return getTotalApresRemise() * TAUX_TAXES;
    }

    public double getFraisLivraison() {
        return FRAIS_LIVRAISON;
    }
}
