CREATE TABLE orders (
  id BIGSERIAL PRIMARY KEY,
  userName TEXT NOT NULL,
  password TEXT NOT NULL,
  orderNumber TEXT NOT NULL,
  amount INTEGER NOT NULL DEFAULT 0 CHECK (amount >= 0),
  currency INTEGER NOT NULL DEFAULT 810 CHECK (currency >= 0),
  returnUrl TEXT NOT NULL,
  failUrl TEXT NOT NULL,
  status TEXT NOT NULL,
  isDeleted BOOLEAN DEFAULT FALSE
);