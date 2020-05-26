pipeline {
  triggers {
      pollSCM('H/5 * * * *')
  }
  agent {
    docker {
      image 'maven:3-jdk-8-alpine'
      args '-v /root/.m2:/root/.m2 -v /usr/bin/docker:/usr/bin/docker -v /var/run/docker.sock:/var/run/docker.sock --privileged'
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
          post {
            always {
              junit 'Auction/target/surefire-reports/*.xml'
            }

          }
          steps {
            dir(path: 'Auction') {
              sh 'mvn test'
            }

          }
        }

        stage('Test Bidding service') {
          post {
            always {
              junit 'BiddingService/target/surefire-reports/*.xml'
            }

          }
          steps {
            dir(path: 'BiddingService') {
              sh 'mvn test'
            }

          }
        }

        stage('Test User service') {
          post {
            always {
              junit 'UserService/target/surefire-reports/*.xml'
            }

          }
          steps {
            dir(path: 'UserService') {
              sh 'mvn test'
            }

          }
        }

      }
    }
	
	stage('Docker Build') {
	  steps {
	    dir(path: 'Auction') {
		  sh 'docker --version'
		  sh '/usr/bin/docker --version'
		  sh 'pwd'
		  sh 'whoami'
		}
	  }
	}
  }
  environment {
    POSTGRES_URL = 'dlbroadwadb.cpbqys5iu3x8.us-east-2.rds.amazonaws.com'
    POSTGRES_USERNAME = 'postgres'
    POSTGRES_DATABASE_NAME = 'postgres'
    POSTGRES_PASSWORD = 'enter123'
    POSTGRES_PORT = '5432'
    POSTGRES_DEFAULT_SCHEMA = 'ebay_schema'
	DockerHub_Credentials = 'dockerhub_id'
	Auction_Repo = 'leeperry/g3p2-auction'
	Bidding_Repo = 'leeperry/g3p2-bidding'
	User_Repo = 'leeperry/g3p2-user'
  }
}