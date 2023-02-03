package one.digitalinnovation.padroesdeprojetosspring.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import one.digitalinnovation.padroesdeprojetosspring.model.Endereco;

@FeignClient(name="viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {
	
	@GetMapping("/{cep}/json/")
	Endereco consultaCep(@PathVariable String cep);

}
