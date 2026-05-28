def call(Map config = [:]) {
    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    checkout scm
                }
            }

            stage('Build') {
                steps {
                    echo "Building ${config.appName}"
                    sh config.buildCommand
                }
            }

            stage('Test') {
                steps {
                    echo "Testing ${config.appName}"
                    sh config.testCommand
                }
            }

            stage('Scan') {
                steps {
                    echo "Scanning ${config.appName}"
                    sh config.scanCommand
                }
            }

            stage('Deploy') {
                steps {
                    echo "Deploying ${config.appName}"
                    sh config.deployCommand
                }
            }
        }

        post {
            success {
                echo "${config.appName} pipeline completed successfully."
            }

            failure {
                echo "${config.appName} pipeline failed."
            }
        }
    }
}