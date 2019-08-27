package demo.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.file.Files;

import static java.util.Objects.requireNonNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void submitDog() throws Exception {
        var resource = getClass().getClassLoader().getResource("ExpectedDogResponse.txt");
        String expectedResponse = Files.readString((new File(requireNonNull(resource).getFile()).toPath()));

        this.mockMvc.perform(post("/animal")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"type\":\"dog\",\"name\":\"Rex\",\"barks-per-minute\":10}"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void submitCat() throws Exception {
        var resource = getClass().getClassLoader().getResource("ExpectedCatResponse.txt");
        String expectedResponse = Files.readString((new File(requireNonNull(resource).getFile()).toPath()));

        this.mockMvc.perform(post("/animal")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"type\":\"cat\",\"name\":\"Felix\",\"lives\":3}"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}