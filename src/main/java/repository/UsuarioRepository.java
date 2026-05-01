package repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Microcredito.auth.Usuario;
import com.Microcredito.enums.RoleUsuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{


    Optional<Usuario> findByEmail(String email);
    
    Optional<Usuario> findByClienteId(Long clienteId);
    
    List<Usuario> findByRole(RoleUsuario role);
    
    @Modifying
    @Query("UPDATE Usuario u SET u.tentativasLogin = u.tentativasLogin + 1, u.ultimoLogin = CURRENT_TIMESTAMP WHERE u.email = :email")
    void incrementarTentativasLogin(@Param("email") String email);
    
    @Modifying
    @Query("UPDATE Usuario u SET u.tentativasLogin = 0, u.bloqueadoAte = null WHERE u.email = :email")
    void resetarTentativasLogin(@Param("email") String email);
    
    @Modifying
    @Query("UPDATE Usuario u SET u.bloqueadoAte = :bloqueio WHERE u.email = :email")
    void bloquearUsuario(@Param("email") String email, @Param("bloqueio") LocalDateTime bloqueio);
}

