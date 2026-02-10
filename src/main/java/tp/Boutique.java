package tp;

public class Boutique {
    private final CatalogueProduits catalogue = new CatalogueProduits();
    private final GestionCommandes gestionCommandes = new GestionCommandes();
    private final BoutiqueInformations informations = new BoutiqueInformations();

    public void lancerBoutique() {
        ajouterCommande("Client1", "Livre", 2);
        ajouterCommande("Client2", "Stylo", 5);
        traiterCommandes();
        informations.afficherDetails();
        gestionCommandes.afficherRevenu();
        informations.afficherPolitiqueRetour();
        informations.afficherHoraires();
    }

    public void ajouterCommande(String nomClient, String nomProduit, int quantite) {
        catalogue.trouverProduitParNom(nomProduit)
                .ifPresent(produit -> gestionCommandes.ajouterCommande(nomClient, produit, quantite));
    }

    public void traiterCommandes() {
        gestionCommandes.traiterCommandes();
    }
}
