package tp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaiementTest {

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
    void shouldUseInjectedNotificationService() {
        NotificationFake notificationFake = new NotificationFake();
        Paiement paiement = new Paiement(notificationFake);
        Commande commande = new Commande("Alice");
        commande.ajouterProduit(new Produit("Livre", 10.0, "Education", 1.0, 10, "Fournisseur"), 1);

        paiement.traiterPaiement(commande);

        assertEquals(6, notificationFake.types.size());
        assertEquals(TypeNotification.INFO, notificationFake.types.get(0));
        assertEquals(TypeNotification.PROMOTION, notificationFake.types.get(4));
        assertEquals(TypeNotification.PROMOTION, notificationFake.types.get(5));
    }

    @Test
    void shouldWorkWithDefaultConstructor() {
        Paiement paiement = new Paiement();
        Commande commande = new Commande("Bob");
        commande.ajouterProduit(new Produit("Stylo", 2.0, "Stationery", 0.1, 10, "Fournisseur"), 2);

        paiement.traiterPaiement(commande);

        assertTrue(output.toString().contains("Traitement du paiement de"));
    }

    private static class NotificationFake extends Notification {
        private final List<TypeNotification> types = new ArrayList<>();

        @Override
        public void envoyerNotification(TypeNotification type, String message) {
            types.add(type);
        }
    }
}
