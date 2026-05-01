package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;
import com.Microcredito.entity.ExtratoTransacao;
import com.Microcredito.enums.TipoTransacao;
@Repository
public  interface ExtratoTransacoRepository extends JpaRepository<ExtratoTransacao, Long> {

	 List<ExtratoTransacao> findByContratoIdOrderByDataTransacaoDesc(Long contratoId);
	    List<ExtratoTransacao> findByContratoIdAndTipoTransacao(Long contratoId, TipoTransacao tipo);
	    List<ExtratoTransacao> findByDataTransacaoBetween(LocalDateTime inicio, LocalDateTime fim);
	}