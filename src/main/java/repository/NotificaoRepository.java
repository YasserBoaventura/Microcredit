package repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Microcredito.entity.Notificao;
import com.Microcredito.enums.TipoNotificaco;

@Repository 
public interface NotificaoRepository extends JpaRepository<Notificao, Long>{


    List<Notificao> findByClienteIdAndLidaFalseOrderByDataEnvioDesc(Long clienteId);
    
    List<Notificao> findByUsuarioIdAndLidaFalse(Long usuarioId);
     
    @Modifying
    @Query("UPDATE Notificacao n SET n.lida = true, n.dataLeitura = CURRENT_TIMESTAMP WHERE n.id = :id")
    void marcarComoLida(Long id);
    
    Long countByClienteIdAndLidaFalse(Long clienteId);
    
    List<Notificao> findByTipoAndEnviadoFalse(TipoNotificaco tipo);
}
