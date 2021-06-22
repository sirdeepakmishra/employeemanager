def buildApp() {
	echo "building the application.."
}

def testApp() {
	echo 'testing the application...'
}

def deployApp() {
	echo "deploying the application...with credentials ${SERVER_CREDENTIALS}"
	echo "deploying version ${params.VERSION}"
}

def deployApp2() {
	echo 'deplying 2 the application...'
	echo "deploying 2 version ${params.VERSION}"
	withCredentials([
		usernamePassword(credentials: 'server-user', usernameVariable: USER, passwordVariable: PWD)
	]) {
		echo "some script ${USER} and ${PWD}"
	}
}

return this
