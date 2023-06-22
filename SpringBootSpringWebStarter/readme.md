--------------------------
Features:

- SpringBoot starter libraries
- logging
- exception advice

--------------------------
SOFTWARE VERSIONS:

JAVA on machine:  openjdk-20.0.1
MAVEN on machine: apache-maven-3.6.3

--------------------------
Execute:

./mvnw spring-boot:run
mvn spring-boot:run

Bizarrely, control c is not stopping spring boot!
However, running in maven does work (once it is compiled and installed):

java -jar target/SpringBootSpringWebStarter-0.0.1-SNAPSHOT.jar

--------------------------
Reference:

For webapp basic revision:
https://spring.io/guides/gs/spring-boot/

--------------------------
**** ALIASES on HP (.bashrc) ****
refapps / webstarter / ssh / java2023

MOVE TO c/j8_pd_2023_workspace/SpringBoot_Reference_Apps = refapps
alias refapps="cd C:;cd j8_pd_2023_workspace/SpringBoot_Reference_Apps"

MOVE TO SpringBootSpringWebStarter = webstarter
alias webstarter ="cd C:;cd j8_pd_2023_workspace/SpringBoot_Reference_Apps/SpringBootSpringWebStarter"

MOVE TO SSH = ssh
alias ssh="cd ~/.ssh"

MOVE TO 2023 Workspace
alias java2023="cd C:;cd j8_pd_2023_workspace"

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
testing commit - completed testing with removal of git config
- config file (no matter for single repo access)
- known hosts (can still commit but git cannot verify it is the correct destination)
--------------------------
