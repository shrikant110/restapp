package com.sbs.vc.file;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author shrikant.kushwaha
 * @ConfigurationProperties using which you can automatically bind the properties defined in the application.properties
 * @ConfigurationProperties(prefix = "file") annotation does its job on application startup and binds all the properties with prefix file to the corresponding fields of the POJO class
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;
    
    private String fundPdf;

    private String stockPdf;
    
    private String marketPdf;
    
    private String industryPdf;
    
    private String watchlistPdf;
   
}