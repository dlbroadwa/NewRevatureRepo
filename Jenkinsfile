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

  }
  environment {
    POSTGRES_URL = 'dlbroadwadb.cpbqys5iu3x8.us-east-2.rds.amazonaws.com'
    POSTGRES_USERNAME = 'postgres'
    POSTGRES_DATABASE_NAME = 'postgres'
    POSTGRES_PASSWORD = 'enter123'
    POSTGRES_PORT = '5432'
    POSTGRES_DEFAULT_SCHEMA = 'ebay_schema'
  }
}