
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
     * Register new user in user pool
     * 
     * @param userName
     * @param password
     * @param options
     * @return void
     */
    void signUp(String userName, String password, Map<String, String> options);

    /**
     * Confirm user signup
     * 
     * @param userName
     * @param verificationCode void
     */
    void confirmSignUp(String userName, String verificationCode);

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
