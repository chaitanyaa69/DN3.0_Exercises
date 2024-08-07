use ctsdnplsql;

DELIMITER //

CREATE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    SET NEW.LastModified = NOW();
END //

DELIMITER ;


CREATE TABLE AuditLog (
    AuditID INT AUTO_INCREMENT PRIMARY KEY,
    TransactionID INT,
    AccountID INT,
    TransactionDate DATETIME,
    Amount DECIMAL(10, 2),
    TransactionType VARCHAR(10),
    ChangeDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DELIMITER //

CREATE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (NEW.TransactionID, NEW.AccountID, NEW.TransactionDate, NEW.Amount, NEW.TransactionType);
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
BEGIN
    DECLARE accountBalance DECIMAL(10, 2);

    -- Check for withdrawals exceeding balance
    IF NEW.TransactionType = 'Withdrawal' THEN
        -- Get the current balance of the account
        SELECT Balance INTO accountBalance 
        FROM Accounts 
        WHERE AccountID = NEW.AccountID;
        
        -- If there is insufficient balance, raise an error
        IF accountBalance < NEW.Amount THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient funds for withdrawal';
        END IF;
    END IF;

    -- Check for deposits with a non-positive amount
    IF NEW.TransactionType = 'Deposit' AND NEW.Amount <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Deposit amount must be positive';
    END IF;
END //

DELIMITER ;

SHOW TRIGGERS;

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (1, 1, NOW(), 1500, 'Withdrawal'); -- an error due to insufficient funds

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (2, 1, NOW(), -100, 'Deposit'); -- an error due to negative deposit amount


DESCRIBE Transactions;



INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (3, 1, '2024-08-07 17:03:18', 500, 'Deposit');



