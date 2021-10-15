# Buildots Pipeline Exercise

## General thoughts

I imagine in reality we would've implemented this in cloud. 
So the choice of technologies would've been:
Docker - for container image creation
ECR - docker image storage
ECS - runtime of docker images
S3 - file storage
RDS - to produce database records instead of JSON files
SQS or RabbitMQ - to make pipeline event driven instead of pinging some directory for new files

Note #2 hints that we should have finite number of retries, then log an error and
some monitoring like Sentry which will alert us about service error
