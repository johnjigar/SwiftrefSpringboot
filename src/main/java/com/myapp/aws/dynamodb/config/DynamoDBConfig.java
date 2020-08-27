package com.myapp.aws.dynamodb.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.myapp.aws.dynamodb.repositories")
public class DynamoDBConfig {
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB;
        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
            amazonDynamoDB = AmazonDynamoDBClientBuilder
                    .standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, "US_EAST_1"))
                    //.withRegion(Regions.US_EAST_1)
                    .build();
        }else{
            amazonDynamoDB = AmazonDynamoDBClientBuilder
                    .standard()
                    //.withCredentials(new EnvironmentVariableCredentialsProvider()) //You have to pass env variables AWS_ACCESS_KEY_ID=;AWS_SECRET_ACCESS_KEY=
                    .withRegion(Regions.US_EAST_1)
                    .build();
        }

        return amazonDynamoDB;
    }
}