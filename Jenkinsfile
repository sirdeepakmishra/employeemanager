def gv
pipeline {
	agent any
	tools {
		maven 'Maven'
	}
	parameters {
		choice(name: 'VERSION', choices: ['1.1', '1.2', '1.3'], description: 'select choice')
		booleanParam(name: 'executeTests', defaultValue: true, description: 'Do you want to execute tests')
	}
	environment {
		SERVER_CREDENTIALS = credentials('server-user')
	}
	stages {

		stage("init") {
			steps {
				script {
					gv = load "script.groovy"
				}
			}
		}

		stage("build") {
			steps {
				script {
					gv.buildApp()
				}
			}
		}

		stage("test") {
			when {
				expression {
					BRANCH_NAME == 'develop/spring-security' || BRANCH_NAME == 'main'
					params.executeTests == true
				}
			}
			steps {
				script {
					gv.testApp()
				}
			}
		}

		stage("deploy") {
			steps {
				script {
					gv.deployApp()
				}
			}
		}

		stage("deploy2") {
			steps {
				script {
					gv.deployApp2()
				}
			}
		}

	}

	post {
		always {
			echo 'post always!!'
		}
		success {
			echo 'post success!!'
		}
		failure {
			echo 'post failure !!'
		}
	}
}
