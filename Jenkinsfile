pipeline { 
agent any 
	
    stages { 
    
      
        
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
                	withSonarQubeEnv('SonarQube'){
                	  bat 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar' 
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
 