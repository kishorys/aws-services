
package com.ky.aws.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;

/**
 * CognitoIdentityProvider.
 * 
 * @author kishora
 *
 */
@Component
public class CognitoIdentityProvider
{

    private final AWSCognitoIdentityProvider value;

    @Autowired
    CredentialsProvider credentialsProvider;

    @Autowired
    public CognitoIdentityProvider(@Value("${aws.region}") final String region, final CredentialsProvider credentialsProvider)
    {
        this.credentialsProvider = credentialsProvider;
        this.value = AWSCognitoIdentityProviderClientBuilder.standard().withCredentials(credentialsProvider.getValue())
            .withRegion(Regions.fromName(region)).build();
    }

    /**
     * @return AWSCognitoIdentityProvider
     */
    public AWSCognitoIdentityProvider getValue()
    {
        return value;
    }

}
