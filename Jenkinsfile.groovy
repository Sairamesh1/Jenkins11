node {
    stage('Checkout') {
        checkout([$class: 'GitSCM', branches: [[name: 'main']],
                  userRemoteConfigs: [[url: 'https://github.com/Pradeep-22/Jenkins.git']]])
    }
    stage('Build') {
        sh 'mvn clean package'
    }
    stage('Deploy to Tomcat') {
        sh 'scp target/MyWebApp.war ubuntu@43.204.130.110:/opt/apache-tomcat-9.0.96/webapps/'
    }
    post {
        always {
            cleanWs()
        }
        success {
            echo "Deployment successful!"
        }
        failure {
            echo "Deployment failed."
        }
    }
}
