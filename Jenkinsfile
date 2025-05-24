pipeline{
    agent any
    stages{
        stage('Build'){
            steps{
                bat 'mvn clean install -DskipTests=true'
            }
			}
 	  stage('Start up selenium grid with 2 chrome nodes'){
            steps{
				bat 'docker-compose up -d --scale chrome=2'
            }
			}

	  stage('Run e2e tests in paralell'){
            steps{
				bat 'mvn verify -Dthreads=8'
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
