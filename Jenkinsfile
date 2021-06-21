pipeline {
    agent any
    environment {
        registry = "acct_id.dkr.ecr.us-east-.amazonaws.com/913665488114.dkr.ecr.us-east-1.amazonaws.com/jenkinsecr"
    }
   
    stages {
        stage('Cloning Git') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/newtest']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '', url: 'https://github.com/Vijayg14/jagadishg14']]])     
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
                sh 'docker push 913665488114.dkr.ecr.us-east-1.amazonaws.com/your_ecr_repo:latest'
         }
        }
      }
   
         // Stopping Docker containers for cleaner Docker run
     stage('stop previous containers') {
         steps {
            sh 'docker ps -f name=mypythonContainer -q | xargs --no-run-if-empty docker container stop'
            sh 'docker container ls -a -fname=mypythonContainer -q | xargs -r docker container rm'
         }
       }
      
    stage('Docker Run') {
     steps{
         script {
                sh 'docker run -d -p 8096:5000 --rm --name mypythonContainer acct_id.dkr.ecr.us-east-2.amazonaws.com/your_ecr_repo:latest'
            }
      }
    }
    }
}