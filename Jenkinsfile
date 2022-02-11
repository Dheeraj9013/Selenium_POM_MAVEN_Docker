pipeline { 
agent any 
	
    stages { 
    
      stage('SCM') {
            steps {
                git url: 'https://github.com/Dheeraj9013/Selenium_POM_MAVEN_Docker'
            }
        }
        
        stage ('Build Jar') { 
            steps{
                bat "mvn clean install -DskipTests"

            }
        }
        
        //stage('docker-grid') {
            //steps {
               
           //    bat "docker-compose up -d"
         //   }
       // }
        
        stage('Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat "mvn clean install"
                }
            }
        }
        
         
       
        stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    // Optionally use a Maven environment you've configured already
             
                        bat 'mvn sonar:sonar'
                    
                }
            }
            }
        
        
        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: false, 
                                  reportDir: 'build', 
                                  reportFiles: 'TestExecutionReport.html', 
                                  reportName: 'HTML Extent Report', 
                                  reportTitles: ''])
            }
        }
        
        
        
    
        
                
     
        
        
        
        }

 }
 