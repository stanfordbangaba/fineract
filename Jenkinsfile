pipeline {
    agent any
    triggers {
        pollSCM('* * * * *')
    }

    environment {
        DOCKER_IMAGE_TAG = "lms-core:0.0.1-SNAPSHOT";
    }

    stages {
        stage("Compile Main Project") {
            steps {
                echo "Compile project"
                sh './gradlew compileJava'
            }
        }
        stage("Build Main Jar") {
            steps {
                echo "Build main jar"
                sh "./gradlew bootJar -DskipTests=true"
            }
        }
        stage("Docker build") {
            steps {
                echo "Build docker image ${env.DOCKER_IMAGE_TAG}"
                sh "docker build -t ${env.DOCKER_IMAGE_TAG} ."
            }
        }
        stage("Deploy App on docker") {
            steps {
                echo "Deploy the app"
                sh 'docker ps -f name=lms-core -q | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -fname=lms-core -q | xargs -r docker container rm'
                sh 'docker run --name anotida/admin-core --restart unless-stopped --network=host -d -e DRIVERCLASS_NAME=org.drizzle.jdbc.DrizzleDriver -e PROTOCOL=jdbc -e SUB_PROTOCOL=mysql:thin -e fineract_tenants_driver=org.drizzle.jdbc.DrizzleDriver -e fineract_tenants_url=jdbc:mysql:thin://localhost:3306/fineract_tenants -e fineract_tenants_uid=root -e fineract_tenants_pwd=Password1# -e FINERACT_DEFAULT_TENANTDB_HOSTNAME=localhost -e FINERACT_DEFAULT_TENANTDB_PORT=3306 -e FINERACT_DEFAULT_TENANTDB_UID=root -e FINERACT_DEFAULT_TENANTDB_PWD=Password1# -e FINERACT_DEFAULT_TENANTDB_CONN_PARAMS= ${env.DOCKER_IMAGE_TAG}'
            }
        }
    }
}


