package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.TutorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
class TutorControllerTest {

    @MockBean
    private TutorService service;

    @Autowired
    private MockMvc mvc;

    @Test
    void deveriaDevolverCodigo200ParaRequisicaoDeCadastrarTutor() throws Exception {
        //Arrange
        String json = """
                {
                    "nome": "Daniel",
                    "telefone": "(19)0000-0010",
                    "email": "email@example.com.br"
                }
                """;
        //Act
        MockHttpServletResponse response = mvc.perform(
                post("/tutores")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        assertEquals(200,response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo400ParaRequisicaoCadastrarTutorDadosInvalidos() throws Exception {
        //Arrange
        String json = """
                {
                    "nome": "Daniel",
                    "telefone": "(19)0000-0010",
                    "email": "email@example.com.br"
                }
                """;

        //Act
        MockHttpServletResponse response = mvc.perform(
                post("/tutores")
                        .contentType(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        assertEquals(400,response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo200ParaRequisicaoDeAtualizarTutor() throws Exception {
        //Arrange
        String json = """
                {
                    "id" : "1",
                    "nome": "Daniel",
                    "telefone": "(19)0000-0010",
                    "email": "email@example.com"
                }
                """;

        //Act
        MockHttpServletResponse response = mvc.perform(
                put("/tutores")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        assertEquals(200,response.getStatus());
    }

    @Test
    void deveriaDevolverCodigo400ParaRequisicaoDeAtualizarTutor() throws Exception {
        //Arrange
        String json = """
                {
                    "id" : "2",
                    "nome": "Rosa",
                    "telefone": "(19)0000-0010",
                    "email":"email@example.com.br"
                }
                """;

        //Act
        MockHttpServletResponse response = mvc.perform(
                put("/tutores")
                        .contentType(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        assertEquals(400,response.getStatus());
    }

}