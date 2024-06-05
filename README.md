Login Credentials:
  Admin: 
    Phone: +998946805777
    Password: admin
  Waiter:
    Phone: +998900000000
    Password: waiter
  Users:
    You can sign up as you wish

JWT token should be set for each api in Authorization Header -> Bearer or you can set it to Collections Authorization Header -> Bearer
By default, now it is set to admins jwt
  
Solution for the logic with order ready time that I have implemented is next:

I fetch all new orders that are not cooked, but created and in progress, so I calculate 1.25 * for each dish quantity from these orders
and in the end, I just add 3 * distance km

Example:
There are 2 orders before me, order 1 has ordered 5 dishes in total and order 2 has ordered 6 dishes. Both are not cooked yet, but in CREATED and PROGRESS statuses, so if my order also consists of 4 dishes, then time will be calculated next:

5 + 6 + 4 = 15 * 1.25 = 18.75 + 3 * distance km
