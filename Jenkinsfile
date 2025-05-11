pipeline {
    agent any
    environment {
        MAVEN_OPTS = "--add-opens java.base/java.util=ALL-UNNAMED"
        JSON_DIR = "${WORKSPACE}/json"
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests=true'
            }
        }
        stage('Rodar testes funcionais em paralelo') {
            withEnv(['MAVEN_OPTS=--add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED'])
            steps {
                sh 'mvn verify -Dthreads=8'
            }
        }
    }

    post {
        always {
            cucumber buildStatus: 'UNSTABLE',
                failedFeaturesNumber: 1,
                failedScenariosNumber: 1,
                skippedStepsNumber: 1,
                failedStepsNumber: 1,
                classifications: [
                    [key: 'Commit', value: '<a href="Clark Ewerton">Clark Ewerton</a>'],
                    [key: 'Submitter', value: 'Clark Ewerton']
                ],
                reportTitle: 'My report',
                fileIncludePattern: '**/*.json',
                sortingMethod: 'ALPHABETICAL',
                trendsLimit: 100,
                jsonReportDirectory: 'json'
        }
    }
}
