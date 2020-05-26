pipeline {
    agent any

    stages {
        stage('Generate the Blog War') {
            steps {
		        withMaven(maven : 'mvn'){
			        sh 'mvn clean package -f blog/pom.xml'
		        }
            }
        }
		stage('Generate the Map War') {
            steps {
		        withMaven(maven : 'mvn'){
			        sh 'mvn clean package -f map/pom.xml'
		        }
            }
        }
        stage('Build the Blog Image'){
            steps{
                sh 'docker build blog/. -t blog:v0'
            }
        }
        stage('Build the Map Image'){
            steps{
                sh 'docker build -t map:v0 map/.'
            }
        }
        stage('Push the Blog Image'){
            steps{
                withDockerRegistry(credentialsId: 'ee315bca-26e6-4bef-b4fd-1195277d9f25', url: '') {
                        sh 'docker tag blog:v0 jpragasa/blog:latest'
                        sh 'docker push jpragasa/blog:latest'
                    }
            }
        }
        stage('Push the Map Image'){
            steps{
                withDockerRegistry(credentialsId: 'ee315bca-26e6-4bef-b4fd-1195277d9f25', url: '') {
                        sh 'docker tag map:v0 jpragasa/map:latest'
                        sh 'docker push jpragasa/map:latest'
                    }
            }
        }
    }
}
