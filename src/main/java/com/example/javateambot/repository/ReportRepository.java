package com.example.javateambot.repository;


import com.example.javateambot.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report , Long> {
}
