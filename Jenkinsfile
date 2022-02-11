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
        	steps{
        	def scannerHome = tool 'SonarQube';
    		withSonarQubeEnv('SonarQube') {
      		 bat "${scannerHome}/bin/sonar-scanner \
      		-D sonar.login=admin \
      		-D sonar.password=Mission@26\
      		-D sonar.projectKey=demoapp-project \
      		-D sonar.exclusions=vendor/**,resources/**,**/*.java \
      		-D sonar.host.url=http://localhost:9000/"
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
 