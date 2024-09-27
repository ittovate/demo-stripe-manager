# Stripe Manager

Stripe Manager is a Spring Boot application that provides a comprehensive interface to interact with various Stripe API endpoints. It includes controllers for managing balances, invoices, payment links, prices, products, and webhooks.


## Features

- Balance management
- Invoice creation and management
- Payment link generation
- Price and product management
- Webhook handling


## Prerequisites

- Java 21
- Maven
- Stripe account and API key

## Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/StripeManager.git
   ```
2. Navigate to the project directory:
   ```
   cd StripeManager
   ```
## Usage

The application can be run in several ways, depending on your development environment and preferences.

#### Option 1: Using Maven (Command Line)

If you have Maven installed on your system:

1. Open a terminal or command prompt.
2. Run the following command:

```
mvn spring-boot:run
```

#### Option 2: Using IntelliJ IDEA

IntelliJ IDEA can use the Maven configuration to run your Spring Boot application:

1. Open the project in IntelliJ IDEA.
2. Open the Maven tool window (View -> Tool Windows -> Maven).
3. Expand your project and navigate to Plugins -> spring-boot -> spring-boot:run.
4. Double-click on `spring-boot:run` to start the application.


#### Option 3: Using IntelliJ IDEA


1. Open the project in IntelliJ IDEA .
2. Locate the main class file `StripeManagerApplication `.
3. Right-click on the main class file and select `Run StripeManagerApplication` .

**The application will start, and you can access the API endpoints at** `http://localhost:8080` :



## API Endpoints

### Product Controller

Manage Stripe products, which represent the goods or services you offer.

- `POST /products/`: Add a new product
- `GET /products/{id}`: Get a product by ID
- `GET /products/`: List products

### Price Controller

Handle pricing for your products, including different currencies and billing models.

- `POST /prices/`: Add a new price
- `GET /prices/{id}`: Get a price by ID
- `GET /prices/`: List prices

### Invoice Controller

Create and manage invoices for your customers.

- `POST /invoices/`: Create a new invoice
- `GET /invoices/{id}`: Get an invoice by ID
- `GET /invoices/`: List invoices
- `POST /invoices/{id}/send`: Send an invoice for manual payment
- `POST /invoices/{id}/finalize`: Finalize an invoice
- `POST /invoices/{id}/pay`: Pay an invoice

### Invoice Items Controller

Manage individual line items on invoices.

- `POST /invoice-items/`: Create a new invoice item
- `GET /invoice-items/{id}`: Get an invoice item by ID
- `GET /invoice-items/`: List invoice items


### Payment Link Controller

Generate and manage payment links for one-time purchases.

- `POST /payment-links/`: Create a new payment link
- `GET /payment-links/`: List payment links
- `GET /payment-links/{id}`: Get a payment link by ID


### Balance Controller

View your Stripe account balance and transaction history.

- `GET /balance/`: Get account balance
- `GET /balance/balance-transactions`: List balance transactions
- `GET /balance/balance-transactions/{id}`: Get a specific balance transaction

### Webhook Controller

Handle incoming webhook events from Stripe.

Set up and manage webhook endpoints to receive real-time event notifications from Stripe.

- `POST /webhooks/`: Add a new webhook endpoint
- `GET /webhooks/`: List webhook endpoints
- `GET /webhooks/{id}`: Get a webhook endpoint by ID

### Webhook Endpoints

- `POST /webhookendpoints/`: Receive webhook notifications
