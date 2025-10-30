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
        emailext(
            subject: "✅ SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: """
                <h3>✅ Build Successful!</h3>
                <p>Project: ${env.JOB_NAME}</p>
                <p>Build Number: ${env.BUILD_NUMBER}</p>
                <p>Environment: ${params.ENV}</p>
                <p>Browser: ${params.BROWSER}</p>
                <p><a href="${env.BUILD_URL}HTML_20Extent_20Report/">View Extent Report</a></p>
                <p><a href="${env.BUILD_URL}console">View Console Output</a></p>
            """,
            to: 'nguyenquy1409@gmail.com',
            attachLog: false
        )
    }
        failure {
        echo "❌ Build failed. Check reports for details."
        emailext(
            subject: "❌ FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: """
                <h3>❌ Build Failed!</h3>
                <p>Project: ${env.JOB_NAME}</p>
                <p>Build Number: ${env.BUILD_NUMBER}</p>
                <p>Check the report:</p>
                <p><a href="${env.BUILD_URL}HTML_20Extent_20Report/">View Extent Report</a></p>
                <p><a href="${env.BUILD_URL}console">View Console Output</a></p>
            """,
            to: 'nguyenquy1409@gmail.com',
            attachLog: true
        )
    }
    }
}