package com.charapadev.zeromusic.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.charapadev.zeromusic.config.S3Details;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StorageService {
    private final AmazonS3 amazonS3;
    private final S3Details s3Details;

    private ByteArrayInputStream base64ToStream(String base64) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        return new ByteArrayInputStream(decodedBytes);
    }

    private String getDirectory(FileEnum fileType) {
        return fileType == FileEnum.AUTHOR ? "authors" : "musics";
    }

    private String generateFilename(FileEnum fileType) {
        String filename = fileType == FileEnum.MUSIC ? "music" : "cover";
        String extension = fileType == FileEnum.MUSIC ? "mp3" : "jpeg";

        return String.join(".", filename, extension);
    }

    public String uploadFile(String base64, Long relatedID, FileEnum fileType) {
        ByteArrayInputStream fileStream = base64ToStream(base64);
        long fileSize = fileStream.available();
        String relatedDir = String.valueOf(relatedID);

        String filename = String.join(
            "/", getDirectory(fileType), relatedDir, generateFilename(fileType)
        );

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(fileSize);
        metadata.setContentType(fileType == FileEnum.MUSIC ? "audio/mpeg" : "image/jpeg");

        amazonS3.putObject(s3Details.bucketName(), filename, fileStream, metadata);
        return resolveFileURL(filename);
    }

    private String resolveFileURL(String filename) {
        return String.format("%s/%s/%s", s3Details.s3Url(), s3Details.bucketName(), filename);
    }
}
