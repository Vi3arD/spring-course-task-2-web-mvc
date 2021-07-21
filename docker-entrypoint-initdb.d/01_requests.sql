INSERT INTO users(userName, password)
VALUES ('user_one', 'secret');

INSERT INTO users(userName, password)
VALUES ('user_two', 'pass');

INSERT INTO orders(userId, orderNumber, amount, currency, returnUrl, failUrl, status, isDeleted)
VALUES (1, 'fffx', 3, 721, 'http://www.google.com/return', 'http://www.google.com/fail', 'finished', false);
