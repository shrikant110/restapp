package com.cardreader.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@PropertySources({ @PropertySource("classpath:application_env.properties") })
@Service
public class ClientEmails {

	

	@Autowired
	Environment environment;

	

}
