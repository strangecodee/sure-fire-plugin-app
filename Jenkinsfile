pipeline {

    agent any

    tools {

        jdk 'JDK17'
        maven 'Maven3'
    }

    options {

        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }

    environment {

        EMAIL_RECIPIENT = 'annu.exe@gmail.com'

        SLACK_CHANNEL = '#jenkins-alerts'

        ANSIBLE_HOST_KEY_CHECKING = 'False'
    }

    stages {

        stage('Verify Tools') {

            steps {

                sh 'java -version'
                sh 'mvn -version'
                sh 'git --version'
                sh 'ansible --version'
            }
        }

        stage('Checkout Code') {

            steps {

                git branch: 'test',
                    url: 'https://github.com/strangecodee/sure-fire-plugin-app.git'
            }
        }

        stage('Build Application') {

            steps {

                echo 'Building Spring Boot Application'

                sh 'mvn clean compile'
            }
        }

        stage('Run Unit Tests') {

            steps {

                echo 'Running Unit Tests'

                sh 'mvn test'
            }

            post {

                always {

                    junit(
                        allowEmptyResults: true,
                        testResults: 'target/surefire-reports/*.xml'
                    )
                }
            }
        }

        stage('Package Application') {

            steps {

                echo 'Packaging JAR File'

                sh 'mvn package -DskipTests'

                archiveArtifacts(
                    artifacts: 'target/*.jar',
                    fingerprint: true
                )
            }
        }

stage('Deploy to EC2 App Server') {

    steps {

        echo 'Deploying Application via Ansible'

        sh '''
            ansible-playbook \
            -i /home/ubuntu/Ansible/inventory.ini \
            -u ubuntu \
            --private-key /home/ubuntu/GitOps.pem \
            deploy.yml
        '''
    }
}
    }

    post {

        success {

            echo 'Pipeline Executed Successfully'

            slackSend(
                teamDomain: 'Anurags_Workspace',
                channel: "${SLACK_CHANNEL}",
                color: 'good',
                tokenCredentialId: 'Slack-token',
                botUser: true,
                message: """
SUCCESS: ${env.JOB_NAME}

Build Number: #${env.BUILD_NUMBER}

Application Successfully Built and Deployed

Build URL:
${env.BUILD_URL}
"""
            )

            mail(
                to: "${EMAIL_RECIPIENT}",
                subject: "[SUCCESS] ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: """
Build Successful

Project: ${env.JOB_NAME}

Build Number: #${env.BUILD_NUMBER}

Application Successfully Built and Deployed to EC2 Server

Build URL:
${env.BUILD_URL}

Regards,
Jenkins CI/CD Pipeline
"""
            )
        }

        failure {

            echo 'Pipeline Failed'

            slackSend(
                teamDomain: 'Anurags_Workspace',
                channel: "${SLACK_CHANNEL}",
                color: 'danger',
                tokenCredentialId: 'Slack-token',
                botUser: true,
                message: """
FAILED: ${env.JOB_NAME}

Build Number: #${env.BUILD_NUMBER}

Build URL:
${env.BUILD_URL}
"""
            )

            mail(
                to: "${EMAIL_RECIPIENT}",
                subject: "[FAILED] ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: """
Build Failed

Project: ${env.JOB_NAME}

Build Number: #${env.BUILD_NUMBER}

Please Check Jenkins Console Logs

Build URL:
${env.BUILD_URL}

Regards,
Jenkins CI/CD Pipeline
"""
            )
        }
    }
}