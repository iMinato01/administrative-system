package com.gg.administrative_system_backend.pettycash.repository;

import com.gg.administrative_system_backend.pettycash.entity.pettycash.PettyCash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PettyCashRepository extends JpaRepository<PettyCash, Long> {
}
