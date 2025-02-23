# Layer Architecture Framework

<div align="center">

[![GitHub Stars](https://img.shields.io/github/stars/ChamathDilshanC/Layer_Architecture_Final?style=for-the-badge&logo=github&color=yellow)](https://github.com/ChamathDilshanC/Layer_Architecture_Final/stargazers)
[![Java Version](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=java)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)
[![Maven](https://img.shields.io/badge/Maven-3.9.6-red?style=for-the-badge&logo=apache-maven)](https://maven.apache.org/)

</div>

## 🚀 Overview

A robust implementation of the layered architecture pattern for standalone Java applications. This framework demonstrates clean separation of concerns through well-defined service layers, data access patterns, and business logic organization. Built with Java 17+ and Maven, it provides a solid foundation for building maintainable Java applications.

## ✨ Key Features

<div align="center">

| Feature | Description |
|---------|------------|
| 🏗️ **Three-tier Architecture** | Clear separation between service, business, and data layers |
| 📦 **Modular Design** | Discrete Maven modules for better maintenance |
| 🔐 **JPA/Hibernate** | Type-safe database operations with connection pooling |
| 🧪 **Comprehensive Testing** | Unit and integration tests with JUnit 5 |
| 🛠️ **Build Automation** | Maven-based build lifecycle management |

</div>

## 🎯 Architecture

```mermaid
graph TD
    A[View Layer] --> B[Controller Layer]
    B --> C[Model Layer]
    C --> D[(Database)]
    
    style A fill:#ff9900,stroke:#333,stroke-width:2px
    style B fill:#00b8d4,stroke:#333,stroke-width:2px
    style C fill:#00c853,stroke:#333,stroke-width:2px
    style D fill:#7e57c2,stroke:#333,stroke-width:2px
```

## 🛠️ Tech Stack

<div align="center">

| Technology | Version | Purpose |
|------------|---------|----------|
| Java | 17+ | Core Programming Language |
| Maven | 3.9.6 | Build and Dependency Management |
| JUnit | 5.9.2 | Testing Framework |
| Mockito | 5.10.0 | Mocking Framework |
| H2 Database | 2.2.224 | Development Database |
| PostgreSQL | 15+ | Production Database |
| Hibernate | 6.2+ | ORM Framework |

</div>

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── view/          # View components and UI logic
│   │   ├── controller/    # Controllers handling user input
│   │   ├── model/         # Domain models and data access
│   │   └── config/        # Application configuration
│   └── resources/
│       ├── config/        # Configuration files
│       └── db/            # Database migration scripts
└── test/
    └── java/             # Unit and integration tests
```

## 🚀 Quick Start

### Prerequisites

- Java 17 JDK
- Maven 3.9+
- PostgreSQL 15+ (for production)

### Setup

```bash
# Clone the repository
git clone https://github.com/ChamathDilshanC/Layer_Architecture_Final.git

# Navigate to project directory
cd Layer_Architecture_Final

# Build the project
mvn clean install

# Run the application
java -jar target/layer-architecture.jar
```

## 🌟 Features in Detail

1. **Layered Architecture**
   - Clear separation between service, business, and data access layers
   - Dependency injection for loose coupling
   - Interface-based design for flexibility

2. **Data Access Layer**
   - JPA/Hibernate implementation
   - Connection pooling
   - Transaction management
   - Entity lifecycle management

3. **Business Layer**
   - Domain-driven design principles
   - Business logic encapsulation
   - Validation and business rules

4. **Testing**
   - Unit tests with JUnit 5
   - Integration tests
   - Mockito for mocking dependencies

## 🤝 Contributing

We welcome contributions! Here's how you can help:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📬 Connect With Me

<div align="center">

[![GitHub](https://img.shields.io/badge/GitHub-ChamathDilshanC-black?style=for-the-badge&logo=github)](https://github.com/ChamathDilshanC)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Chamath_Dilshan-blue?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/chamathdilsahnc/)

</div>

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

<div align="center">

Made with ❤️ by [Chamath Dilshan](https://github.com/ChamathDilshanC)

⭐ Star this repository if you find it helpful!

</div>
