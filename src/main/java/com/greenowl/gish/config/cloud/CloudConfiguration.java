package com.greenowl.gish.config.cloud;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.sns.AmazonSNSClient;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Configuration class responsible for defining any possible beans responsible for interfacing with
 * cloud provider. Currently the primary Cloud Service used is AWS.
 */
@Configuration
public class CloudConfiguration implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
        this.propertyResolver = new RelaxedPropertyResolver(env, "aws.credentials.");
    }

    @Bean
    public AmazonSNSClient snsClient(AWSCredentials credentials) {
        return new AmazonSNSClient(credentials);
    }

    @Bean
    public TransferManager transferManager(AWSCredentials credentials) {
        return new TransferManager(credentials);
    }

    @Bean
    public AWSCredentials awsc() {
        return new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return propertyResolver.getProperty("id");
            }

            @Override
            public String getAWSSecretKey() {
                return propertyResolver.getProperty("secret");
            }
        };
    }
}
