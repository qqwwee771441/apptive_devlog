#!/bin/bash

echo "🔄 Cleaning and building project..."
./gradlew clean build -x test

echo "🐳 Stopping and removing existing containers..."
docker-compose down

echo "🐳 Building Docker images..."
docker-compose build --no-cache

echo "🚀 Starting containers..."
docker-compose up
