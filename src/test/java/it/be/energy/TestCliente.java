package it.be.energy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;
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
import it.be.energy.repository.ClienteRepository;
import it.be.energy.service.ClienteService;

@SpringBootTest
@AutoConfigureMockMvc
public class TestCliente {
	@Autowired
	private MockMvc mockMvc;

	@Mock
	ClienteRepository clienteRepo;

	@Mock
	ClienteService clienteService;



	@Test
	@WithMockUser
	final void testGetAllWeb() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/web/mostraelencoclienti")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testGetAll() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clienti")).andDo(print()).andExpect(status().isOk());	
	}
	
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testPost() throws Exception {
		String body = "{\r\n"
				+ "  \"ragioneSociale\": \"string\",\r\n"
				+ "  \"partitaIva\": \"string\",\r\n"
				+ "  \"email\": \"string\",\r\n"
				+ "  \"dataInserimento\": \"2022-03-15\",\r\n"
				+ "  \"dataUltimoContatto\": \"2022-03-15\",\r\n"
				+ "  \"fatturatoAnnuale\": 0,\r\n"
				+ "  \"pec\": \"string\",\r\n"
				+ "  \"telefono\": \"string\",\r\n"
				+ "  \"emailContatto\": \"string\",\r\n"
				+ "  \"nomeContatto\": \"string\",\r\n"
				+ "  \"cognomeContatto\": \"string\",\r\n"
				+ "  \"telefonoContatto\": \"string\",\r\n"
				+ "  \"tipo\": \"PA\",\r\n"
				+ "  \"sedeLegale\": {\r\n"
				+ "      \"id\": 2\r\n"
				+ "  },\r\n"
				+ "  \"sedeOperativa\": {\r\n"
				+ "      \"id\":3\r\n"
				+ "  },\r\n"
				+ "  \"fatture\": [\r\n"
				+ "  ]\r\n"
				+ "}";
		
		
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/cliente")
	    			.contentType(MediaType.APPLICATION_JSON)
	    			.content(body))
	    			.andExpect(status().isCreated()).andReturn();
	}
	
	
	
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testPut() throws Exception {
		String body = "{\r\n"
				+ "  \"ragioneSociale\": \"Bianca\",\r\n"
				+ "  \"partitaIva\": \"string\",\r\n"
				+ "  \"email\": \"string\",\r\n"
				+ "  \"dataInserimento\": \"2022-03-15\",\r\n"
				+ "  \"dataUltimoContatto\": \"2022-03-15\",\r\n"
				+ "  \"fatturatoAnnuale\": 0,\r\n"
				+ "  \"pec\": \"string\",\r\n"
				+ "  \"telefono\": \"string\",\r\n"
				+ "  \"emailContatto\": \"string\",\r\n"
				+ "  \"nomeContatto\": \"string\",\r\n"
				+ "  \"cognomeContatto\": \"string\",\r\n"
				+ "  \"telefonoContatto\": \"string\",\r\n"
				+ "  \"tipo\": \"PA\",\r\n"
				+ "  \"sedeLegale\": {\r\n"
				+ "      \"id\": 2\r\n"
				+ "  },\r\n"
				+ "  \"sedeOperativa\": {\r\n"
				+ "      \"id\":3\r\n"
				+ "  },\r\n"
				+ "  \"fatture\": [\r\n"
				+ "  ]\r\n"
				+ "}";
		
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/cliente/2")
	    			.contentType(MediaType.APPLICATION_JSON)
	    			.content(body))
	    			.andExpect(status().isOk()).andReturn();
	}
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testDeleteById() throws Exception {
		this.mockMvc.perform(delete("http://localhost:8080/api/cliente/1")).andDo(print()).andExpect(status().isOk());	
	}
	@Test
	@WithMockUser
	final void testfindById() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/cliente/2")).andDo(print()).andExpect(status().isOk());	
	}
	
	
	/*
	 * ordinamento tests
	 */
	@Test
	@WithMockUser
	final void testFindAllByOrderByRagioneSociale() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clienteragionesociale")).andDo(print()).andExpect(status().isOk());	
	}
	@Test
	@WithMockUser
	final void testFindAllByOrderByDataUltimoContatto() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clientedataultimocontatto")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindAllByOrderByDataInserimento() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clientedatainserimento")).andDo(print()).andExpect(status().isOk());	
	}
	@Test
	@WithMockUser
	final void testFindAllByOrderByFatturatoAnnuale() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clientefatturatoannuale")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindAllByOrderBySedeLegaleComuneProvincia() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clientesedelegale")).andDo(print()).andExpect(status().isOk());	
	}
	
	
	/*
	 * filtri tests
	 */
	
	@Test
	@WithMockUser
	final void testFindByRagioneSociale() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clienteragionesocialefilter/Bianca")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindByFatturatoAnnualeGreaterThanEqual() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clientefatturatoannualemaggiore/10000")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindByFatturatoAnnualeLessThanEqual() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clientefatturatoannualeminore/5000000")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindByFatturatoAnnualeBetween() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clientefatturatoannualetra/5000/5000000")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindByDataInserimentoGreaterThanEqual() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clientedatainserimentomaggiore/2019-02-21")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindByDataInserimentoLessThanEqual() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clientedatainserimentominore/2022-03-17")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindByDataInserimentoBetween() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clientedatainserimentotra/2019-02-21/2022-03-17")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindByDataUltimoContattoGreaterThanEqual() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clientedataultimocontattomaggiore/2019-02-21")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindByDataUltimoContattoLessThanEqual() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clientedataultimocontattominore/2022-03-17")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindByDataUltimoContattoBetween() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/api/clientedataultimocontattotra/2019-02-21/2022-03-17")).andDo(print()).andExpect(status().isOk());	
	}
	
	
	
	/*
	 * web tests
	 */
	
	@Test
	@WithMockUser
	final void testFindByOrderByDataUltimoContattoWeb() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/web/ordinadataultimocontatto")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindAllByOrderByDataUltimoContattoWeb() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/web/ordinadataultimocontatto")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void testFindAllByOrderByDataInserimentoWeb() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/web/ordinadatainserimento")).andDo(print()).andExpect(status().isOk());	
	}
	@Test
	@WithMockUser
	final void testFindAllByOrderByFatturatoAnnualeWeb() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/web/ordinafatturatoannuale")).andDo(print()).andExpect(status().isOk());	
	}
	


	

}
