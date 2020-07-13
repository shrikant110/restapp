package com.cardreader.dashboard.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardreader.dashboard.entities.Module;

@Repository
public interface DashBoardRepository extends JpaRepository<Module, Long> {

}
