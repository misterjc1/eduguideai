package zoom_academia.poo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.wouti.zoom_academia.entities.Cinematique;
import com.wouti.zoom_academia.entities.Service;
import com.wouti.zoom_academia.entities.TypeCinematique;

public class CinematiqueTest {

    @Test
    public void testCinematiqueCreation() {
        Cinematique cinematique = new Cinematique();
        cinematique.setIntitule("Selectionner preferences");
        cinematique.setCodeCinematique("CODE001");
        cinematique.setImage("image.png");

        assertEquals("Selectionner preferences", cinematique.getIntitule());
        assertEquals("CODE001", cinematique.getCodeCinematique());
        assertEquals("image.png", cinematique.getImage());
    }

    @Test
    public void testSetAndGetTypeCinematique() {
        Cinematique cinematique = new Cinematique();
        TypeCinematique typeCinematique = new TypeCinematique();
        typeCinematique.setCodeTypeCinematique("TC001");

        cinematique.setTypeCinematique(typeCinematique);

        assertNotNull(cinematique.getTypeCinematique());
        assertEquals("TC001", cinematique.getTypeCinematique().getCodeTypeCinematique());
    }

    @Test
    public void testSetAndGetTypeService() {
        Cinematique cinematique = new Cinematique();
        Service service = new Service();
        service.setCodeService("SERV001");

        cinematique.setTypeService(service);

        assertNotNull(cinematique.getTypeService());
        assertEquals("SERV001", cinematique.getTypeService().getCodeService());
    }

    @Test
    public void testSetAndGetCinematiqueMere() {
        Cinematique cinematique = new Cinematique();
        Cinematique cinematiqueMere = new Cinematique();
        cinematiqueMere.setCodeCinematique("MERE001");

        cinematique.setCinematiqueMere(cinematiqueMere);

        assertNotNull(cinematique.getCinematiqueMere());
        assertEquals("MERE001", cinematique.getCinematiqueMere().getCodeCinematique());
    }
}
