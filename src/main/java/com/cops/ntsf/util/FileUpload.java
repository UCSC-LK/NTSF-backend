package com.cops.ntsf.util;

import javax.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUpload {

    public static String uploadFile(Part filePart, String uploadDirectory, String userId) throws Exception {
        InputStream fileContent = filePart.getInputStream();
        String fileName = filePart.getSubmittedFileName();
        String renamedFileName = renameFileName(userId, fileName);
        String filePath = uploadDirectory + File.separator + renamedFileName;

        OutputStream outputStream = Files.newOutputStream(Paths.get(filePath));
        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = fileContent.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
        fileContent.close();

        System.out.println("filePath: " + filePath);

        return filePath;
    }

    private static String renameFileName(String userId, String fileName) {
        System.out.println("File Name before renaming: " + fileName);
        String[] parts = fileName.split("\\.");
        String extension = parts[1];
        String newFileName = userId + "." + extension;
        System.out.println("File Name after renaming: " + newFileName);
        return newFileName;
    }
}
