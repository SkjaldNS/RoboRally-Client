package dk.dtu.roborally_server.controllerTests;

import dk.dtu.roborally_server.controller.DocumentationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
/**
 * The DocumentationController class handles HTTP requests related to the API documentation.
 * It provides a method to retrieve the API documentation.
 *
 * The class is annotated with @RestController, indicating that it's a controller class that can handle HTTP requests.
 * It's also annotated with @RequestMapping("/api-docs"), indicating that its methods are mapped to the /api-docs endpoint.
 *
 * The class uses the Spring Framework's Web MVC framework to handle HTTP requests and generate responses.
 * It uses the @Autowired annotation to automatically inject a MockMvc object, which is used to perform HTTP requests in the unit tests.
 */
public class DocumentationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    /**
     * This method handles GET requests to the /api-docs endpoint.
     * It retrieves the API documentation and returns it in the HTTP response.
     *
     * The method is annotated with @Test, indicating that it's a unit test method.
     * It uses the MockMvc.perform method to perform a GET request to the /api-docs endpoint.
     * It then checks the status of the response using the andExpect method.
     *
     * The method throws an Exception if an error occurs during the request.
     */
    @Test
    public void testGetDocumentation() throws Exception {
        mockMvc.perform(get("/api-docs"))
                .andExpect(status().isOk());
    }
}