package com.sbs.vc.scan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbs.vc.scan.domain.ScanMaster;

@Repository
public interface ScanMasterRepository extends JpaRepository<ScanMaster, Long>{

}
