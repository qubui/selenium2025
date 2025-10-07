pipeline {
    agent any
    triggers {
        cron('0 7 * * *')  // runs at 7:00 AM every day
    }
    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Select browser')
        choice(name: 'ENV', choices: ['PROD', 'INT', 'UAT'], description: 'Select environment')
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

       stage('Publish Extent Report') {
    		steps {
        	publishHTML(target: [
            reportDir: 'target/extent-report',
            reportFiles: 'HTMLReport.html',
            reportName: 'HTML Extent Report',
            keepAll: true,
            alwaysLinkToLastBuild: true,
            allowMissing: false
        	])
    		}
		}
    }
    
    post {
        always {
            echo "Cleaning workspace..."
            cleanWs()
        }
        success {
            echo "✅ Build succeeded!"
        }
        failure {
            echo "❌ Build failed. Check reports for details."
        }
    }
}