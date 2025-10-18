pipeline {
    agent any

    environment {
        // Adjust these as needed for your environment
        MAVEN_HOME = tool name: 'Maven 3' 
        JAVA_HOME  = tool name: 'JDK 17'
        PATH       = "${MAVEN_HOME}/bin:${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Checking out source code..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Building with Maven (skip tests)…"
                sh "${MAVEN_HOME}/bin/mvn clean install -DskipTests=true"
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running TestNG tests…"
                sh "${MAVEN_HOME}/bin/mvn test -Dsurefire.suiteXmlFiles=testng.xml"
            }
            post {
                always {
                    echo "Publishing TestNG/JUnit reports…"
                    // Adjust path if the reports are under a different folder
                    junit '**/target/surefire-reports/*.xml'
                    // Optionally archive HTML reports
                    archiveArtifacts artifacts: '**/test-reports/**/*.html', allowEmptyArchive: true
                }
            }
        }

        stage('Package / Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo "Main branch build — packaging / deployment steps."
                // e.g., sh "${MAVEN_HOME}/bin/mvn deploy" or custom deploy script
                sh "${MAVEN_HOME}/bin/mvn clean package"
                // Archive artifact
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline completed successfully!"
        }
        failure {
            echo "❌ Pipeline failed."
        }
        always {
            echo "Cleaning up workspace…"
            cleanWs()
        }
    }
}
