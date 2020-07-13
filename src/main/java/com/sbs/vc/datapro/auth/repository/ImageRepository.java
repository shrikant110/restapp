package com.sbs.vc.datapro.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbs.vc.datapro.auth.model.ImageModel;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long>{
	
	public ImageModel findOneByUsername(String username);
	
	
}

