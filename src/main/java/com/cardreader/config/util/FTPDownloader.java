package com.cardreader.config.util;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPDownloader {

    FTPClient ftp = null;

    public FTPDownloader(String host, String user, String pwd) throws Exception {
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect(host);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }
        ftp.login(user, pwd);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
       // ftp.enterLocalPassiveMode();
    }

    public void downloadFile(String remoteFilePath, String localFilePath) {
        try (FileOutputStream fos = new FileOutputStream(localFilePath)) {
            this.ftp.retrieveFile(remoteFilePath, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
            } catch (IOException f) {
            	  f.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            FTPDownloader ftpDownloader =  new FTPDownloader("54.169.212.182", "aurora_ftpclient1", "Nov2018$");
            ftpDownloader.downloadFile( "/aurora/inbound/funds/TSC_Aurora_Funds.zip","E://home/ec2-user/aurora/tmp/temp_fund.zip");
            System.out.println("FTP File downloaded successfully");
            ftpDownloader.disconnect();
            ZipUnZipDirectory.unzip("E://home/ec2-user/aurora/tmp/temp_fund.zip", "D://home/ec2-user/aurora/tmp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

