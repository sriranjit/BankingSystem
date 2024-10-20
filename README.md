# Banking System

## Overview

The Banking System project is a console-based application that allows users to manage their bank accounts. Users can create accounts, deposit and withdraw funds, and check their account balances. The application utilizes a MySQL database to store user account information and transaction records.

## Features

- Create a new bank account
- Deposit funds into an account
- Withdraw funds from an account
- Check the current balance of an account
- Transaction logging to a text file

## Technologies Used

- Java
- MySQL
- JDBC (Java Database Connectivity)

## Database Setup

### MySQL Installation

1. **Install MySQL**: If you haven't already, download and install MySQL from the [official website](https://www.mysql.com/downloads/).

### Create Database and Tables

1. **Log in to MySQL**: Open your command line interface and log in to MySQL:

   ```bash
   mysql -u root -p
   ```

   Enter your password when prompted.

2. **Create the database**: Execute the following command to create a new database:

   ```bash
   CREATE DATABASE banking_system;
   ```

3. **Use the database**:

   ```bash
   USE banking_system;
   ```

4. **Create the tables**: Run the following SQL commands to create the necessary tables:

   ```bash
   CREATE TABLE users (
   account_number INT PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   balance INT NOT NULL
   );

   CREATE TABLE transactions (
   account_number INT NOT NULL,
   transaction_type VARCHAR(50) NOT NULL,
   amount INT NOT NULL,
   FOREIGN KEY (account_number) REFERENCES users(account_number)
   );
   ```

5. **Exit MySQL**:

   ```bash
   EXIT;
   ```

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/sriranjit/BankingSystem.git
   cd BankingSystem
   ```

2. Compile the Java files:

   ```bash
   javac -d bin src/Banking/*.java src/Main.java
   ```

3. Run the application:

   ```bash
   java -cp bin Main
   ```

## Usage

1. Run the program, and you'll be presented with a menu to select different banking operations.
2. Follow the prompts to create an account, deposit, withdraw, or check your balance.

## Logging

All transactions are logged to a file named `bank_transaction.txt`, which will be created in the project directory upon the first transaction.

## Author

Sri Ranjit M  
Email: [sriranjitmohan26@gmail.com](mailto:sriranjitmohan26@gmail.com)
GitHub: [sriranjit](https://github.com/sriranjit)
