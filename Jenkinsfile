pipeline {

    agent {
        label 'built-in'
    }

    tools {

        jdk 'JDK17'
        maven 'Maven3'
    }

    options {

        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    parameters {

        booleanParam(
            name: 'SKIP_TESTS',
            defaultValue: false,
            description: 'Skip Unit Tests'
        )

        booleanParam(
            name: 'SKIP_SONAR',
            defaultValue: false,
            description: 'Skip SonarQube Analysis'
        )

        booleanParam(
            name: 'SKIP_SPOTBUGS',
            defaultValue: false,
            description: 'Skip SpotBugs Analysis'
        )

        booleanParam(
            name: 'SKIP_COVERAGE',
            defaultValue: false,
            description: 'Skip JaCoCo Coverage'
        )
    }

    environment {

        EMAIL_RECIPIENT = 'annu.exe@gmail.com'

        SLACK_CHANNEL = '#jenkins-alerts'

        ANSIBLE_HOST_KEY_CHECKING = 'False'
    }

    stages {

        stage('Verify Tools') {

            steps {

                sh 'echo "JAVA_HOME=$JAVA_HOME"'

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

                sh 'mvn clean compile'
            }
        }

        stage('Parallel Quality Checks') {

            parallel {

                stage('Unit Testing') {

                    when {
                        expression {
                            return !params.SKIP_TESTS
                        }
                    }

                    steps {

                        echo 'Running Maven Surefire Tests'

                        sh 'mvn test'
                    }

                    post {

                        always {

                            junit(
                                allowEmptyResults: true,
                                testResults: 'target/surefire-reports/*.xml'
                            )

                            archiveArtifacts(
                                artifacts: 'target/surefire-reports/*',
                                allowEmptyArchive: true
                            )
                        }
                    }
                }

                stage('JaCoCo Coverage') {

                    when {
                        expression {
                            return !params.SKIP_COVERAGE
                        }
                    }

                    steps {

                        echo 'Running JaCoCo Coverage'

                        sh 'mvn test jacoco:report'

                        jacoco(
                            execPattern: 'target/jacoco.exec',
                            classPattern: 'target/classes',
                            sourcePattern: 'src/main/java',
                            inclusionPattern: '**/*.class',
                            exclusionPattern: '**/test/**'
                        )

                        publishHTML([
                            allowMissing: true,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: 'target/site/jacoco',
                            reportFiles: 'index.html',
                            reportName: 'JaCoCo Coverage Report'
                        ])
                    }
                }

                stage('SpotBugs Analysis') {

                    when {
                        expression {
                            return !params.SKIP_SPOTBUGS
                        }
                    }

                    steps {

                        echo 'Running SpotBugs Analysis'

                        sh 'mvn clean verify'

                        recordIssues tools: [
                            spotBugs(
                                pattern: 'target/spotbugsXml.xml'
                            )
                        ]

                        publishHTML([
                            allowMissing: true,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: 'target/site',
                            reportFiles: 'spotbugs.html',
                            reportName: 'SpotBugs Report'
                        ])
                    }
                }

                stage('SonarQube Analysis') {

                    when {
                        expression {
                            return !params.SKIP_SONAR
                        }
                    }

                    steps {

                        withSonarQubeEnv('SonarScanner') {

                            sh '''
                                mvn sonar:sonar \
                                -Dsonar.projectKey=sure-fire-plugin-app \
                                -Dsonar.projectName=sure-fire-plugin-app
                            '''
                        }
                    }
                }
            }
        }

        stage('Package Application') {

            steps {

                sh 'mvn package -DskipTests'

                archiveArtifacts(
                    artifacts: 'target/*.jar',
                    fingerprint: true
                )
            }
        }

        stage('Generate Reports') {

            steps {

                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/site',
                    reportFiles: 'index.html',
                    reportName: 'Project Reports'
                ])
            }
        }

        stage('Approval Before Deployment') {

            steps {

                timeout(time: 2, unit: 'MINUTES') {

                    input message: 'Approve Build Deployment?'
                }
            }
        }

        stage('Publish Build') {

            steps {

                sh 'mkdir -p artifacts'

                sh 'cp target/*.jar artifacts/'

                archiveArtifacts(
                    artifacts: 'artifacts/*.jar',
                    fingerprint: true
                )

                echo 'Artifacts Published Successfully'
            }
        }

        stage('Deploy to EC2 App Server') {

            steps {

                echo 'Starting Deployment via Ansible'

                sshagent(['ec2-key']) {

                    sh '''
                        ansible-playbook \
                        -i /home/ubuntu/Ansible/inventory.ini \
                        deploy.yml
                    '''
                }
            }
        }
    }

    post {

        success {

            slackSend(
                teamDomain: 'Anurags_Workspace',
                channel: "${SLACK_CHANNEL}",
                color: 'good',
                tokenCredentialId: 'Slack-token',
                botUser: true,
                message: """
SUCCESS: ${env.JOB_NAME}

Build Number: #${env.BUILD_NUMBER}

Branch: test

Build URL:
${env.BUILD_URL}

Application Successfully Deployed to EC2 Server
"""
            )

            mail(
                to: "${EMAIL_RECIPIENT}",
                subject: "[SUCCESS] ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                body: """
Build Successful

Project: ${env.JOB_NAME}

Branch: test

Build Number: #${env.BUILD_NUMBER}

Build URL:
${env.BUILD_URL}

Application Successfully Deployed to EC2 Server

Regards,
Jenkins CI/CD Pipeline
"""
            )
        }

        failure {

            slackSend(
                teamDomain: 'Anurags_Workspace',
                channel: "${SLACK_CHANNEL}",
                color: 'danger',
                tokenCredentialId: 'Slack-token',
                botUser: true,
                message: """
FAILED: ${env.JOB_NAME}

Build Number: #${env.BUILD_NUMBER}

Branch: test

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

Branch: test

Build Number: #${env.BUILD_NUMBER}

Build URL:
${env.BUILD_URL}

Please check Jenkins Console Logs.

Regards,
Jenkins CI/CD Pipeline
"""
            )
        }
    }
}
