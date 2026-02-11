package tp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NotificationTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream output;

    @BeforeEach
    void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldHandleAllTypedNotifications() {
        Notification notification = new Notification();

        notification.envoyerNotification(TypeNotification.INFO, "Message info");
        notification.envoyerNotification(TypeNotification.URGENTE, "Message urgente");
        notification.envoyerNotification(TypeNotification.RETARD, "Message retard");
        notification.envoyerNotification(TypeNotification.PROMOTION, "Message promotion");
        notification.envoyerNotification(TypeNotification.ANNIVERSAIRE, "Message anniversaire");
        notification.envoyerNotification(TypeNotification.NOUVEAUTES, "Message nouveautés");
        notification.envoyerNotification(TypeNotification.FIN_DE_SERIE, "Message fin de série");

        String logs = output.toString();
        assertTrue(logs.contains("Notification [INFO] envoyée : Message info"));
        assertTrue(logs.contains("Notification urgente envoyée !"));
        assertTrue(logs.contains("Notification de retard envoyée !"));
        assertTrue(logs.contains("Notification promotionnelle envoyée !"));
        assertTrue(logs.contains("Notification spéciale d'anniversaire !"));
        assertTrue(logs.contains("Notification de nouvelles collections !"));
        assertTrue(logs.contains("Notification fin de série !"));
    }
}
