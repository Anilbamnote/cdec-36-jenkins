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
                sh '/opt/maven/bin/mvn sonar:sonar -Dsonar.projectKey=studentapp -Dsonar.host.url=http://3.254.124.73:9000 -Dsonar.login=5e9fd080ac18d58eff5043c49e098d18e10b7d81'
            }
        }
                stage('Deploy') {
            steps {
                echo "Deploy success"
            }
        }
    }
}



