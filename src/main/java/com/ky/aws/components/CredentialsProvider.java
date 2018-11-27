
package com.ky.aws.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;

/**
 * CredentialsProvider
 * 
 * @author kishora
 *
 */
@Component
public class CredentialsProvider
{

    /**
     * credentials provider.
     */
    private final AWSStaticCredentialsProvider value;

    @Autowired
    Credentials awsCredentials;

    public CredentialsProvider(final Credentials awsCredentials)
    {
        this.awsCredentials = awsCredentials;
        this.value = new AWSStaticCredentialsProvider(awsCredentials.getValue());
    }

    public AWSStaticCredentialsProvider getValue()
    {
        return value;
    }

}
