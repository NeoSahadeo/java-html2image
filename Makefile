FLAGS = -DskipTests

dev:
	mvn exec:java $(FLAGS)

all: clean normal

normal:
	mvn package $(FLAGS)

clean:
	mvn clean $(FLAGS)

.PHONY: clean dev normal all
