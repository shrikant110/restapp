package com.sbs.vc.datapro.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbs.vc.datapro.email.model.MailDomain;



@Repository
public interface MailDomainRepository extends JpaRepository<MailDomain, Long> {

}
