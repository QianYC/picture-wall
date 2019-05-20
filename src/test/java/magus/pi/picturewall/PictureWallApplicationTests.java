package magus.pi.picturewall;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = IndexController.class)
public class PictureWallApplicationTests {

    @Autowired
    WebApplicationContext context;

    MockMvc mvc;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void contextLoads() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/home");
        MvcResult result = mvc.perform(builder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

}
