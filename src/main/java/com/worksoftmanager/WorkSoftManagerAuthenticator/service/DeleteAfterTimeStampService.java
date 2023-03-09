package com.worksoftmanager.WorkSoftManagerAuthenticator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DeleteAfterTimeStampService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DeleteAfterTimeStampService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    @Scheduled(fixedRate = 120000)
    public void deleteOldSessions() {
        String sql = "DELETE FROM session WHERE created_at < NOW() - INTERVAL '10 minutes';";
        jdbcTemplate.update(sql);
    } */
}