package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import com.Microcredito.entity.Parcela;
import com.Microcredito.enums.StatusParcela;
@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long> {

    List<Parcela> findByContratoId(Long contratoId);
    List<Parcela> findByContratoIdAndStatus(Long contratoId, StatusParcela status);
    @Query("SELECT p FROM Parcela p WHERE p.dataVencimento < :data AND p.status = 'PENDENTE'")
    List<Parcela> findParcelasVencidas(LocalDate data);
    @Modifying
    @Query("UPDATE Parcela p SET p.status = 'VENCIDO' WHERE p.id = :id")
    void marcarComoVencida(Long id);
    BigDecimal sumValorOriginalByContratoIdAndStatus(Long contratoId, StatusParcela status);
    long countByStatus(StatusParcela status);
}
