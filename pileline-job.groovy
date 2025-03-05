pipeline {
    agent any
    stages {
        stage('git_ckeckout') {
            steps {
                git branch: 'main', url: 'https://github.com/Anilbamnote/student-ui-app.git'
            }
        }
        stage('build') {
            steps {
                sh '/opt/maven/bin/mvn clean package'
            }
        }
        stage('Test') {
            steps {

            withSonarQubeEnv(installationName: 'sonar', credentialsId: 'new-sonar-cred') {
                sh '/opt/maven/bin/mvn sonar:sonar'
             }
              
             // sh '/opt/maven/bin/mvn sonar:sonar -Dsonar.projectKey=studentapp -Dsonar.host.url=http://3.254.124.73:9000 -Dsonar.login=5e9fd080ac18d58eff5043c49e098d18e10b7d81'
            }
        }
        stage('waitfor-gate') {
            steps {
             timeout(10) {
  
            }
               waitForQualityGate true
            }
        }
        stage('Deploy') {
            steps {
                echo "Deploy success"
            }
        }
    }
}



// pipeline {
//     agent  {label 'node1'}
//     stages {
//         stage('git_checkout') {
//             steps {
//                 git branch: 'main', url: 'https://github.com/Anilbamnote/student-ui-app.git'
//             }
//         }
//         stage('build') {
//             steps {
//                 sh '/opt/maven/bin/mvn clean package'
//             }
//         }
//         stage('test') {
//             steps {
//                withSonarQubeEnv(installationName: 'sonar', credentialsId: 'sonar-cred') {
//                  sh '/opt/maven/bin/mvn sonar:sonar'
//             }


//             }
//         }
//         stage('Quality_Gate') {
//             steps {
//              timeout(10) {
                
//                }
//                  waitForQualityGate true
//             }
//         }
//          stage('artifact-upload') {
//             steps {
//                sh 'aws s3 cp target/studentapp-2.2-SNAPSHOT.war  s3://my-terra-bucket00999'
//            }
//         }
    
//         stage('deploy') {
//             steps {
//                 deploy adapters: [tomcat9(credentialsId: 'tomacat-cred', path: '', url: 'http://47.128.241.201:8080')], contextPath: '/', war: '**/*.war'
//             }
//         }
//     }
// }