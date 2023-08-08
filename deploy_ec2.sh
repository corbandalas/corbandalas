mvn clean package -Pproduction
scp -i /Users/artyomtereshchenko/corbandalascom.pem /Users/artyomtereshchenko/IdeaProjects/corbandalas/launcher/target/launcher-1.0-SNAPSHOT.jar ubuntu@ec2-34-238-122-76.compute-1.amazonaws.com:~
