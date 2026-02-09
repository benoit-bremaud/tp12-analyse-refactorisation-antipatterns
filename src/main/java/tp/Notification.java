package tp;

public class Notification {
    private static Notification instance;

    private Notification() {}

    public static Notification getInstance() {
        if (instance == null) {
            instance = new Notification();
        }
        return instance;
    }

    public void envoyerNotification(String message) {
        System.out.println("tp.Notification envoyée : " + message);
        if (message.contains("urgent")) {
            System.out.println("tp.Notification urgente envoyée !");
        } else if (message.contains("retard")) {
            System.out.println("tp.Notification de retard envoyée !");
        } else if (message.contains("promotion")) {
            System.out.println("tp.Notification promotionnelle envoyée !");
        } else if (message.contains("anniversaire")) {
            System.out.println("tp.Notification spéciale d'anniversaire !");
        } else if (message.contains("nouveautés")) {
            System.out.println("tp.Notification de nouvelles collections !");
        } else if (message.contains("fin de série")) {
            System.out.println("tp.Notification fin de série !");
        }
    }
}
