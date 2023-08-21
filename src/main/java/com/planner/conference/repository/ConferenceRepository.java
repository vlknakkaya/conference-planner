package com.planner.conference.repository;

import com.planner.conference.model.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
}
