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
        stage('Subir Selenium Grid com Dois Nodes em Chrome') {
            steps {
                git url: 'https://github.com/clark-ewerton/selenium-java-cucumber-junit-grid-docker-jenkins.git', branch: 'master'
                dir("${env.WORKSPACE}") {
                    sh 'docker-compose -p selenium_${BUILD_NUMBER} up -d --scale chrome=2'
                }
            }
        }

        stage('Rodar testes funcionais em paralelo') {
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
