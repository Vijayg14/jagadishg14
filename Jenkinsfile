pipeline {
    agent any 
    environment {
        //TODO # 1 --> once you sign up for Docker hub, use that user_id here
        registry = "gcr.io/fast-haiku-318314/my-app2:green"
        //TODO #2 - update your credentials ID after creating credentials for connecting to Docker Hub
       // registryCredential = 'fa32f95a-2d3e-4c7b-8f34-11bcc0191d70'
        //dockerImage = ''
    }
    
    stages {
        stage('Cloning Git') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'rmgt']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '', url: 'https://github.com/Vijayg14/jagadishg14.git']]])       
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
    
     // Uploading Docker images into Docker Hub
    stage('Upload Image') {
     steps{    
         script {
            sh 'gcloud auth configure-docker'
            sh 'docker push  gcr.io/fast-haiku-318314/my-app2:green'
            }
        }
      }
    }
    
     // Stopping Docker containers for cleaner Docker run
     stage('docker stop container') {
         steps {
            sh 'docker ps -f name=mypythonContainer -q | xargs --no-run-if-empty docker container stop'
            sh 'docker container ls -a -fname=mypythonContainer -q | xargs -r docker container rm'
         }
       }
    
    
    // Running Docker container, make sure port 8096 is opened in 
    stage('Docker Run') {
     steps{
         script {
            dockerImage.run("-p 8096:5000 --rm --name mypythonContainer")
         }
      }
    }
  }
}
