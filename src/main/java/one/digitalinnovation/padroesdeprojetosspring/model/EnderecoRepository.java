package one.digitalinnovation.padroesdeprojetosspring.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String>{
	
	Optional<Endereco> findById(String cep);

}
