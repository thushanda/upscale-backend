package com.devstack.ecom.Upscale.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
    @Value("${aws.access_key}")
    private String accesskey;
    @Value("${aws.secret}")
    private String secret;
    @Value("${aws.region}")
    private String region;

    @Bean
    public AmazonS3 s3(){
        AWSCredentials awsCredentials
                = new BasicAWSCredentials(accesskey,secret);
        return AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    @Bean
    public AmazonS3Client s3Client(){
        AWSCredentials awsCredentials
                = new BasicAWSCredentials(accesskey,secret);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
