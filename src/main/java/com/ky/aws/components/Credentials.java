
package com.ky.aws.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

/**
 * Credentials
 * 
 * @author kishora
 *
 */
@Component
public class Credentials
{

    private final AWSCredentials value;

    @Autowired
    public Credentials(@Value("${aws.access_key}") final String accessKey, @Value("${aws.secret}") final String secret)
    {
        this.value = new BasicAWSCredentials(accessKey, secret);
    }

    public AWSCredentials getValue()
    {
        return value;
    }
}
