curl --location --request POST 'http://localhost:8080/oauth/token' \
--form 'username="lilei"' \
--form 'password="123456"' \
--form 'grant_type="password"' \
--form 'scope="select"' \
--form 'client_id="app1"' \
--form 'client_secret="123456"'

curl --location --request GET 'http://localhost:8080/users/current' \
--header 'Authorization: Bearer ec8d4204-9114-4497-ba24-800d7ef69737'