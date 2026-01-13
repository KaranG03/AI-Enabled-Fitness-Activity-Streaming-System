# 🏋️ AI-Enabled Fitness Activity Streaming System  
### Real-time Microservices + Kafka Event Streaming + AI Recommendations  
🚀 Built with **Spring Boot Microservices**, **Apache Kafka**, **Spring Cloud Gateway**, **Eureka**, **Config Server**, **MongoDB + PostgreSQL**, **Docker**, and **React (Vite)**.

<p align="center">
  <a href="https://github.com/KaranG03/AI-Enabled-Fitness-Activity-Streaming-System">
    <img src="https://img.shields.io/badge/Status-Active-success?style=for-the-badge" />
  </a>
  <img src="https://img.shields.io/badge/Microservices-Spring%20Boot-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Event%20Streaming-Kafka-orange?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Frontend-React%20(Vite)-purple?style=for-the-badge" />
</p>

---


## 🌟 Overview  
This project is a **real-time AI-powered fitness platform** built using a **microservices architecture**.  
Users perform activities (running, workout sessions, calories, duration, etc.) which are streamed through **Kafka**, processed asynchronously, and used to generate **AI-driven fitness recommendations**.

⚡ Key focus areas:  
✅ Event-driven architecture  
✅ Real-time streaming with Kafka  
✅ Microservices with Spring Cloud  
✅ API Gateway routing + service discovery  
✅ Config Server centralized configuration  
✅ Polyglot persistence (PostgreSQL + MongoDB)  
✅ Docker-ready setup  
✅ React Vite frontend  

---

## 🧠 Why This Project is Special  
Most fitness apps are CRUD-based. This system is **streaming-first**:

📌 Instead of direct synchronous API calls between services, the system uses:  
🔥 **Kafka Topics → Consumer Groups → Async Processing → AI Recommendations**

This makes the system scalable, loosely coupled, and production-aligned.

---



## 🏗️ Architecture (High-Level)

```txt
                    ┌────────────────────┐
                    │   React (Vite UI)  │
                    └─────────┬──────────┘
                              │
                              ▼
                    ┌────────────────────┐
                    │  API Gateway (8080)│
                    │ Spring Cloud GW     │
                    └─────────┬──────────┘
                              │
     ┌────────────────────────┼─────────────────────────┐
     ▼                        ▼                         ▼
┌──────────────┐      ┌──────────────┐         ┌─────────────────┐
│ User Service  │      │ Activity Svc  │         │ AI Service       │
│ (PostgreSQL)  │      │ (MongoDB)     │         │ (MongoDB)        │
└──────┬────────┘      └──────┬────────┘         └────────┬────────┘
       │                      │                             │
       │                      ▼                             │
       │              ┌──────────────┐                      │
       │              │ Kafka Topic   │                      │
       │              │ activity-events│                     │
       │              └──────┬────────┘                      │
       │                     ▼                               │
       │          ┌─────────────────────┐                    │
       │          │ Kafka Consumer Group│                    │
       │          │ activity-processor  │                    │
       │          └─────────────────────┘                    │
       │                                                     ▼
       │                                           ┌─────────────────┐
       └──────────────────────────────────────────►│ AI Recommendation │
                                                   │ Generation Engine │
                                                   └─────────────────┘

          ┌─────────────────────────────────────────────────────────┐
          │ Eureka Server (Service Discovery) + Config Server (8888) │
          └─────────────────────────────────────────────────────────┘


 

## ✅ Features

- 👤 **User registration + profile APIs**
- 🏃 **Activity tracking** (duration, calories, type, timestamp)
- 📡 **Kafka-based real-time activity streaming**
- 🤖 **AI-based recommendation generation**
- 🌐 **API Gateway routing for all services**
- 🔍 **Service Discovery using Eureka**
- ⚙️ **Centralized configuration via Config Server**
- ⚡ **Kafka Streaming**
  - Producer: Activity Service publishes events
  - Consumer: AI Service listens via `@KafkaListener`
- 🧩 **Microservices Ready**
  - Independent deployments
  - Independent databases
  - Load balancing via `lb://SERVICE-NAME`

---

## 🛠️ Tech Stack

**Backend**
- Java 21+
- Spring Boot
- Spring Cloud Gateway
- Spring Cloud Config Server
- Netflix Eureka
- Spring Kafka
- Spring Data JPA + Hibernate
- Spring Data MongoDB
- Spring Validation

**Messaging**
- Apache Kafka (Docker)

**Databases**
- PostgreSQL (User Service)
- MongoDB (Activity + AI Recommendation Services)

**Frontend**
- React + Vite

**DevOps / Tools**
- Docker
- Git + GitHub
- Postman

---

## 📦 Services & Ports

| Service Name (Eureka) | Port | Database   |
|------------------------|------|------------|
| config-server          | 8888 | -          |
| eureka-server          | 8761 | -          |
| gateway-service        | 8080 | -          |
| user-service           | 8081 | PostgreSQL |
| activity-service       | 8082 | MongoDB    |
| ai-service             | 8083 | MongoDB    |

---

## 📌 Kafka Setup

- **Topic Used:** `activity-events`
- **Consumer Group:** `activity-processor-group`

---

## 🚀 Run Locally (Step-by-Step)

1️⃣ **Start Infrastructure (Kafka + MongoDB + PostgreSQL)**  
```bash
docker ps
Kafka should be available at localhost:9092.

List topics:

bash
docker exec <KAFKA_CONTAINER_NAME> /opt/kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
Create topic if missing:

bash
docker exec <KAFKA_CONTAINER_NAME> /opt/kafka/bin/kafka-topics.sh \
  --bootstrap-server localhost:9092 \
  --create \
  --topic activity-events \
  --partitions 1 \
  --replication-factor 1
2️⃣ Start Config Server

bash
cd configserver
mvn spring-boot:run
Verify:

bash
curl http://localhost:8888/gateway-service/default
3️⃣ Start Eureka Server

bash
cd eurekaserver
mvn spring-boot:run
Verify: http://localhost:8761

4️⃣ Start Gateway

bash
cd gateway
mvn spring-boot:run
5️⃣ Start User Service

bash
cd userservice
mvn spring-boot:run
6️⃣ Start Activity Service

bash
cd activityservice
mvn spring-boot:run
7️⃣ Start AI Service

bash
cd aiservice
mvn spring-boot:run
🌐 Gateway Routing (Example)
Direct Service Call:

http
GET http://localhost:8081/api/users/{userId}
Through Gateway:

http
GET http://localhost:8080/api/users/{userId}
🧪 API Examples
✅ Register User

http
POST /api/users/register
Content-Type: application/json
json
{
  "firstName": "Karan",
  "lastName": "Gautam",
  "email": "karangautam1234@gmail.com",
  "password": "Karang8290@"
}
✅ Get User Profile

http
GET /api/users/{userId}
✅ Validate User

http
GET /api/users/{userId}/validate
✅ Publish Activity Event

http
POST /api/activities
Content-Type: application/json
json
{
  "userId": "f97e8a29-b9c4-4054-8dab-d0382d6a7fbc",
  "type": "RUNNING",
  "durationMinutes": 30,
  "caloriesBurned": 250
}
🧠 AI Recommendation Flow
Activity Service publishes event to Kafka topic activity-events

AI Service consumes the event asynchronously

AI Service generates a recommendation based on:

Activity type

Intensity

Calories

Duration

Recommendation is stored in MongoDB and can be fetched via APIs

🔒 Security (Planned / In Progress)
JWT authentication (Gateway-level)

Role-based access control (RBAC)

Secure REST API design aligned with OWASP

📌 What I Learned
Designing microservices with real-world patterns

Kafka producers/consumers + JSON serialization issues

Debugging deserialization + type header conflicts

Eureka service discovery + gateway routing

Centralized config with Spring Cloud Config

Docker-based Kafka debugging with CLI tools

📈 Future Improvements
Add JWT authentication + Keycloak integration

Add rate limiting + circuit breaker (Resilience4j)

Add distributed tracing (Zipkin / OpenTelemetry)

Add metrics dashboard (Prometheus + Grafana)

Improve AI recommendation engine with better prompts + context

👨‍💻 Author
Karan Gautam  
📍 Chennai, India
🔗 GitHub: KaranG03  
🔗 LinkedIn: karan-gautam2834




