package it.be.energy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import it.be.energy.repository.FatturaRepository;
import it.be.energy.service.FatturaService;

@SpringBootTest
@AutoConfigureMockMvc
public class TestFattura {
	@Autowired
	private MockMvc mockMvc;

	@Mock
	FatturaRepository fatturaRepo;

	@Mock
	FatturaService fatturaService;

	
	@Test
	@WithMockUser
	final void testGetAll() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/fatture")).andDo(print()).andExpect(status().isOk());	
	}
	
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testPost() throws Exception {
		String body = "{\r\n"
				+ "  \"numeroFattura\": 15,\r\n"
				+ "  \"anno\": 2020,\r\n"
				+ "  \"data\": \"2022-03-16T00:00:00\",\r\n"
				+ "  \"importo\": 10000,\r\n"
				+ "  \"statoFattura\": {\r\n"
				+ "    \"id\": 1\r\n"
				+ "  },\r\n"
				+ "  \"cliente\": {\r\n"
				+ "    \"id\": 2\r\n"
				+ "  }\r\n"
				+ "}";
		
		
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/fattura")
	    			.contentType(MediaType.APPLICATION_JSON)
	    			.content(body))
	    			.andExpect(status().isCreated()).andReturn();
	}
	
	
	
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testPut() throws Exception {
		String body = "{\r\n"
				+ "  \"numeroFattura\": 0,\r\n"
				+ "  \"anno\": 2010,\r\n"
				+ "  \"data\": \"2022-03-15T00:00:00\",\r\n"
				+ "  \"importo\": 0,\r\n"
				+ "  \"statoFattura\": {\r\n"
				+ "    \"id\": 1\r\n"
				+ "  },\r\n"
				+ "  \"cliente\": {\r\n"
				+ "    \"id\": 2\r\n"
				+ "    }\r\n"
				+ "}";
		
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/fattura/2")
	    			.contentType(MediaType.APPLICATION_JSON)
	    			.content(body))
	    			.andExpect(status().isOk()).andReturn();
	}
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testDeleteById() throws Exception {
		this.mockMvc.perform(delete("http://localhost:8080/api/fattura/1")).andDo(print()).andExpect(status().isOk());	
	}
	@Test
	@WithMockUser
	final void testfindById() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/fattura/2")).andDo(print()).andExpect(status().isOk());	
	}
	
	
	/*
	 * filtri tests
	 */
	
	@Test
	@WithMockUser
	final void testFindByClienteRagioneSociale() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/fatturaragionesociale/Bianca")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindByStatoFattura() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/statofattura/1")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindByData() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/fatturadata/2022-03-16")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindByAnno() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/fatturaanno/2010")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindByImportoBetween() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/fatturaimporto/10/50000")).andDo(print()).andExpect(status().isOk());	
	}
	

	

}
