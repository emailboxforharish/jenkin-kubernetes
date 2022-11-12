pipeline {
    environment {
        imagename = "spring-git-jenkin-docker-image"
        registryCredential = 'dockerhub'
        dockerImage = ''
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
        stage('Building image') {
            steps{
                script {
                  dockerImage = imagename
                }
            }
        }
        stage('Deploy Image') {
            steps{
                script {
                  docker.withRegistry( '', registryCredential ) {
                    dockerImage.push()
                    // dockerImage.push('latest')
                  }
                }
            }
        }
    }
}