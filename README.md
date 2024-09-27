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



## API Endpoints

**The application will start, and you can access the API endpoints at** `http://localhost:8080` :


#### Query Parameters

**All endpoints that return a list can have the following query parameters**

- `active` (optional): Filter for active items (boolean).
- `endingBefore` (optional): A cursor for pagination, representing the item before which the current page starts.
- `limit` (optional): Maximum number of items to return (1-100, default is 10).
- `shippable` (optional): Filter for shippable items (boolean).
- `startingAfter` (optional): A cursor for pagination, representing the item after which the current page starts.
- `url` (optional): Filter by URL.

**Response**
Returns a list of objects.


### Product Controller

Manage Stripe products, which represent the goods or services you offer.

 **Add a new product**
 
 `POST /products/`  
  ```json
{
  "object": "product",
  "active": true,
  "livemode": false,
  "name": "one time product"
}
  ```
 
- `GET /products/{id}`:Get a product by ID
- `GET /products/`: List products

### Price Controller

Handle pricing for your products, including different currencies and billing models.

 **Add a new price**
 
`POST /prices/`
```json
{
    "object": "price",
    "active": true,
    "billing_scheme": "per_unit",
    "currency": "usd",
    "livemode": false,
    "product": {
        "id": "prod_Qss27oIva74oZy"
    },
    "type": "one_time",
    "unit_amount": 500,
    "unit_amount_decimal": "500"
}
```

- `GET /prices/{id}`: Get a price by ID
- `GET /prices/`: List prices

### Invoice Controller

Create and manage invoices for your customers.

**Create a new invoice**
 `POST /invoices/`
 ```json
{
    "object": "invoice",
    "account_country": "US",
    "account_name": "Stripe Docs",
    "billing_reason": "manual",
    "charge": null,
    "collection_method": "send_invoice",
    "created": 1680644467,
    "currency": "usd",
    "custom_fields": null,
    "customer_address": null,
    "customer_email": "jennyrosen@example.com",
    "customer_name": "Jenny Rosen",
    "customer_phone": null,
    "customer_shipping": null,
    "customer_tax_exempt": "none",
    "customer_tax_ids": [],
    "default_payment_method": null,
    "default_source": null,
    "default_tax_rates": [],
    "description": null,
    "discount": null,
    "discounts": [],
    "due_date": "1727184338",
    "ending_balance": null,
    "footer": null,
    "from_invoice": null,
    "hosted_invoice_url": null,
    "invoice_pdf": null,
    "issuer": {
        "type": "self"
    },
    "last_finalization_error": null,
    "latest_revision": null,
    "livemode": false,
    "metadata": {},
    "next_payment_attempt": null,
    "number": null,
    "on_behalf_of": null,
    "paid": false,
    "paid_out_of_band": false,
    "payment_intent": null,
    "payment_settings": {
        "default_mandate": null,
        "payment_method_options": null,
        "payment_method_types": null
    },
    "post_payment_credit_notes_amount": 0,
    "pre_payment_credit_notes_amount": 0,
    "quote": null,
    "receipt_number": null,
    "rendering_options": null,
    "shipping_cost": null,
    "shipping_details": null,
    "starting_balance": 0,
    "subscription": null,
    "subtotal": 0,
    "subtotal_excluding_tax": 0,
    "tax": null,
    "test_clock": null,
    "total": 0,
    "total_discount_amounts": [],
    "total_excluding_tax": 0,
    "total_tax_amounts": [],
    "transfer_data": null,
    "webhooks_delivered_at": 1680644467
}
 ```
- `GET /invoices/{id}`: Get an invoice by ID
- `GET /invoices/`: List invoices
- `POST /invoices/{id}/send`: Send an invoice for manual payment
- `POST /invoices/{id}/finalize`: Finalize an invoice
- `POST /invoices/{id}/pay`: Pay an invoice

### Invoice Items Controller

Manage individual line items on invoices.

**Create a new invoice item**
 `POST /invoice-items/`
 ```json
{
    "customer": {
        "id": "cus_QrDvQTYJyeIMDP"
    },
    "price": {
        "id": "price_1Q18HpBfiIectqtG3CbtOa9g"
    }
}
 ```
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

 **Add a new webhook endpoint**
 
`POST /webhooks/`
```json
{
  "object": "webhook_endpoint",
  "api_version": null,
  "application": null,
  "description": null,
  "enabled_events": [
    "*"
  ],
  "livemode": false,
  "metadata": {},
  "secret": "whsec_wRNftLajMZNeslQOP6vEPm4iVx5NlZ6z",
  "status": "enabled",
  "url": "https://25a8-154-178-246-69.ngrok-free.app/webhookendpoints/"
}
```

- `GET /webhooks/`: List webhook endpoints
- `GET /webhooks/{id}`: Get a webhook endpoint by ID

### Webhook Endpoints

- `POST /webhookendpoints/`: Receive webhook notifications
