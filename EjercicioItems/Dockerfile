FROM java:8

WORKDIR /app/EjercicioItems/

# Copy the jar
COPY target/EjercicioItems.jar ./
# Copy input data
COPY src/test/java/* /tmp/docker/input/

# Volumes
VOLUME ["/tmp/docker/input", "/tmp/docker/output", "/tmp/docker/data/logs"]

# Run the jar (CMD)
CMD ["java", "-jar", "EjercicioItems.jar"]