# Finance Payment Plan Calculator

## Description

This is a simple finance payment plan calculator that calculates the monthly payment, total interest, and total payment of a loan. The user can input the loan amount, interest rate, and loan term in years. The calculator will then display the monthly payment, total interest, and total payment of the loan.

## Table of Contents

* [Tools](#tools)

* [Installation](#installation)

* [Usage](#usage)

## Tools

Required tools to run this project:

* OpenJDK 17
* Gradle 8.2.1
* Kotlin 1.8.20
* MySQL 8.0+

## Installation

This application works with env variables, for the db connection

Environment Variables:
| Variable | Description |
|----------|-------------|
| DATABASE_HOST | The host of the database *i.e:* localhost:3306|
| DATABASE_NAME | The name of the database |
| DATABASE_USERNAME | The username of the database |
| DATABASE_PASSWORD | The password of the database |
| SECRET_KEY | The secret key used for JWT |

To install the necessary dependencies, run the following command:

``` sh
./gradlew build
```

## Usage

Tu run the web server

``` sh
./gradlew bootRun
```
