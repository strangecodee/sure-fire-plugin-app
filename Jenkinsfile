@Library('ansible-shared-library') _

node('UBUNTU-NODE') {

    checkout scm

    def config = load 'config.groovy'

    ansibleDeploy(config)
}