
package com.ky.aws.services;

import java.util.Map;

import com.ky.aws.dto.AuthDto;

/**
 * CognitoUserService
 * 
 * @author kishora
 *
 */
public interface CognitoUserService
{
    /**
     * Authenticate Cognito User
     * 
     * @param options
     * @param session
     * @param userChallenge
     * @return AuthDto
     */
    AuthDto authenticate(Map<String, String> options, String session, AuthDto.Challenge userChallenge);
}
