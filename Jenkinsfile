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
        stage('Push Docker Image') {
                    steps {
                        withCredentials([string(
                            credentialsId: 'jenkinspwd',
                            variable: 'DOCKER_PASS'
                        )]) {
                            bat '''
                                :: Force use of default context to avoid "desktop-linux" errors
                                docker context use default

                                echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                                docker push %IMAGE_NAME%:%IMAGE_TAG%
                                docker logout
                            '''
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