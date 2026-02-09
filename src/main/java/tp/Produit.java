package tp;

public class Produit {
    private String nom;
    private double prix;
    private String categorie;
    private double poids;
    private int stock;
    private String fournisseur;
    private int garantieMois;
    private String couleur;
    private String dimensions;

    public Produit(String nom, double prix, String categorie, double poids, int stock, String fournisseur) {
        this.nom = nom;
        this.prix = prix;
        this.categorie = categorie;
        this.poids = poids;
        this.stock = stock;
        this.fournisseur = fournisseur;
        this.garantieMois = 24;
        this.couleur = "Inconnue";
        this.dimensions = "Non spécifiées";
    }

    public String getNom() {
        return "Produit : " + nom + " (" + categorie + ")";
    }

    public String getPrix() {
        return prix + " EUR";
    }

    public String getCategorie() {
        return "Catégorie : " + categorie + " (fournisseur : " + fournisseur + ")";  
    }

    public String getPoids() {
        return "Poids : " + poids + " kg";
    }

    public String getStock() {
        return "Stock actuel : " + stock + " unités";
    }

    public String getGarantie() {
        return "Garantie : " + garantieMois + " mois";
    }

    public String getCouleur() {
        return "Couleur : " + couleur;
    }

    public String getDimensions() {
        return "Dimensions : " + dimensions;
    }
}
