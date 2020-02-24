package hr.martinovic.FakeStagram.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import hr.martinovic.FakeStagram.model.Image;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ImageRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void findAll() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/image")
                            .with(user("basic_user").password("studentpass").roles("USER", "ADMIN"))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void save() throws Exception {
        Map<String, String> imageMap = new HashMap<>();
        imageMap.put("name", "Test");
        imageMap.put("fakeness", "2.0");
		imageMap.put("type", Image.Type.NORMAL.toString());
		imageMap.put("file", "test");

        this.mockMvc
                .perform(
                        post("/api/image")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(imageMap))
                                .with(csrf())
                                .with(user("admin").password("adminpass").roles("USER", "ADMIN"))
                )
                .andExpect(status().isCreated());
    }

}