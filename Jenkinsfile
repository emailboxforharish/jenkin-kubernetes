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
       stage('Maven Install') {
            agent {
             	docker {
               	image 'maven:3.5.0'
               }
             }
             steps {
             	sh 'mvn clean install'
             }
       }
       stage('Docker Build') {
            agent any
            steps {
             	sh 'docker build -t emailboxforharish/spring-docker-image:latest .'
            }
       }
       stage('Docker Push') {
            agent any
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                    sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                    sh 'docker push emailboxforharish/spring-docker-image:latest'
               }
             }
       }
    }
}