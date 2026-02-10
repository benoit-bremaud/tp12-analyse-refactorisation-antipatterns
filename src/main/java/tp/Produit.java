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
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public double getPoids() {
        return poids;
    }

    public int getStock() {
        return stock;
    }

    public int getGarantieMois() {
        return garantieMois;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getDimensions() {
        return dimensions;
    }

    public String getFournisseur() {
        return fournisseur;
    }
}
