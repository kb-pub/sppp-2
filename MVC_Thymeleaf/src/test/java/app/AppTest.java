package app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AppTest {

}

@WebMvcTest(VehicleController.class)
public class ControllerTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private VehicleService vehicleService;
    @Test
    public void test() throws Exception {
        given(vehicleService.getDetails("id"))
                .willReturn(new Vehicle("Honda"));
        this.mvc.perform(get("/id/vehicle"))
                .andExpect(status().isOk())
                .andExpect(content().string("Honda"));
    }
}