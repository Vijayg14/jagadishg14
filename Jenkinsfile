
node {
  
  def image
  def mvnHome = tool 'Maven3'

  
     stage ('checkout') {
        checkout([$class: 'GitSCM', branches: [[name: '*/maven']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '', url:'https://github.com/Vijayg14/jagadishg14']]])       
        }
    
    
    stage ('Build') {
            sh 'mvn -f MyAwesomeApp/pom.xml clean install'            
        }
        
    stage ('archive') {
            archiveArtifacts '**/*.jar'
        }
        
    stage ('Docker Build') {
         // Build and push image with Jenkins' docker-plugin
        withDockerServer([uri: "tcp://localhost:4243"]) {

            withDockerRegistry([credentialsId: "dockerhub", url: "https://index.docker.io/v1/"]) {
            image = docker.build("ananthkannan/mywebapp", "maven")
            image.push()
            
            }
        }
    }
    stage ('Kubernetes Deploy') {
        kubernetesDeploy(
            configs: 'maven/springBootDeploy.yml',
            kubeconfigId: 'K8S',
            enableConfigSubstitution: true
            )
    }
    
       stage('docker stop container') {
            sh 'docker ps -f name=myContainer -q | xargs --no-run-if-empty docker container stop'
            sh 'docker container ls -a -fname=myContainer -q | xargs -r docker container rm'

       }

    stage ('Docker run') {

        image.run("-p 8085:8085 --rm --name myContainer")

    }
}