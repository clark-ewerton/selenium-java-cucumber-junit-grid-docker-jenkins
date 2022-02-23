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
}
}


