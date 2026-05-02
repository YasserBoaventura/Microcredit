package com.Microcredito.Pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microcredito.entity.Pagamento;
@Repository 
public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {

	
}
