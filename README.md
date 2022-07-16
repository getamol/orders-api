# Post request data

### POST Request 
```
http://localhost:8222/api/v1/orders/72914
```
### Data
```
{
    "customerName": "Pradeep",
    "customerEmail":"pradeep@corkery.com",
    "orderPrice":5000,
    "orderDate":"2022-07-15",
    "lineItems":[
       {
            "name":"rubber pad",
            "qty": 4,
            "price": 200
        }
    ]
}
```
