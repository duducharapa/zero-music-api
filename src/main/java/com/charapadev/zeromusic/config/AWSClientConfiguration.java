package com.charapadev.zeromusic.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSClientConfiguration {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String regionName;

    @Value("${cloud.aws.s3.url}")
    private String s3Url;

    @Value("${cloud.aws.s3.bucket-name}")
    private String bucketName;

    private BasicAWSCredentials getCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    private AwsClientBuilder.EndpointConfiguration getEndpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(s3Url, regionName);
    }

    private AWSStaticCredentialsProvider getCredentialsProvider() {
        return new AWSStaticCredentialsProvider(getCredentials());
    }

    @Bean(name = "amazonS3")
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(getEndpointConfiguration())
            .withCredentials(getCredentialsProvider())
            .build();
    }

    @Bean
    public S3Details s3Details() {
        return new S3Details(s3Url, bucketName);
    }

}
