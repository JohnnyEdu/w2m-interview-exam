package org.w2m.demo.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.w2m.demo.controller.SuperHeroController;


@AutoConfigureMockMvc
@SpringBootTest
class SuperHeroIntegrationTest {

    @Autowired
    SuperHeroController superHeroController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void retrieveAllSuperHeroesNotEmpty() throws Exception {

        var requestBuilder = MockMvcRequestBuilders.get("/api/v1/super-heroes")
                .contentType("application/json");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{\"id\":1,\"name\":\"Dr. Strange\",\"super_powers\":[{\"id\":5,\"name\":\"TELEPATHY\"},{\"id\":4,\"name\":\"TELEKINESIS\"}]},{\"id\":2,\"name\":\"Superman\",\"super_powers\":[{\"id\":2,\"name\":\"BIONIC_VISION\"},{\"id\":3,\"name\":\"FLY\"},{\"id\":1,\"name\":\"STRENGTH\"}]},{\"id\":3,\"name\":\"Spiderman\",\"super_powers\":[{\"id\":1,\"name\":\"STRENGTH\"}]}]"))
                .andReturn();//TODO refactor to read Json file, kotlin class or groovy file (multi line string)
    }
}
