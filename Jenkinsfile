pipeline { 
agent any 
tools{
    
    maven "maven"
}

	
    stages {
   
    stage ('Build Jar') { 
            steps{
            	git 'https://github.com/Dheeraj9013/Selenium_POM_MAVEN_Docker'
                bat "mvn clean install -DskipTests=true"

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
                	withSonarQubeEnv('sonar123'){
                	bat  "mvn sonar:sonar" 
  					  
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
 