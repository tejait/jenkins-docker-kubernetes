pipeline {
    agent any
    environment {
        // CHANGE THIS: Your Docker Hub username
        DOCKER_USER = 'TejaIT'

        // THIS MUST MATCH: The "ID" you created in Jenkins Credentials
        REGISTRY_ID = 'DockerCredentialsfinal'

        IMAGE_NAME = 'docker-kubernetes'
        DOCKER_CONTEXT = 'default'
    }
    stages {
        stage('Maven Build') {
            steps {
                // Use 'bat' for Windows Jenkins, 'sh' for Linux
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Docker Build & Push') {
            steps {
                script {
                    // This block handles the login, build, and push automatically
                    docker.withRegistry('', "${REGISTRY_ID}") {
                        def myImage = docker.build("${DOCKER_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER}")
                        myImage.push()
                        myImage.push('latest')
                    }
                }
            }
        }
        stage('Kubernetes Deploy') {
            steps {
                // This command tells Minikube to pull the brand new image version
                bat "kubectl set image deployment/docker-kubernetes-deployment spring-app=${DOCKER_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER}"
            }
        }
    }
}