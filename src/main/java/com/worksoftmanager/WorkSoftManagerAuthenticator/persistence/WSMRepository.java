package com.worksoftmanager.WorkSoftManagerAuthenticator.persistence;

import com.worksoftmanager.WorkSoftManagerAuthenticator.DTO.UserDTO;
import com.worksoftmanager.WorkSoftManagerAuthenticator.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface WSMRepository extends JpaRepository<UsersEntity, Long> {

    @Query(value = "SELECT new com.worksoftmanager.WorkSoftManagerAuthenticator.DTO.UserDTO(u.username, u.password) FROM UsersEntity u WHERE u.username = :username")
    UserDTO findPassword(@Param("username") String username);

}
