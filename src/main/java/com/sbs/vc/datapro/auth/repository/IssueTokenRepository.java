package com.sbs.vc.datapro.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbs.vc.datapro.auth.model.IssueToken;

public interface IssueTokenRepository  extends JpaRepository<IssueToken, Long> {
	IssueToken findByToken(String encryptedToken);
}

