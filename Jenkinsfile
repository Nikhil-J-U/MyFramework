pipeline {
    agent any
    
    stages {
        
        stage("Build") {
            steps {
                echo("Build the project")
            }
        }
        
        stage("RUN UT's") {
            steps {
                echo("RUN UT's")
            }
        }
        
        stage("Deploy to DEV ") {
            steps {
                echo("deploying to dev env")
            }
        }
        
        stage("Deploy to QA ") {
            steps {
                echo("deploying to QA env")
            }
        }
        
        stage("RUN Automation REG test ") {
            steps {
                echo("run automation reg test")
            }
        }
        
        stage("Deploy to Stage ") {
            steps {
                echo("deploying to Stage env")
            }
        }
        
        
        stage("Deploy to PROD ") {
            steps {
                echo("deploying to prod env")
            }
        }
    }
}