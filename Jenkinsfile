pipeline {
    agent any

    tools {
        maven 'Maven3'   // Jenkins global tool config name
        jdk 'JDK11'      // Jenkins global tool config name
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Select browser')
        choice(name: 'ENV', choices: ['UAT', 'INT', 'PROD'], description: 'Select environment')
    }

    options {
        timestamps()   // show timestamps in logs
        ansiColor('xterm') // better console coloring
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/qubui/selenium2025.git'
            }
        }

        stage('Run Tests') {
            steps {
                sh "mvn clean test -Dbrowser=${params.BROWSER} -Denv=${params.ENV}"
            }
        }

        stage('Publish Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Publish HTML Report') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/extent-reports',
                    reportFiles: 'index.html',
                    reportName: 'Extent Report'
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