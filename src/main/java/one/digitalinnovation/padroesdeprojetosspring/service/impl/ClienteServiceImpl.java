package one.digitalinnovation.padroesdeprojetosspring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.padroesdeprojetosspring.model.Cliente;
import one.digitalinnovation.padroesdeprojetosspring.model.ClienteRepository;
import one.digitalinnovation.padroesdeprojetosspring.model.Endereco;
import one.digitalinnovation.padroesdeprojetosspring.model.EnderecoRepository;
import one.digitalinnovation.padroesdeprojetosspring.service.ClienteService;
import one.digitalinnovation.padroesdeprojetosspring.service.ViaCepService;
@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ViaCepService viaCep;
	
	private void salvarClientePorCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() ->{
			Endereco novoEndereco = viaCep.consultaCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}
	
	@Override
	public List<Cliente> buscarTodos() {
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes;
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}

	@Override
	public void inserir(Cliente cliente) {
		salvarClientePorCep(cliente);	
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clienteAtual = clienteRepository.findById(id); 
		if(clienteAtual.isPresent()) {
			salvarClientePorCep(clienteAtual.get());
		}
	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}
}
