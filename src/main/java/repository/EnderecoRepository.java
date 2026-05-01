package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microcredito.entity.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    List<Endereco> findByClienteId(Long clienteId);
    List<Endereco> findByClienteIdAndPrincipalTrue(Long clienteId);
    void deleteByClienteId(Long clienteId);

}
