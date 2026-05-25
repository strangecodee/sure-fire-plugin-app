@Library('ansible-shared-library') _

pipeline {

    agent any

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
