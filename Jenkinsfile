pipeline {
    agent any
    stages{
        stage('Build Maven'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/emailboxforharish/jenkin-kubernetes']]])
                sh 'mvn clean install'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t emailboxforharish/spring-git-jenkin-docker .'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dockerhub', variable: 'dockerhubpwd')]) {
                   sh 'docker login -u emailboxforharish -p ${dockerhubpwd}'

}
                   sh 'docker push emailboxforharish/spring-git-jenkin-docker'
                }
            }
        }
        /* stage('Deploy to k8s'){
            steps{
                script{
                    kubernetesDeploy (configs: 'deploymentservice.yaml',kubeconfigId: 'k8sconfigpwd')
                }
            }
        } */
    }
}