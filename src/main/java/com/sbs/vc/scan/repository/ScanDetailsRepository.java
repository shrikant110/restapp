package com.sbs.vc.scan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbs.vc.scan.domain.ScanDetailMaster;

@Repository
public interface ScanDetailsRepository extends JpaRepository<ScanDetailMaster, Long>{

}
