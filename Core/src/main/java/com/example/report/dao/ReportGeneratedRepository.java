package com.example.report.dao;
import com.example.report.entity.ReportGenerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportGeneratedRepository extends JpaRepository<ReportGenerated, Long> {
}

