#!/bin/bash

################################################################################
# Bash Shell script to execute psql commands to create users-service-db database
################################################################################

set -e
export PGPASSWORD=$POSTGRES_PASSWORD;
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  CREATE USER $APP_DB_USER WITH PASSWORD '$APP_DB_PASSWORD';
  CREATE DATABASE $APP_DB;
  GRANT ALL PRIVILEGES ON DATABASE $APP_DB TO $APP_DB_USER;
EOSQL
