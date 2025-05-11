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
            script {
                def projectName = "selenium_${BUILD_NUMBER}"

                // Verifica se já existe o container selenium-hub e derruba se necessário
                sh """
                    echo "Verificando containers existentes..."
                    if [ \$(docker ps -a --format '{{.Names}}' | grep -w 'selenium-hub' | wc -l) -gt 0 ]; then
                        echo "Container selenium-hub já existe. Removendo com docker-compose down..."
                        docker-compose -p selenium_5 down || true
                    fi
                """

                // Agora sobe o grid com chrome em escala 2
                sh "docker-compose -p ${projectName} up -d --scale chrome=2"
            }
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
