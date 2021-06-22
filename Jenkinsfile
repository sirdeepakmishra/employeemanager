pipeline {
	agent any
	tools {
		maven 'Maven'
	}
	parameters {
		string(name: 'VERSION', defaultValue: '', description: 'version to deploying on prod')
		choice(name: 'VERSION', choices['1.1', '1.2', '1.3'], description: 'select choice')
		booleanParam(name: 'executeTests', defaultValue: false, description: 'boolean Params for execute tests')
	}
	environment {
		NEW_VERSION = '1.1'
		SERVER_CREDENTIALS = credentials('server-user')
	}
	stages {

		stage("build") {
			steps {
				echo "building the application pipeline...with version ${NEW_VERSION}"
				echo 'mvn install'
				sh 'mvn install'
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
				echo 'testing the application pipeline.....'
				echo "deploying version... ${params.VERSION}"
			}
		}

		stage("deploy") {
			steps {
				echo "deploying the application pipeline...with credentials ${SERVER_CREDENTIALS}"
				sh "${SERVER_CREDENTIALS}"
			}
		}

		stage("deploy2") {
			steps {
				echo "deploying2 the application..."
				withCredentials([
					usernamePassword(credentials: 'server-user', usernameVariable: USER, passwordVariable: PWD)
				]) {
					sh "some script ${USER} and ${pwd}"
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
