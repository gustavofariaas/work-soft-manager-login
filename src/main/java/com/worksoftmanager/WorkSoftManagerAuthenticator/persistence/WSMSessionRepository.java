package com.worksoftmanager.WorkSoftManagerAuthenticator.persistence;

import com.worksoftmanager.WorkSoftManagerAuthenticator.DTO.SessionDTO;
import com.worksoftmanager.WorkSoftManagerAuthenticator.DTO.UserDTO;
import com.worksoftmanager.WorkSoftManagerAuthenticator.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface WSMSessionRepository extends JpaRepository<SessionEntity, Long> {

    SessionDTO findFirstByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("UPDATE SessionEntity e SET e.token = :token WHERE e.username = :username")
    int updateUserSession(@Param("username") String username, @Param("token") String token);
}
