package hr.martinovic.FakeStagram.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import hr.martinovic.FakeStagram.model.Image;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ImageControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
    public void invalidImageSubmit() throws Exception {
        this.mockMvc
                .perform(
                        post("/images/new")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .with(csrf())
                            .with(user("admin").password("adminpass").roles("USER", "ADMIN"))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("newImageForm"));

    }

	@Test
	public void validImageSubmit() throws Exception {
		this.mockMvc
				.perform(
						post("/images/new")
								.param("name", "Test")
								.param("fakeness", "2.0")
								.param("type", Image.Type.NORMAL.toString())
								.param("file", "test")
								.contentType(MediaType.APPLICATION_FORM_URLENCODED)
								.with(csrf())
								.with(user("admin").password("adminpass").roles("USER", "ADMIN"))
				)
				.andExpect(status().isOk())
				.andExpect(view().name("newImageUploaded"));

	}
}