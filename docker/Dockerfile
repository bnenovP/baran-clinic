FROM jenkins/jenkins:lts

# Switch to root
USER root

# Install Docker CLI
RUN apt-get update && \
    apt-get install -y docker.io && \
    rm -rf /var/lib/apt/lists/*

# Switch back to Jenkins user
USER jenkins