package br.com.Attornatus.desafio.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URI;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.Attornatus.desafio.DTO.endereco.EnderecoDTO;
import br.com.Attornatus.desafio.DTO.pessoa.PessoaDTO;
import br.com.Attornatus.desafio.model.Endereco;
import jakarta.servlet.ServletException;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class PessoaControlerTest {
	@Autowired
	private MockMvc moc;

	@Autowired
	private JacksonTester<PessoaDTO> json;

	@Autowired
	private JacksonTester<EnderecoDTO> sonj;

	
	//Pessoa
	
	@Test
	void devePegar201PorSalvarPessoa() throws Exception {
		URI uri = new URI("/pessoa");

		var endereco = new Endereco("logradouro", "cep", "numero", "cidade", false);

		var jj = json.write(new PessoaDTO("Cleison", LocalDate.now(), endereco)).getJson();

		moc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jj))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	void devePegar200PorlistarPessoas() throws Exception {
		URI uri = new URI("/pessoa");

		var endereco = new Endereco("logradouro", "cep", "numero", "cidade", false);

		var jj = json.write(new PessoaDTO("Cleison", LocalDate.now(), endereco)).getJson();

		var jj2 = json.write(new PessoaDTO("Cleidecida", LocalDate.now(), endereco)).getJson();

		moc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jj));
		moc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jj2));

		moc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	void devePegar200PorConsultarPessoas() throws Exception {
		URI uri = new URI("/pessoa");

		var endereco = new Endereco("logradouro", "cep", "numero", "cidade", false);

		var jj = json.write(new PessoaDTO("Cleison", LocalDate.now(), endereco)).getJson();

		var jj2 = json.write(new PessoaDTO("Cleidecida", LocalDate.now(), endereco)).getJson();

		moc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jj));
		moc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jj2));

		moc.perform(MockMvcRequestBuilders.get("/pessoa/{id}", 2)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	void devePegar200PorEditarPessoas() throws Exception {
		URI uri = new URI("/pessoa");

		var endereco = new Endereco("logradouro", "cep", "numero", "cidade", false);

		var jj = json.write(new PessoaDTO("Cleison", LocalDate.now(), endereco)).getJson();

		var jj2 = json.write(new PessoaDTO("Cleidecida", LocalDate.now(), endereco)).getJson();

		moc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jj));
		moc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jj2));

		var jj_2 = json.write(new PessoaDTO("Cleison Santos", LocalDate.now(), endereco)).getJson();

		moc.perform(MockMvcRequestBuilders.put("/pessoa/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jj_2))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	//Endereco
	
	@Test
	void devePegar201PorSalvarEndereco() throws Exception {
		var endereco = new Endereco("logradouro", "cep", "numero", "cidade", false);

		var jj = json.write(new PessoaDTO("Cleison", LocalDate.now(), endereco)).getJson();

		moc.perform(MockMvcRequestBuilders.post("/pessoa")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jj));

		var jjEnd = sonj.write(new EnderecoDTO("logradouro", "cep", "numero", "cidade", false)).getJson();
		
		moc.perform(MockMvcRequestBuilders.post("/pessoa/endereco/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jjEnd))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	void devePegar200PorListarEnderecosPessoa() throws Exception {
		var endereco = new Endereco("logradouro", "cep", "numero", "cidade", false);

		var jj = json.write(new PessoaDTO("Cleison", LocalDate.now(), endereco)).getJson();

		moc.perform(MockMvcRequestBuilders.post("/pessoa")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jj));

		moc.perform(MockMvcRequestBuilders.get("/pessoa/endereco/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	void devePegar200PorListarEnderecoPrincipal() throws Exception {
		var endereco = new Endereco("logradouro", "cep", "numero", "cidade", true);

		var jj = json.write(new PessoaDTO("Cleison", LocalDate.now(), endereco)).getJson();

		moc.perform(MockMvcRequestBuilders.post("/pessoa")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jj));

		var jjEnd = sonj.write(new EnderecoDTO("logradouro", "cep", "numero", "cidade", true)).getJson();

		moc.perform(MockMvcRequestBuilders.post("/pessoa/endereco/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jjEnd));
		
		moc.perform(MockMvcRequestBuilders
				.get("/pessoa/endereco/principal/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	//Exceptions
	
	@Test
	void devePegarExcecaoPorNaoAcharEnderecoPrincipal() throws Exception {
		var endereco = new Endereco("logradouro", "cep", "numero", "cidade", false);

		var jj = json.write(new PessoaDTO("Cleison", LocalDate.now(), endereco)).getJson();

		moc.perform(MockMvcRequestBuilders.post("/pessoa")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jj));	
		
		assertThrows(ServletException.class, () -> moc.perform(MockMvcRequestBuilders
													.get("/pessoa/endereco/principal/{id}", 1)
													.contentType(MediaType.APPLICATION_JSON)));
	}
		
	@Test
	void devePegarExcecaoPorSalvarDoisEnderecosComoFavorito() throws Exception {
		var endereco = new Endereco("logradouro", "cep", "numero", "cidade", true);

		var jj = json.write(new PessoaDTO("Cleison", LocalDate.now(), endereco)).getJson();

		moc.perform(MockMvcRequestBuilders.post("/pessoa")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jj));

		var jjEnd = sonj.write(new EnderecoDTO("logradouro", "cep", "numero", "cidade", true)).getJson();
		
		assertThrows(ServletException.class, () -> moc.perform(MockMvcRequestBuilders
				 									.post("/pessoa/endereco/{id}", 1)
													.contentType(MediaType.APPLICATION_JSON)
													.content(jjEnd)));
	}

}
