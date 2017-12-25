# url-shortener
HTTP service that serves to shorten URLs

# Project
This is spring-boot application with embedded h2 database.

for running application you can use
```
mvn spring-boot:run
```

OR

```
mvn package

java -jar target/url-shortener-1.0.0-SNAPSHOT.jar
```

# Usage

First of all you need to create account
```
curl -X POST --header 'Content-Type: application/json;charset=UTF-8' 'http://localhost:8080/account' -d '{"accountId":"test"}'
```
after that you will be have a password.
Next you need register your url using {accountId} and {password} from previous steps
```
curl -X POST --header 'Content-Type: application/json;charset=UTF-8' 'http://{accountId}:{password}@localhost:8080/register' -d '{"url":"https://google.ru", "redirectType":301}'
```
Now you can use retrieved url for redirecting to original url
```
curl -v {url}
```
In the end you can see your statistics
```
curl 'http://{accountId}:{password}@localhost:8080/statistic/{accountId}'
```