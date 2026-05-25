@Library('ansible-shared-library') _

node('Built-In Node') {

    checkout scm

    def config = load 'config.groovy'

    ansibleDeploy(config)
}