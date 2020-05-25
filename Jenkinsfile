pipeline {
  agent {
    docker {
      image 'maven:3-jdk-8-alpine'
      args '-v /root/.m2:/root/.m2'
    }
  }
  stages {
    stage('Build') {
      parallel {
        stage('Build Auction service') {
          steps {
			dir(path: 'Auction') {
			  sh 'mvn -B -DskipTests clean package'
			}
          }
        }

        stage('Build Bidding service') {
          steps {
            dir(path: 'BiddingService') {
              sh 'mvn -B -DskipTests clean package'
            }
          }
        }

		stage('Build User service') {
          steps {
            dir(path: 'UserService') {
              sh 'mvn -B -DskipTests clean package'
            }
          }
        }
      }
    }

    stage('Test') {
      parallel {
        stage('Test Auction service') {
          steps {
			dir(path: 'Auction') {
			  sh 'mvn test'
			}
          }
		  post {
			always {
				junit 'Auction/surefire-reports/*.xml'
			}
		  }
        }

        stage('Test Bidding service') {
          steps {
            dir(path: 'BiddingService') {
              sh 'mvn test'
            }
          }
		  post {
			always {
				junit 'BiddingService/surefire-reports/*.xml'
			}
		  }
        }

		stage('Test User service') {
          steps {
            dir(path: 'UserService') {
              sh 'mvn test'
            }
          }
		  post {
			always {
				junit 'UserService/surefire-reports/*.xml'
			}
		  }
        }
      }
    }
  }
}