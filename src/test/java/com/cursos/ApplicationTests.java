package com.cursos;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ApplicationTests {

	@Autowired
	MockMvc mock;
	
	@Test
	@Order(0)
	void testDeleteCurso() throws Exception{
		mock.perform(delete("/curso/CSS"));
	}
	// Devolvemos el listado de cursos
	@Test
	@Order(1)
	void testListCursos() throws Exception {
		mock.perform(get("/lista")).andDo(print());
	}
	
	@Test
	@Order(3)
	void testInsertarCurso() throws Exception{
		mock.perform(
				post("/curso")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\"nombre\": \"Angular\",\"duracion\": 4,\"horario\": \"10:00 - 14:00\"}"))
		.andDo(print());
	}
	
	@Test
	@Order(4)
	void testActualizarCurso() throws Exception{
		mock.perform(
				put("/curso")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\"nombre\": \"Angular\",\"duracion\": 4,"
						+ "\"horario\": \"16:00 - 20:00\"}"))
				.andDo(print());	
		}



}
