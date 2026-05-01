package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

import com.Microcredito.entity.ConfiguracaoGeral;
import com.Microcredito.entity.ExtratoTransacao;
import com.Microcredito.enums.TipoTransacao;

@Repository 
public interface ConfiguacaoGeralRepository extends JpaRepository<ConfiguracaoGeral, Long> {
 List<ExtratoTransacao> findByContratoIdOrderByDataTransacaoDesc(Long contratoId);
    List<ExtratoTransacao> findByContratoIdAndTipoTransacao(Long contratoId, TipoTransacao tipo);
    List<ExtratoTransacao> findByDataTransacaoBetween(LocalDateTime inicio, LocalDateTime fim);

}
