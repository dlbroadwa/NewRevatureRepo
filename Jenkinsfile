pipeline {
  triggers {
      pollSCM('H/5 * * * *')
  }
  agent any
  
  stages {
    stage('Build') {
      parallel {
        stage('Build Auction service') {
          steps {
		    withMaven(maven: 'maven') {
				dir(path: 'Auction') {
				  sh 'mvn -B -DskipTests clean package'
				}
			}

          }
        }

        stage('Build Bidding service') {
          steps {
		    withMaven(maven: 'maven') {
				dir(path: 'BiddingService') {
				  sh 'mvn -B -DskipTests clean package'
				}
			}
          }
        }

        stage('Build User service') {
          steps {
		    withMaven(maven: 'maven') {
				dir(path: 'UserService') {
				  sh 'mvn -B -DskipTests clean package'
				}
			}
          }
        }

      }
    }

    stage('Test') {
      parallel {
        stage('Test Auction service') {
          steps {
		    withMaven(maven: 'maven') {
				dir(path: 'Auction') {
				  sh 'mvn test'
				}
			}
          }
        }

        stage('Test Bidding service') {
          steps {
		    withMaven(maven: 'maven') {
				dir(path: 'BiddingService') {
				  sh 'mvn test'
				}
			}
          }
        }

        stage('Test User service') {
          steps {
		    withMaven(maven: 'maven') {
				dir(path: 'UserService') {
				  sh 'mvn test'
				}
			}
          }
        }

      }
    }
	
	stage('Docker') {
	  parallel {
	    stage('Create Auction image') {
		  steps {
			 dir(path: 'Auction') {
			   script {
			     def image = docker.build Auction_Repo
				 image.push()
			   }
			 }
		  }
		}
		stage('Create Bidding image') {
		  steps {
			 dir(path: 'BiddingService') {
			   script {
			     def image = docker.build Bidding_Repo
				 image.push()
			   }
			 }
		  }
		}
		stage('Create User image') {
		  steps {
			 dir(path: 'UserService') {
			   script {
			     def image = docker.build User_Repo
				 image.push()
			   }
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
	DockerHub_Credentials = 'dockerhub_id'
	Auction_Repo = 'leeperry/g3p2-auction'
	Bidding_Repo = 'leeperry/g3p2-bidding'
	User_Repo = 'leeperry/g3p2-user'
  }
}