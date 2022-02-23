pipeline{
    agent any
    stages{
        stage('Build do projeto Web com selenium'){
            steps{
                bat 'mvn clean install -DskipTests=true'
            }
			}
 	  stage('Subir Selenium Grid com Dois Nodes em Chrome'){
            steps{
				bat 'docker-compose up -d --scale chrome=2'
            }
			}
			
	  stage('Rodar testes funcionais em paralelo'){
            steps{
				bat 'mvn verify -Dthreads=8'
            }
			}
			
			stage ('Cucumber Reports') {

            steps {
                cucumber buildStatus: "UNSTABLE",
                    fileIncludePattern: "**/cucumber.json",
                    jsonReportDirectory: 'target'

            }

        }
	
}

	

post {
    always {
	junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
		
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


