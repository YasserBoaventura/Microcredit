package com.Microcredito.Notificacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.Microcredito.entity.Notificao;

public interface NotificacaoRepository extends JpaRepository<Notificao, Long> {

    List<Notificao> findByClienteIdAndLidaFalseOrderByDataEnvioDesc(Long clienteId);
    List<Notificao> findByUsuarioIdAndLidaFalse(Long usuarioId);
    List<Notificao> findByEnviadoFalse();
    @Modifying
    @Query("UPDATE Notificao n SET n.lida = true WHERE n.id = :id")
    void marcarComoLida(Long id);
    long countByClienteIdAndLidaFalse(Long clienteId);
}
