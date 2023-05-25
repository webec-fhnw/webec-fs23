package ch.fhnw.webec.lengthconverter;

import ch.fhnw.webec.lengthconverter.ConvertController.ConvertRequest;
import ch.fhnw.webec.lengthconverter.ConvertController.ConvertResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ConvertControllerIT {

    @Autowired
    TestRestTemplate template;

    @Test
    public void convert() {
        var response = template.getForObject("/api/convert?feet=5&inches=8",
                ConvertResponse.class);
        assertNotNull(response);
        assertEquals(172, response.cmPart);
        assertEquals(7, response.mmPart);
    }
}
