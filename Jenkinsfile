pipeline{
  environment {
    scannerHome = tool 'SonarQubeScanner'
    registry = "jaggu14"+"Vijayg@14"
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
  agent any
    stages {
        stage('clean') {
            steps {
                bat 'mvn clean'
            }
        }
        stage('build and test') {
            steps {
                bat 'mvn package'
            }
        }
        stage('Building image') {
            steps{
                script {
                  dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
             }
          }
          stage('Push Image') {
              steps{
                  script {
                      docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                      }
                  }
              }
            }
         stage('Sonarqube') {
 
            steps {
              script {
                  withSonarQubeEnv('sonarqube') {
                  bat "${scannerHome}/bin/sonar-scanner"
                    }
                  }
               }
          }
      stage("SonarQube Quality Gate"){
        steps {
          script {
               timeout(time: 5, unit: 'MINUTES') { 
               def qualitygate = waitForQualityGate() 
               if (qualitygate.status != 'OK') {
               abortPipeline:true
               error "Pipeline aborted due to quality gate failure: ${qualitygate.status}"
                 }
               else {
               echo "Quality gate passed"
                  }
              }
           }
         }
       }
      
      stage('Deploying into k8s'){
        steps{
          bat 'kubectl apply -f deployment.yml'
          bat 'kubectl apply -f service.yml'
        }
      }
  }
}
