package tp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandeTest {

    @Test
    void shouldCalculateSousTotal() {
        Produit livre = new Produit("Livre", 10.0, "Education", 1.0, 10, "Fournisseur");
        Commande commande = new Commande("Alice");

        commande.ajouterProduit(livre, 2);

        assertEquals(20.0, commande.getSousTotal(), 0.0001);
    }

    @Test
    void shouldCalculateTotalApresRemise() {
        Produit livre = new Produit("Livre", 10.0, "Education", 1.0, 10, "Fournisseur");
        Commande commande = new Commande("Alice");

        commande.ajouterProduit(livre, 2);

        assertEquals(18.0, commande.getTotalApresRemise(), 0.0001);
    }

    @Test
    void shouldCalculateTaxes() {
        Produit livre = new Produit("Livre", 10.0, "Education", 1.0, 10, "Fournisseur");
        Commande commande = new Commande("Alice");

        commande.ajouterProduit(livre, 2);

        assertEquals(3.6, commande.getTaxes(), 0.0001);
    }

    @Test
    void shouldCalculateTotal() {
        Produit livre = new Produit("Livre", 10.0, "Education", 1.0, 10, "Fournisseur");
        Commande commande = new Commande("Alice");

        commande.ajouterProduit(livre, 2);

        assertEquals(26.6, commande.getTotal(), 0.0001);
    }
}
