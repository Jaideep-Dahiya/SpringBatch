# Use the official MySQL image as the base image
FROM mysql
# Copy the SQL script into the container
COPY schema-mysql.sql /docker-entrypoint-initdb.d/
