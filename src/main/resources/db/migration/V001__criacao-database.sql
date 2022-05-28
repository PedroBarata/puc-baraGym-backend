/* DATABASE */

SELECT 'CREATE DATABASE ${ph_dbname} WITH ENCODING = "UTF8" LOCALE = "pt_BR.UTF-8" TEMPLATE template0'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '${ph_dbname}');