--------------------------
Features:

- SpringBoot starter libraries
- logging
- exception advice

--------------------------
Execute:

./mvnw spring-boot:run
mvn spring-boot:run

--------------------------
Reference:

For webapp basic revision:
https://spring.io/guides/gs/spring-boot/

--------------------------
Aliases

java2023 
ssh

--------------------------
Remake github SSH key:

1) Generate the key
   ssh-keygen -t rsa -b 4096 -C "[email]"

2) Restart the ssh-agent if necessary:
   eval "$(ssh-agent -s)"

3) Add the newly generated private key to config file:
   - update config
   - ssh-add ~/.ssh/[private key]

4) Add the public SSH key into github

DAILY ON MACBOOK:

ssh-agent -s
ssh-add ~/.ssh/github-TRT

--------------------------