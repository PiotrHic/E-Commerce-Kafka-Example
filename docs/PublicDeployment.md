## Public Deployment

The application is deployed and publicly accessible at:

**URL:** [https://e-commerce-kafka-example.onrender.com](https://e-commerce-kafka-example.onrender.com)

### Testing the endpoint

You can test the order event endpoint by sending a POST request:

```bash
curl -X POST https://e-commerce-kafka-example.onrender.com/api/orders/events \
  -H "Content-Type: application/json" \
  -d '{
        "shipmentNumber": "12345",
        "recipientEmail": "test@example.com",
        "recipientCountryCode": "US",
        "senderCountryCode": "PL",
        "statusCode": 10
      }'