# aws-services
Including helper services for AWS functions.
* signup user
* confirm user sign up using verification code
* authenticate user
* validate 2 factor authentication via email or sms
* generate presigned url
* more todo

# Example usage

Get service object
```
final CognitoUserService userService = context.getBean(CognitoUserService.class);
```

Prepare request paramaters
```
final Map<String, String> options = new HashMap<>();
options.put("USERNAME", "+11234567890");
options.put("PASSWORD", "MySecretPassword");
```

Get authentication response
```
AuthDto dto = userService.authenticate(options, null, AuthDto.Challenge.NONE);
```