pipeline {
    environment {
        registry = "emailboxforharish/spring-git-jenkin-docker"
        registryCredential = 'dockerhub_id'
        dockerImage = ''
    }
    agent any
    stages {
        stage('Cloning our Git') {
            steps {
                git 'https://github.com/emailboxforharish/jenkin-kubernetes.git'
            }
        }
        stage('install') {
            steps {
                bat "mvn install"
            }
        }
        stage('Building our image') {
            steps{
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }
        stage('Deploy our image') {
            steps{
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Orchestrate')
        {
            steps{
                script{
                    bat 'kubectl apply -f deployment.yml'
                }
            }
        }
        /* stage('Cleaning up') {
            steps{
                sh "docker rmi $registry:$BUILD_NUMBER"
            }
        } */
    }
}