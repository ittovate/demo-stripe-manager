# Demo Stripe Manager

Stripe Manager is a Spring Boot application that provides a comprehensive interface to interact with various Stripe API endpoints. It includes controllers for managing balances, invoices, payment links, prices, products, and webhooks.



## What is the Stripe API?

The Stripe API is a set of endpoint provided by Stripe, a leading online payment processing platform. It allows developers to integrate Stripe's payment processing capabilities into their own applications, websites, or services

## Purpose of This Project

The primary purpose of this project is to demonstrate how developers can effectively integrate with the Stripe API. 

## Key Features of the Stripe API

 **1-** Balance management
 
 **2-** Invoice catalog management
 
 **3-** Payment link generation
 
 **4-** price catalog management
 
 **5-** product catalog management
 
 **6-** notification management 
 
## Features

### Balance management

 1. The balance is like a summary of how much money you have in your Stripe account.
    
 2. You can check your balance anytime to see how much money is there.
    
 3. You can also look at your balance history. This shows you a list of past transactions 
    
### Invoice catalog management

 `Invoices` are used when you want to formally bill a customer and include detailed transaction information.
 
Invoice lifecycle : 

**1-**  A newly created invoice has `draft` status.

**2-** Stripe finalizes an invoice when it’s ready to be paid by changing its status to `open`.
   You can no longer change most details of a finalized invoice.
      
**3-** Stripe can wait for the customer to pay the invoice or automatically attempt to pay it using the 
      customer’s default payment method.
      
 > If payment succeeds, Stripe updates the invoice status to `paid`.

 >  If payment fails, the invoice remains `open`.
        
**4-** Optionally, you can change the status of an unpaid invoice to void or uncollectible. 

### Payment link generation
 
Payment Links let you sell online without needing to create a website or app.

Payment links allow you to offer this functionality through subscriptions. Your payment provider will supply you with a secure payment page, where customers will be directed to enter their payment information when they click on the link.

Examples of payment link usage:

 Register for an event: Use a payment link for event registration, e.g., "Sign up for Conference - $199"

### price catalog management

**1-** Price catalog management allows you to create, update, and organize your product pricing.

**2-** Prices can be configured as one-time charges or recurring subscriptions with various billing intervals   (e.g., monthly, annually).

**3-** Price management integrates with other Stripe features, allowing you to use these prices in invoices, checkout sessions, and subscription management

**4-** Price catalog management helps ensure consistent pricing across your platform and simplifies the process of updating prices or introducing new pricing models.


### product catalog management

Create and organize products (physical goods, digital items, services)

Integrate products with pricing, payment links, and invoices. 

Easily update prices across the system by modifying the product-price association

Generate payment links  for single products or product bundles Customize payment links

Use products as the central entity to maintain consistency across all payment features


### notification management 

Webhook endpoints are special URLs that you set up to get notifications when certain things happen in your Stripe account or connected accounts.
    
When an event occurs (like a payment or a refund), Stripe sends a message to your webhook endpoint to let you know.

send an email to the customer who paid to let them know that their payment is received, payment
method was added to their account 

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

## User Journey

### Admin Actions

1. **Create Products**  
   Use the Stripe API to create products. Refer to the [Stripe Documentation](https://docs.stripe.com/api/products/create) for details on how to implement this.

2. **Create Prices**  
   Set prices and associate them with existing products. Check the [Stripe Documentation](https://docs.stripe.com/api/prices/create) for guidance.

3. **Create Payment Links**  
   Generate payment links for customers with either:
   - A fixed amount
   - Based on one or more purchased products  
   See the [Stripe Documentation](https://docs.stripe.com/api/payment-link/create) for implementation details.

4. **Create Invoices**  
   Generate, finalize, and process invoices for customer payments. You can also send invoices via email for payment. Refer to the [Stripe Documentation](https://docs.stripe.com/api/invoices/create) for further details.

5. **Financial Management**  
   Manage financial data by retrieving account balances and listing balance transactions. For more information, visit the [Stripe Documentation](https://docs.stripe.com/api/balance).



### Customer Actions

- **Receive Invoices**  
  Customers will receive invoices via email.

- **Make Payments**  
  Customers can pay invoices through:
  - A link provided in the email invoice
  - A custom payment link created by the admin

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



## More details about each point.  read the documentation.

**products** 
```
https://docs.stripe.com/api/products
```
**prices**  
```
https://docs.stripe.com/api/prices
```
**payment-link** 
```
https://docs.stripe.com/api/payment-link
```

**invoices**
```
https://docs.stripe.com/api/invoices
```
**invoiceitems**

```
https://docs.stripe.com/api/invoiceitems
```
**balance**
```
https://docs.stripe.com/api/balance
```

**webhook_endpoints**

```
https://docs.stripe.com/api/webhook_endpoints
```
