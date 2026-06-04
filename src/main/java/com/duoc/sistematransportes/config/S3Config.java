package com.duoc.sistematransportes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.sessionToken}")
    private String sessionToken;

    @Value("${aws.region}")
    private String region;

    @PostConstruct
    public void test() {
        System.out.println("ACCESS: " + accessKey);
        System.out.println("SECRET: " + secretKey);
        System.out.println("TOKEN EXISTS: " + (sessionToken != null));
        System.out.println("REGION: " + region);
    }

    @Bean
    public S3Client s3Client() {

        AwsSessionCredentials credentials = AwsSessionCredentials.create(
                accessKey,
                secretKey,
                sessionToken);

        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(
                        StaticCredentialsProvider.create(credentials))
                .build();
    }

}