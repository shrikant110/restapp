package com.cardreader.config.util;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUnZipDirectory {
	public static void main(String[] args) throws IOException {

        String sourceDirectoryPath = "/Users/umesh/personal/tutorials/source";
        String zipFilePath = "/Users/umesh/personal/tutorials/source.zip";

        zipDirectory(sourceDirectoryPath, zipFilePath);
    }

    public static void zipDirectory(String sourceDirectoryPath, String zipPath) throws IOException {
        Path zipFilePath = Files.createFile(Paths.get(zipPath));

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFilePath))) {
            Path sourceDirPath = Paths.get(sourceDirectoryPath);

            Files.walk(sourceDirPath).filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(sourceDirPath.relativize(path).toString());
                        try {
                            zipOutputStream.putNextEntry(zipEntry);
                            zipOutputStream.write(Files.readAllBytes(path));
                            zipOutputStream.closeEntry();
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    });
        }
    }
    public static void unzip(final String zipFilePath, final String unzipLocation) throws IOException {

        if (!(Files.exists(Paths.get(unzipLocation)))) {
            Files.createDirectories(Paths.get(unzipLocation));
        }
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipInputStream.getNextEntry();
            while (entry != null) {
                Path filePath = Paths.get(unzipLocation, entry.getName());
                if (!entry.isDirectory()) {
                    unzipFiles(zipInputStream, filePath);
                } else {
                    Files.createDirectories(filePath);
                }

                zipInputStream.closeEntry();
                entry = zipInputStream.getNextEntry();
            }
        }
    }

    public static void unzipFiles(final ZipInputStream zipInputStream, final Path unzipFilePath) throws IOException {

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(unzipFilePath.toAbsolutePath().toString()))) {
            byte[] bytesIn = new byte[1024];
            int read = 0;
            while ((read = zipInputStream.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }

    }
}

