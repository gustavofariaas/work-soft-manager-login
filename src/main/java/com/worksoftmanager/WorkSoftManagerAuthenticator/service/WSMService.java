package com.worksoftmanager.WorkSoftManagerAuthenticator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worksoftmanager.WorkSoftManagerAuthenticator.DTO.SessionDTO;
import com.worksoftmanager.WorkSoftManagerAuthenticator.DTO.UserDTO;
import com.worksoftmanager.WorkSoftManagerAuthenticator.entity.SessionEntity;
import com.worksoftmanager.WorkSoftManagerAuthenticator.entity.UsersEntity;
import com.worksoftmanager.WorkSoftManagerAuthenticator.model.LoginRequest;
import com.worksoftmanager.WorkSoftManagerAuthenticator.model.UserSession;
import com.worksoftmanager.WorkSoftManagerAuthenticator.persistence.WSMRepository;
import com.worksoftmanager.WorkSoftManagerAuthenticator.persistence.WSMSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;


@Service
public class WSMService {

    @Autowired
    WSMRepository repository;

    @Autowired
    WSMSessionRepository sessionRepository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WSMService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Boolean getCredentials(LoginRequest request) {

        UserDTO obj = repository.findPassword(request.getUsername());
        if(ObjectUtils.isEmpty(obj)) {
            return false;
        }

        if(obj.getPassword().equals(request.getPassword())) {
            return true;
        }

        return false;

    }

    public void createUserSession(UserSession userSession) {
        SessionEntity sessionDTO = new SessionEntity(userSession.getUsername(), userSession.getToken());
        sessionDTO.setTimestamp(Timestamp.from(Instant.now()));
        sessionRepository.save(sessionDTO);
    }

    public Boolean getUserSession(UserSession userSession) {
        SessionDTO sessionDTO = sessionRepository.findFirstByUsername(userSession.getUsername());
        if(ObjectUtils.isEmpty(sessionDTO)) {
            return false;
        }

        return true;
    }

    public int updateToken(UserSession session) {
        return sessionRepository.updateUserSession(session.getUsername(), session.getToken());
    }

    public void deleteSession(UserSession session) {
        String username = session.getUsername();
        String sql = "DELETE FROM sessions WHERE username = " + "'" + username + "'" + ";";
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }

    @Modifying
    public void createUserLogin(LoginRequest loginRequest) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername(loginRequest.getUsername());
        usersEntity.setPassword(loginRequest.getPassword());
        usersEntity.setTimestamp(Timestamp.from(Instant.now()));
        repository.save(usersEntity);
    }
}
