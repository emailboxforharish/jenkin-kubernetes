pipeline {
  environment {
    imagename = "jenkin-docker-image"
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
    stage('package') {
        steps {
            bat "mvn package -f jenkin-kubernetes"
        }
    }
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build imagename
        }
      }
    }
    stage('Deploy Image') {
      steps{
        script {
          docker.withRegistry( '', registryCredential ) {
            dockerImage.push("$BUILD_NUMBER")
             dockerImage.push('latest')

          }
        }
      }
    }
    stage('Remove Unused docker image') {
      steps{
        sh "docker rmi $imagename:$BUILD_NUMBER"
         sh "docker rmi $imagename:latest"

      }
    }
  }
}