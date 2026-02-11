package tp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandePrinterTest {

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
    void shouldPrintCommandeDetails() {
        Commande commande = new Commande("Charlie");
        commande.ajouterProduit(new Produit("Livre", 10.0, "Education", 1.0, 10, "Fournisseur"), 2);
        CommandePrinter printer = new CommandePrinter();

        printer.afficher(commande);

        String logs = output.toString();
        assertTrue(logs.contains("Client : Charlie"));
        assertTrue(logs.contains("Produit : Livre"));
        assertTrue(logs.contains("Quantité : 2"));
        assertTrue(logs.contains("Total avec remise : 18.00"));
        assertTrue(logs.contains("Taxes : 3.60"));
        assertTrue(logs.contains("Frais de livraison : 5.00"));
    }
}
