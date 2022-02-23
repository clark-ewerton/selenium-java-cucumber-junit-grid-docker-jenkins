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
			
	  stage('Generate Cucumber HTML report') {
        cucumber buildStatus: 'UNSTABLE',
                reportTitle: 'My report',
                fileIncludePattern: '**/*.json',
                trendsLimit: 10,
                classifications: [
                    [
                        'key': 'Browser',
                        'value': 'Chrome'
                    ]
                ]
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
                        [key: 'Commit', value: '<a href="${GERRIT_CHANGE_URL}">${GERRIT_PATCHSET_REVISION}</a>'],
                        [key: 'Submitter', value: '${GERRIT_PATCHSET_UPLOADER_NAME}']
                ],
                reportTitle: 'My report',
                fileIncludePattern: '**/*cucumber-report.json',
                sortingMethod: 'ALPHABETICAL',
                trendsLimit: 100
    }
}
}


