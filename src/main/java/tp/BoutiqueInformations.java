package tp;

public class BoutiqueInformations {
    private final String boutiqueName = "La Boutique Géniale";
    private final String boutiqueAdresse = "123 Rue de Commerce";
    private final String boutiqueTelephone = "01 23 45 67 89";
    private final String boutiqueEmail = "contact@boutique.com";
    private final int nombreEmployes = 10;
    private final double tauxTVA = 20.0;
    private final String devise = "EUR";
    private final String nomGerant = "Monsieur Martin";
    private final String heureOuverture = "09:00";
    private final String heureFermeture = "18:00";
    private final String politiqueRetour = "14 jours après achat";

    public void afficherDetails() {
        System.out.println("Nom : " + boutiqueName);
        System.out.println("Adresse : " + boutiqueAdresse);
        System.out.println("Téléphone : " + boutiqueTelephone);
        System.out.println("Email : " + boutiqueEmail);
        System.out.println("Nombre d'employés : " + nombreEmployes);
        System.out.println("Devise : " + devise);
        System.out.println("Taux de TVA : " + tauxTVA + "%");
        System.out.println("Gérant : " + nomGerant);
    }

    public void afficherPolitiqueRetour() {
        System.out.println("Politique de retour : " + politiqueRetour);
    }

    public void afficherHoraires() {
        System.out.println("Heures d'ouverture : " + heureOuverture);
        System.out.println("Heures de fermeture : " + heureFermeture);
    }
}
