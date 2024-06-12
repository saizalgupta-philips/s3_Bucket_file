package com.s3.examples.configuration;

import com.amazonaws.auth.BasicSessionCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AWSS3Config {

	@Value("${accessKey}")
	private String accessKey;

	@Value("${secretKey}")
	private String secretKey;

	@Value("${region}")
	private String region;

	@Value("${sessionToken}")
	private String sessionToken;

	public AWSCredentials credentials() {
		AWSCredentials credentials = new BasicSessionCredentials(accessKey, secretKey,sessionToken);
		return credentials;
	}

	@Bean
	public AmazonS3 amazonS3() {

		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials())).withRegion(region).build();
		return s3client;
	}
}
