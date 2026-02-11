package tp;

public class Notification {
    public void envoyerNotification(TypeNotification type, String message) {
        System.out.println("Notification [" + type + "] envoyée : " + message);

        switch (type) {
            case URGENTE -> System.out.println("Notification urgente envoyée !");
            case RETARD -> System.out.println("Notification de retard envoyée !");
            case PROMOTION -> System.out.println("Notification promotionnelle envoyée !");
            case ANNIVERSAIRE -> System.out.println("Notification spéciale d'anniversaire !");
            case NOUVEAUTES -> System.out.println("Notification de nouvelles collections !");
            case FIN_DE_SERIE -> System.out.println("Notification fin de série !");
            default -> {
                // Pas de message spécifique complémentaire
            }
        }
    }
}
