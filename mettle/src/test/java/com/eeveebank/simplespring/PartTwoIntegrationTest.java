package com.eeveebank.simplespring;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"test", "part2"})
@AutoConfigureMockMvc
public class PartTwoIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testReturnsCorrectCount() throws Exception {
        mockMvc.perform(
                        get("/client-cars")
                                .queryParam("colour", "RED")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json("2"));
    }

    @Test
    public void testNoCarsMatched() throws Exception {
        mockMvc.perform(
                        get("/client-cars")
                                .queryParam("colour", "WHITE")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json("0"));
    }
}
