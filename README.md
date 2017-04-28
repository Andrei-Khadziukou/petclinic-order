# petclinic-order

## Liquibase manual configuration
You need to execute next SQL queries to init PostgreSQL DB:
- CREATE USER pc_admin;
- Create DATABASE order_service OWNER pc_admin;
- CREATE SCHEMA IF NOT EXISTS app AUTHORIZATION pc_admin;
> if you work in console, you need to change db to order_service. Example: 'postgres# \connect order_service'
- Execute gradle task 'gradlew update' to update change sets;

After that, You can run application to add change set into db.

## Configuration
* Add `management.security.enabled: false` property into `application.yml` file if you need to connect to secured endpoints from actuator (don't commit that configuration)
