@Library('ansible-shared-library') _

node('master') {

    checkout scm

    def config = load 'config.groovy'

    ansibleDeploy(config)
}
