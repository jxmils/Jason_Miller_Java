-- Jason Miller M5

-- Question 1: What are the categories of products in the database?
SELECT category
FROM products;

-- Question 2: What products are made by Dell?
SELECT *
FROM products 
WHERE product_name LIKE 'Dell%';

-- Question 3: List all the orders shipped to Pennsylvania.
SELECT *
FROM orders
WHERE ship_state = 'Pennsylvania';

-- Question 4: List the first name and last name of all employees with last names that start with the letter W.
SELECT first_name, last_name
FROM employees
WHERE last_name LIKE 'W%';

-- Question 5: List all customers from zip codes that start with 55.
SELECT *
FROM customers
WHERE postal_code LIKE '55%';

-- Question 6: List all customers from zip codes that end with 0.
SELECT *
FROM customers
WHERE postal_code LIKE '%0';

-- Question 7: List the first name, last name, and email for all customers with a ".org" email address.
SELECT first_name, last_name, email
FROM customers
WHERE email LIKE '%.org';

-- Question 8: List the first name, last name, and phone number for all customers from the 202 area code.
SELECT first_name, last_name, phone
FROM customers
WHERE Phone LIKE '%(202)%';

-- Question 9: List the first name, last name, and phone number for all customers from the 202 area code, ordered by last name, first name.
SELECT first_name, last_name, phone
FROM customers
WHERE Phone LIKE '%(202)%'
ORDER BY last_name, first_name;