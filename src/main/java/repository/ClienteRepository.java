package repository;

import java.util.*;
import java.time.*;
import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.Optional;

import org.hibernate.query.Page;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Microcredito.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpfCnpj(String cpfCnpj);
     
    Optional<Cliente> findByEmail(String email);
    
    @Query("SELECT c FROM Cliente c WHERE c.nomeRazaoSocial LIKE %:nome%")
    List<Cliente> buscarPorNome(@Param("nome") String nome);
     
    @Query("SELECT c FROM Cliente c WHERE c.scoreCredito >= :scoreMinimo AND c.rendaMensal >= :rendaMinima")
    List<Cliente> findClientesElegiveis(@Param("scoreMinimo") Integer scoreMinimo, 
                                         @Param("rendaMinima") BigDecimal rendaMinima);
    
    @Query("SELECT COUNT(c) FROM Cliente c WHERE c.dataCadastro BETWEEN :inicio AND :fim")
    Long countClientesPorPeriodo(@Param("inicio") LocalDateTime inicio, 
                                  @Param("fim") LocalDateTime fim);
    
    org.springframework.data.domain.Page<Cliente> findByAtivoTrue(Pageable pageable);
    
    List<Cliente> findByRendaMensalGreaterThanEqual(BigDecimal renda);

}
