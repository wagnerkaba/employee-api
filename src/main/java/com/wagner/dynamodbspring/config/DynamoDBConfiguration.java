package com.wagner.dynamodbspring.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfiguration {

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildAmazonDynamoDB());
    }

    private AmazonDynamoDB buildAmazonDynamoDB() {



        // Salvei o login e o password do AWS como "environment variables"
        // Configurei as "environment variables" tanto no Intellij (para funcionar localmente) quanto no Heroku (para funcionar na nuvem)
        // System.getenv é um método para ler as "environment variables"

        String AWS_ACCESS_KEY = System.getenv("AWS_ACCESS_KEY");
        String AWS_SECRET_KEY = System.getenv("AWS_SECRET_KEY");



        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                "dynamodb.us-east-1.amazonaws.com",
                                "us-east-1"
                        )
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(AWS_ACCESS_KEY,
                                        AWS_SECRET_KEY
                                )
                        )
                )
                .build();

    }


}
