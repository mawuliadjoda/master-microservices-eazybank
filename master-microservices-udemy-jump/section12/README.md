
docker run -d -p 7080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:24.0.3 start-dev

http://localhost:7080/ 


http://localhost:7080/realms/master/.well-known/openid-configuration


https://oauth.com/playground


# 1. Request authorization code 
https://authorization-server.com/authorize?
response_type=code
&client_id=-K2qG_SiXLRJVxS2b4GOmZdb
&redirect_uri=https://oauth.com/playground/authorization-code.html
&scope=photo+offline_access
&state=DLitVZvWJEOLHrds

# 2. Exchange the Authorization Code

Now you're ready to exchange the authorization code for an access token.

The client builds a POST request to the token endpoint with the following parameters:

POST https://authorization-server.com/token

grant_type=authorization_code
&client_id=-K2qG_SiXLRJVxS2b4GOmZdb
&client_secret=jSA7zbIxREQ8Mcjk9xtMD76hc6LSZ2JaYW_qrmAfCUaQ2Sms
&redirect_uri=https://oauth.com/playground/authorization-code.html
&code=E64FsRwairbpFhlNCD0cyu8oUgAqd3iv4L2cw52achNGL_kd

# 3. Token Endpoint Response

Here's the response from the token endpoint! The response includes the access token and refresh token.

{
"token_type": "Bearer",
"expires_in": 86400,
"access_token": "HaHZtLHvbQgomQF7zrj5xj7ALfkDatNWm4wxepJvVDxw-vrpjMggebscCF_DAuWKcCXR3Lc1",
"scope": "photo offline_access",
"refresh_token": "JUC-HMFUU0XYrrdCADbvT0zy"
}

# https://www.keycloak.org/docs-api/latest/rest-api/index.html

http://localhost:7080/realms/master/.well-known/openid-configuration
