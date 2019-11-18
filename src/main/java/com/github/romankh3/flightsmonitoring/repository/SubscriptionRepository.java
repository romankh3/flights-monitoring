package com.github.romankh3.flightsmonitoring.repository;

import com.github.romankh3.flightsmonitoring.repository.entity.Subscription;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUsername(String username);
}
