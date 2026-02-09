package tp;

public class Paiement {
    private Notification notification = Notification.getInstance();

    public void traiterPaiement(Commande commande) {
        double montant = commande.getTotal();
        System.out.println("Traitement du paiement de " + montant + "€");
        notification.envoyerNotification("tp.Paiement de " + montant + "€ traité.");
        notification.envoyerNotification("Merci pour votre achat !");
        notification.envoyerNotification("Votre commande sera expédiée bientôt.");
        notification.envoyerNotification("Suivi de commande disponible !");
        notification.envoyerNotification("Promotions spéciales en cours !");
        notification.envoyerNotification("Vous avez gagné un coupon !");
    }
}
