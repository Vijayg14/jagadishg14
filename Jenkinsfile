pipeline {
    agent any
    environment {
        registry = "913665488114.dkr.ecr.us-east-1.amazonaws.com/jenkinsecr"
    }
   
    stages {
        stage('Cloning Git') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/newest']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '', url: 'https://github.com/Vijayg14/jagadishg14']]])     
            }
        }
  
    // Building Docker images
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry
        }
      }
    }
   
    // Uploading Docker images into AWS ECR
    stage('Pushing to ECR') {
     steps{  
         script {
                sh 'aws ecr get-login-password --region us-east-1| docker login --username AWS --password-stdin 913665488114.dkr.ecr.us-east-1.amazonaws.com'
                sh 'docker push 913665488114.dkr.ecr.us-east-1.amazonaws.com/jenkinsecr:latest'
         }
        }
      }
   
         