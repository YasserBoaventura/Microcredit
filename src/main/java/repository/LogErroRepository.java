package repository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Microcredito.entity.LogErro;
@Repository
public interface LogErroRepository extends JpaRepository<LogErro, Long>{

	   List<LogErro> findByDataErroBetween(LocalDateTime inicio, LocalDateTime fim);
	    Page<LogErro> findByResolvidoFalse(Pageable pageable);
	    @Modifying
	    @Query("UPDATE LogErro l SET l.resolvido = true WHERE l.id = :id")
	    void marcarComoResolvido(Long id);
	    long countByResolvidoFalse();
	
}
