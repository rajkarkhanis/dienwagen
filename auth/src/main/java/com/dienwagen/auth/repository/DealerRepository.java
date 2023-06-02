package com.dienwagen.auth.repository;

import com.dienwagen.auth.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerRepository extends JpaRepository<Dealer, Integer> {
}