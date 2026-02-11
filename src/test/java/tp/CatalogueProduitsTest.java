package tp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CatalogueProduitsTest {

    @Test
    void shouldFindExistingProductByNameIgnoringCaseAndSpaces() {
        CatalogueProduits catalogue = new CatalogueProduits();

        boolean found = catalogue.trouverProduitParNom("  liVRe ").isPresent();

        assertTrue(found);
    }

    @Test
    void shouldNotFindUnknownProduct() {
        CatalogueProduits catalogue = new CatalogueProduits();

        boolean found = catalogue.trouverProduitParNom("Chaise").isPresent();

        assertFalse(found);
    }
}
