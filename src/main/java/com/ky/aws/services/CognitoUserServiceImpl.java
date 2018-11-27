
package com.ky.aws.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.ChallengeNameType;
import com.amazonaws.services.cognitoidp.model.InitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.InitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.RespondToAuthChallengeRequest;
import com.amazonaws.services.cognitoidp.model.RespondToAuthChallengeResult;
import com.ky.aws.components.CognitoIdentityProvider;
import com.ky.aws.dto.AuthDto;

/**
 * CognitoUserServiceImpl
 * 
 * @author kishora
 *
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CognitoUserServiceImpl implements CognitoUserService
{
    Logger LOGGER = LoggerFactory.getLogger(CognitoUserServiceImpl.class);

    @Autowired
    CognitoIdentityProvider identityProvider;

    @Value("${aws.clientapp_id}")
    String clientAppId;

    @Override
    public AuthDto authenticate(final Map<String, String> options, final String session,
        final AuthDto.Challenge userChallenge)
    {
        final AuthDto authDto = new AuthDto();
        switch (userChallenge)
        {
            case NONE:
                final InitiateAuthRequest authRequest = new InitiateAuthRequest();
                authRequest.setAuthFlow(AuthFlowType.USER_PASSWORD_AUTH);
                authRequest.setClientId(clientAppId);
                authRequest.setAuthParameters(options);
                final InitiateAuthResult authResult = identityProvider.getValue().initiateAuth(authRequest);
                authDto.setSession(authResult.getSession());
                authDto.setChallenge(AuthDto.Challenge.valueOf(authResult.getChallengeName()));
                break;
            case NEW_PASSWORD_REQUIRED:
                respondToNewPasswordRequiredChallenge(options, session);
                break;
            case SMS_MFA:
                respondToSmsSFA(options, session);
                break;
            default:
                LOGGER.error("Invalid UserChallenge " + userChallenge.name() + " used");
                break;
        }

        return authDto;
    }

    /**
     * respondToNewPasswordRequiredChallenge
     * 
     * @param options
     * @param session
     * @return AuthDto
     */
    private AuthDto respondToNewPasswordRequiredChallenge(final Map<String, String> options, final String session)
    {
        final AuthDto authDto = new AuthDto();
        final Map<String, String> challengeResponses = new HashMap<String, String>();
        challengeResponses.putAll(options);
        final RespondToAuthChallengeRequest respondToAuthChallengeRequest =
            new RespondToAuthChallengeRequest().withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                .withClientId(clientAppId).withChallengeResponses(challengeResponses).withSession(session);
        final RespondToAuthChallengeResult result =
            identityProvider.getValue().respondToAuthChallenge(respondToAuthChallengeRequest);
        authDto.setAuthorized(true);
        authDto.setChallenge(AuthDto.Challenge.valueOf(result.getChallengeName()));
        return authDto;
    }

    /**
     * respondToSmsSFA
     * 
     * @param options
     * @param session
     * @return AuthDto
     */
    private AuthDto respondToSmsSFA(final Map<String, String> options, final String session)
    {
        final AuthDto authDto = new AuthDto();
        final Map<String, String> challengeResponses = new HashMap<String, String>();
        challengeResponses.putAll(options);
        final RespondToAuthChallengeRequest respondToAuthChallengeRequest =
            new RespondToAuthChallengeRequest().withChallengeName(ChallengeNameType.SMS_MFA).withClientId(clientAppId)
                .withChallengeResponses(challengeResponses).withSession(session);
        final RespondToAuthChallengeResult result =
            identityProvider.getValue().respondToAuthChallenge(respondToAuthChallengeRequest);
        authDto.setAuthorized(true);
        if (null != result.getChallengeName())
        {
            authDto.setChallenge(AuthDto.Challenge.valueOf(result.getChallengeName()));
        }
        authDto.setAccessToken(result.getAuthenticationResult().getAccessToken());
        return authDto;
    }

}
