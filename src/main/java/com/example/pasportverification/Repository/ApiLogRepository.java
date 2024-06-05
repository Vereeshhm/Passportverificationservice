package com.example.pasportverification.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pasportverification.Logentities.ApiLog;

public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {

}
