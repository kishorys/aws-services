
package com.ky.aws.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * AuthDto
 * 
 * @author kishora
 *
 */
public class AuthDto
{

    public static enum Challenge
    {
        NONE, NEW_PASSWORD_REQUIRED, SMS_MFA
    }

    private boolean authorized;
    private Challenge challenge;
    private String session;
    private String accessToken;

    public AuthDto()
    {
        this.authorized = false;
        this.challenge = Challenge.NONE;
    }

    public void setAuthorized(final boolean authorized)
    {
        this.authorized = authorized;
    }

    public void setChallenge(final Challenge challenge)
    {
        this.challenge = challenge;
    }

    public String getSession()
    {
        return session;
    }

    public void setSession(final String session)
    {
        this.session = session;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(final String accessToken)
    {
        this.accessToken = accessToken;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

}
