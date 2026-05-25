@Library('ansible-shared-library') _

pipeline {

    agent {

        label 'UBUNTU-NODE'
    }

    stages {

        stage('Load Config') {

            steps {

                script {

                    def config = load 'config.groovy'

                    ansibleDeploy(config)
                }
            }
        }
    }
}