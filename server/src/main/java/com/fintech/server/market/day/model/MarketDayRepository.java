package com.fintech.server.market.day.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketDayRepository extends JpaRepository<MarketDay, Long> {
}
