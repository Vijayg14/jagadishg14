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
    
    
     // Uploading Docker images into Docker Hub
    stage('Push to GCR ') {
     steps{    
         script {
             sh 'docker login -u _json_key -p "$(cat keyfile.json)" https://gcr.io'
             sh 'docker push  gcr.io/fast-haiku-318314/my-app2:green'
            }
     }
    }
        }
}
}
}
}