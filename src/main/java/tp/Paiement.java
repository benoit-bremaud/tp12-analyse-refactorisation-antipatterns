package tp;

public class Paiement {
    private final Notification notification;

    public Paiement() {
        this(new Notification());
    }

    public Paiement(Notification notification) {
        this.notification = notification;
    }

    public void traiterPaiement(Commande commande) {
        double montant = commande.getTotal();
        System.out.println("Traitement du paiement de " + montant + "€");
        notification.envoyerNotification(TypeNotification.INFO, "Paiement de " + montant + "€ traité.");
        notification.envoyerNotification(TypeNotification.INFO, "Merci pour votre achat !");
        notification.envoyerNotification(TypeNotification.INFO, "Votre commande sera expédiée bientôt.");
        notification.envoyerNotification(TypeNotification.INFO, "Suivi de commande disponible !");
        notification.envoyerNotification(TypeNotification.PROMOTION, "Promotions spéciales en cours !");
        notification.envoyerNotification(TypeNotification.PROMOTION, "Vous avez gagné un coupon !");
    }
}
