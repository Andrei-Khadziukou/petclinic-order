# petclinic-order

## Liquibase manual configuration
You can init database in 2 approach: from Gradle or manually.
1. Gradle tasks
- execute task 'gradlew createDatabase' to create user, db and schema
- execute task 'gradlew update' to add tables in db (liquibase changesets)
> For deleting db you should ensure disconnecting all usages, then you can execute task 'gradlew dropDatabase'

2. Manual
You need to execute next SQL queries to init PostgreSQL DB:
- CREATE USER pc_admin;
- Create DATABASE order_service OWNER pc_admin;
> if you work in console, after creating db you need to change db to order_service. Example: 'postgres# \connect order_service'
- CREATE SCHEMA IF NOT EXISTS app AUTHORIZATION pc_admin;
- Execute gradle task 'gradlew update' to update change sets;

After that, You can run application to add change set into db.

## Rest documentation
[Rest documentation](http://docs.spring.io/spring-restdocs/docs/1.2.0.RELEASE/reference/html5/) will be generated after building application
('gradlew build') in '../petclinic-order/build/asciidoc/html5' directory.

## Configuration
* Add `management.security.enabled: false` property into `application.yml` file if you need to connect to secured endpoints from actuator (don't commit that configuration)
