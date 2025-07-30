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
     sh "docker build -t=abksharm/selenium ."
      }

      }

       stage('push image'){
            steps{
              sh "docker push abksharm/selenium"
            }

            }
   }

}