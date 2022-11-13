pipeline {
    environment {
        registryCredential = 'dockerhub'
    }
    agent any
    stages {
        stage('git repo & clean') {
            steps {
                bat "rmdir  /s /q jenkin-kubernetes"
                bat "git clone https://github.com/emailboxforharish/jenkin-kubernetes.git"
                bat "mvn clean -f jenkin-kubernetes"
            }
        }
        stage('install') {
            steps {
                bat "mvn install -f jenkin-kubernetes"
            }
        }
        stage('Docker Build and Tag') {
            steps {
                sh 'docker build -t spring-git-jenkin-docker-image:latest .'
                sh 'docker tag spring-git-jenkin-docker-image emailboxforharish/spring-git-jenkin-docker-image:latest'
                sh 'docker tag spring-git-jenkin-docker-image emailboxforharish/spring-git-jenkin-docker-image:$BUILD_NUMBER'
            }
        }
        stage('Publish image to Docker Hub') {
            steps {
                withDockerRegistry([ credentialsId: "dockerHub", url: "" ]) {
                  sh  'docker push emailboxforharish/spring-git-jenkin-docker-image:latest'
                  sh  'docker push emailboxforharish/spring-git-jenkin-docker-image:$BUILD_NUMBER'
                }
            }
        }
        stage('Run Docker container on Jenkins Agent') {
            steps {
                sh "docker run -d -p 4030:80 emailboxforharish/spring-git-jenkin-docker-image"
            }
        }
        stage('Run Docker container on remote hosts') {
            steps {
                sh "docker -H ssh://jenkins@172.31.28.25 run -d -p 4001:80 emailboxforharish/spring-git-jenkin-docker-image"
            }
        }
    }
}