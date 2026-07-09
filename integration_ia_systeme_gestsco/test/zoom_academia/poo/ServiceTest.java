package zoom_academia.poo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wouti.zoom_academia.entities.Parametre;
import com.wouti.zoom_academia.entities.Service;
import com.wouti.zoom_academia.entities.TemplatePrompt;
import com.wouti.zoom_academia.transverse.TypeService;

public class ServiceTest {

    private Service service;
    private TemplatePrompt templatePrompt;
    private List<Parametre> parametres;

    @BeforeEach
    public void setUp() {
        // Initialisation des objets avant chaque test
        service = new Service();
        templatePrompt = new TemplatePrompt();  
        parametres = new ArrayList<>();
        
        service.setIdService(1L);
        service.setCodeService("SERVICE123");
        service.setTypeService(TypeService.ORIENTATION_PROFFESSIONNELLE);  // Enum
        service.setMessage("Message de test");
        service.setLogo("logo.png");
        service.setTemplatePrompt(templatePrompt);
        service.setParametre(parametres);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, service.getIdService());
        assertEquals("SERVICE123", service.getCodeService());
        assertEquals(TypeService.ORIENTATION_PROFFESSIONNELLE, service.getTypeService());
        assertEquals("Message de test", service.getMessage());
        assertEquals("logo.png", service.getLogo());
        assertEquals(templatePrompt, service.getTemplatePrompt());
        assertEquals(parametres, service.getParametre());
    }

    @Test
    public void testToString() {
        String expected = "Service [idService=1, codeService=SERVICE123, typeService=ORIENTATION_PROFFESSIONNELLE, message=Message de test, logo=logo.png, templatePrompt=" + templatePrompt + ", parametre=" + parametres + "]";
        assertEquals(expected, service.toString());
    }


}
