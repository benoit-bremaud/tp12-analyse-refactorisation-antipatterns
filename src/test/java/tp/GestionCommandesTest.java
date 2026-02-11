package tp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GestionCommandesTest {

    @Test
    void shouldAccumulateRevenueAfterProcessingOrders() {
        GestionCommandes gestion = new GestionCommandes();
        Produit produit = new Produit("Livre", 10.0, "Education", 1.0, 10, "Fournisseur");

        gestion.ajouterCommande("Alice", produit, 2);
        gestion.ajouterCommande("Bob", produit, 1);

        gestion.traiterCommandes();

        double totalAttenduCommandeAlice = (20.0 * 0.9) + ((20.0 * 0.9) * 0.2) + 5.0;
        double totalAttenduCommandeBob = (10.0 * 0.9) + ((10.0 * 0.9) * 0.2) + 5.0;
        double revenuAttendu = totalAttenduCommandeAlice + totalAttenduCommandeBob;

        assertEquals(revenuAttendu, gestion.getRevenu(), 0.0001);
    }
}
