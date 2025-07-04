package com.bank.repository;

import com.bank.model.StatementEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatementEntryRepository extends JpaRepository<StatementEntry, Long> {
    List<StatementEntry> findByAccountIdOrderByDateAsc(Long accountId);
}