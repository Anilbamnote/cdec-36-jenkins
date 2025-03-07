pipeline {
    agent any
    stages {
        stage('Pull') {
            steps {
                git branch: 'main', url: 'https://github.com/Anilbamnote/oncdec-b18.git'
            }
        }
        stage('Test') {
            steps {
                sh ''' 
                    cd terraform/eks
                    terraform init
                    terraform plan
                    
                    '''
            }
        }
        stage('Deploy') {
            steps {
                sh ''' cd terraform/eks
                    terraform init
                    terraform apply --auto-approve'''
            }
        }
    }
}