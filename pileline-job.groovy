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
                sh '/opt/maven/bin/mvn clean pacakage'
            }
        }
        stage('Test') {
            steps {
                echo 'test success'
            }
        }
                stage('Deploy') {
            steps {
                echo "Deploy success"
            }
        }
    }
}



