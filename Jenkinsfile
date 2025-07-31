pipeline{

   agent any
   stages{
      stage('Build Jar'){
      steps{
      sh "mvn clean package -DskipTests"
      }
      }
      stage('Build image'){
      steps{
     sh "docker build -t=abhi2012/selenium ."
      }

      }

       stage('push image'){
         environment {
               DOCKER_HUB = credentials('97efe88c-4ad0-4bac-b601-12974dee7ee9')
             }
            steps{
              sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}'
              sh "docker push abksharm/selenium"
            }

            }
   }

   post{
   always{
   sh "docker logout"
   }
   }

}