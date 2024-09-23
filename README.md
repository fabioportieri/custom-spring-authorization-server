## Amministrazione Trasparente Authorization Server

Customized from sample at https://github.com/andifalk/custom-spring-authorization-server


Read [README.original.md](README.original.md) for details.


developed with spring Authorization server, the microservice is intended to authorize third party
apps agains amm-trasparente-backoffice, the authorization is performed through OAuth2 - client credentials
flow

## Usage:

obtain a token, providing a clientid and a secret


```
url --location 'http://localhost:8999/oauth2/token?scope=openid%20profile%20email%20offline_access' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: ••••••' \
--data-urlencode 'grant_type=client_credentials'
```

Authorization basic, username: thirdparty-amm-trasparente passoword: ...

example response:
```json
{
    "access_token": "eyJraWQiOiIyOGVmYWFiMS1hYjdiLTRmNTgtYjg2MC00ZmEzMTE1NTM4ZGEiLCJ0eXAiOiJqd3QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0aGlyZHBhcnR5LWFtbS10cmFzcGFyZW50ZSIsImF1ZCI6WyJ0aGlyZHBhcnR5LWFtbS10cmFzcGFyZW50ZSIsImFwaTovL2RlZmF1bHQiXSwibmJmIjoxNzI3MDk1NTYwLCJyb2xlcyI6WyJST0xFX1VTRVIiLCJST0xFX0VESVRPUiIsIlJPTEVfVVNFUl9NQU5BR0VSIiwiUk9MRV9SRVZJU0VSIiwiUk9MRV9URU1QTEFURV9NQU5BR0VSIiwiUk9MRV9DQVRFR09SSUVTX01BTkFHRVIiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4OTk5IiwiZXhwIjoxNzI3MDk2NDYwLCJpYXQiOjE3MjcwOTU1NjAsImp0aSI6Ijc1Y2U0ODgyLTZmMTctNGJmOC05MzkyLTFkMGE0OTYyMzI4OCJ9.TM8ct9sK4IDVZXFLJUofz60_jxSF6ECCL-GBmcwQBkdLqfG4gjZw3j4Qq6xEsr-PeSRCQa7iAyqnaG1hCTszPqb_Gu08DRkVA-DkOYRmtzf1MNfD6xIE1RGDLarzX-H9UY3EHwdH55BhVtNpOMMlpVGBNvoWiDzRwys5rzLAm2E7CXLXkuIuzkSSPM022OC_44rHyV8w8bX9xw_cw7xd38TLEVY6Evj03gdhx0Y9bITO5IRKypzEwVH12OI8xVtXbpZsvvuo012hJLndhmhnoWkZtB7saFEQVbQ5v3YMPs3FALAeT3mbj_B36SwyLL4kvbvnt9joohEBfvSxIe6A0A",
    "token_type": "Bearer",
    "expires_in": 899
}
```

now we can use the provided token to authenticate against amm-trasparente-bo API:

```
curl --location 'http://localhost:8080/amm-trasparente/api/v1/posts' \
--header 'Content-Type: application/json' \
--header 'Authorization: ••••••' \
--data '{
"name": "teddstt"
}'
```