pipeline {
	agent any
	tools {
		maven 'Maven'
	}
	parameters {
		//string(name: 'VERSION', defaultValue: '', description: 'version to deploying on prod')
		choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: 'select choice')
		booleanParam(name: 'executeTests', defaultValue: true, description: 'boolean Params for execute tests')
	}
	environment {
		NEW_VERSION = '1.1'
		SERVER_CREDENTIALS = credentials('server-user')
	}
	stages {

		stage("build") {
			steps {
				echo "BUILD the application pipeline...with version ${NEW_VERSION}"
				echo 'mvn install'
				//sh 'mvn install'
				script {
					def test = 2 + 2 > 3 ? 'cool' : 'not cool'
					echo test
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
				echo 'TEST the application pipeline.....'
				echo "deploying version... ${params.VERSION}"
			}
		}

		stage("deploy1") {
			steps { 
				echo "DEPLOY 1 the application pipeline...with credentials ${SERVER_CREDENTIALS}"
				//sh "${SERVER_CREDENTIALS}"
			}
		}

		stage("deploy2") {
			steps { 
				echo "DEPLOY 2...."
				withCredentials([
					usernamePassword(credentials: 'server-user', usernameVariable: USER, passwordVariable: PWD)
				]) {    echo "USERNAME and PASSWORD : ${USER} and ${PWD}"
					//sh "some script ${USER} and ${PWD}"
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
