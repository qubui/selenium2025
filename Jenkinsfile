pipeline {
    agent any

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Select browser')
        choice(name: 'ENV', choices: ['UAT', 'INT', 'PROD'], description: 'Select environment')
    }

    options {
        ansiColor('xterm')    // requires AnsiColor plugin
        timestamps()          // show timestamps in logs
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/qubui/selenium2025.git'
            }
        }

        stage('Run Tests') {
            steps {
                bat "mvn clean test -Dbrowser=${params.BROWSER} -Denv=${params.ENV}"
            }
        }

        stage('Publish Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }

       stage('Publish HTML Report') {
    		steps {
        	publishHTML(target: [
            reportDir: 'target/extent-reports',
            reportFiles: 'index.html',
            reportName: 'Extent Report'
        ])
    		}
		}
    }
}