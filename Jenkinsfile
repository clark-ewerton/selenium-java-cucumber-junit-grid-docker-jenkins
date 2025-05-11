pipeline{
    agent any
	  docker {
            image 'openjdk:8-jdk' // Imagem com o JDK 8
            args '-v $HOME/.m2:/root/.m2' // (Opcional) Usa o cache local do Maven
        }
    stages{
        stage('Build do projeto Web com selenium'){
            steps{
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
	  stage('Rodar testes funcionais em paralelo'){
            steps{
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


