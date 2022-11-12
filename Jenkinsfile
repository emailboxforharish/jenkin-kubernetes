pipeline {
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
        stage('test') {
            steps {
                bat "mvn test -f jenkin-kubernetes"
            }
        }
        stage('package') {
            steps {
                bat "mvn package -f jenkin-kubernetes"
            }
        }
    }
}