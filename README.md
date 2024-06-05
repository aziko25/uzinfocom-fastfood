# FastFood Delivery System API

## Overview
This API is designed for managing a food delivery system. It supports roles such as Admin, Waiter, and User, each with specific permissions. The system includes functionality for placing orders, calculating delivery times, and managing the order status.

## Login Credentials

### Admin
- **Phone:** +998946805777
- **Password:** admin

### Waiter
- **Phone:** +998900000000
- **Password:** waiter

### Users
- You can sign up with any phone number and password of your choice.

## Authorization

For each API request, the JWT token should be set in the `Authorization` header as follows:
Authorization: Bearer <JWT_TOKEN>


Alternatively, you can set the JWT token to the Collections Authorization Header as `Bearer`.

By default, the admin's JWT token is used for authentication.

## Order Ready Time Calculation

The order ready time is calculated based on the number of dishes in the orders and the distance to the delivery location. Here is the step-by-step logic:

1. **Fetch all new orders** that are in `CREATED` or `PROGRESS` statuses and have not been cooked yet.
2. **Calculate the total number of dishes** in these orders.
3. **Apply a multiplier** of 1.25 to the total number of dishes to estimate the cooking time.
4. **Add the delivery time** based on the distance in kilometers, with a multiplier of 3 minutes per kilometer.

### Example Calculation

Assume there are two existing orders before the current order:

- **Order 1:** 5 dishes
- **Order 2:** 6 dishes

Both orders are not cooked yet but are in `CREATED` or `PROGRESS` statuses. If the current order consists of 4 dishes and the distance to the delivery location is `distance km`, the calculation is as follows:

1. **Total number of dishes:** 5 (Order 1) + 6 (Order 2) + 4 (Current order) = 15 dishes
2. **Estimated cooking time:** 15 dishes * 1.25 = 18.75 minutes
3. **Delivery time:** 3 * distance km minutes

So, the total estimated time will be:

