FROM openjdk:17
RUN mkdir /SavvyFix
WORKDIR /SavvyFix
COPY out/artifacts/SavvyFix_jar/*.jar /SavvyFix/SavvyFix.jar
CMD ["java","-jar","/SavvyFix/SavvyFix.jar"]