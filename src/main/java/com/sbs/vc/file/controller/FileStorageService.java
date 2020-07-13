package com.sbs.vc.file.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sbs.vc.datapro.exceptions.FileStorageException;
import com.sbs.vc.datapro.exceptions.MyFileNotFoundException;
import com.sbs.vc.datapro.exceptions.ProcessFailedException;
import com.sbs.vc.file.FileStorageProperties;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;
    
    private final Path fundfileStorageLocation;
    
    private final Path marketfileStorageLocation;
    
    private final Path industryfileStorageLocation;
    
    private final Path stockfileStorageLocation;
    
    private final Path watchListfileStorageLocation;

    
    

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        this.fundfileStorageLocation = Paths.get(fileStorageProperties.getFundPdf()).toAbsolutePath().normalize();
        this.stockfileStorageLocation = Paths.get(fileStorageProperties.getStockPdf()).toAbsolutePath().normalize();
        this.marketfileStorageLocation = Paths.get(fileStorageProperties.getMarketPdf()).toAbsolutePath().normalize();
        this.industryfileStorageLocation = Paths.get(fileStorageProperties.getIndustryPdf()).toAbsolutePath().normalize();
        this.watchListfileStorageLocation= Paths.get(fileStorageProperties.getWatchlistPdf()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
            Files.createDirectories(this.fundfileStorageLocation);
            Files.createDirectories(this.stockfileStorageLocation);
            Files.createDirectories(this.watchListfileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    
   

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
    
    public Resource loadStockFileAsResource(String fileName) throws ProcessFailedException{
        try {
            Path filePath = this.stockfileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
            	 throw new ProcessFailedException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new ProcessFailedException("File not found " + fileName);
        }
    }
    
    public Resource loadwatchListFileAsResource(String fileName) throws ProcessFailedException{
        try {
            Path filePath = this.watchListfileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
            	 throw new ProcessFailedException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new ProcessFailedException("File not found " + fileName);
        }
    }
    
    
    public Resource loadFundFileAsResource(String fileName) throws ProcessFailedException{
        try {
            Path filePath = this.fundfileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
            	 throw new ProcessFailedException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
        	 throw new ProcessFailedException("File not found " + fileName);
        }
    }
    
    public Resource loadMarketFileAsResource(String fileName) {
        try {
            Path filePath = this.marketfileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
    
    public Resource loadIndustryFileAsResource(String fileName) {
        try {
            Path filePath = this.industryfileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}