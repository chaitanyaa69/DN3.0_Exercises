use ctsdnplsql;

DELIMITER //

CREATE PROCEDURE SafeTransferFunds(IN fromAccountID INT, IN toAccountID INT, IN transferAmount DECIMAL(10, 2))
BEGIN
    DECLARE insufficientFundsCondition CONDITION FOR SQLSTATE '45000';
    DECLARE EXIT HANDLER FOR insufficientFundsCondition
    BEGIN
        -- Log the error message
        INSERT INTO ErrorLog (ErrorMessage, ErrorDate)
        VALUES (CONCAT('Insufficient funds for transfer from account ', fromAccountID), NOW());
        ROLLBACK;
    END;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Log the general error message
        INSERT INTO ErrorLog (ErrorMessage, ErrorDate)
        VALUES (CONCAT('Error during transfer from account ', fromAccountID, ' to account ', toAccountID), NOW());
        ROLLBACK;
    END;

    START TRANSACTION;

    -- Check if there are sufficient funds
    IF (SELECT Balance FROM Accounts WHERE AccountID = fromAccountID) < transferAmount THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient funds';
    END IF;

    -- Deduct from the source account
    UPDATE Accounts SET Balance = Balance - transferAmount WHERE AccountID = fromAccountID;

    -- Add to the destination account
    UPDATE Accounts SET Balance = Balance + transferAmount WHERE AccountID = toAccountID;

    COMMIT;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE UpdateSalary(IN employeeID INT, IN percentageIncrease DECIMAL(5, 2))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Log the error message
        INSERT INTO ErrorLog (ErrorMessage, ErrorDate)
        VALUES (CONCAT('Error updating salary for employee ', employeeID), NOW());
    END;

    -- Update the salary
    UPDATE Employees
    SET Salary = Salary + (Salary * (percentageIncrease / 100))
    WHERE EmployeeID = employeeID;

    IF ROW_COUNT() = 0 THEN
        -- Employee ID does not exist, handle the exception
        INSERT INTO ErrorLog (ErrorMessage, ErrorDate)
        VALUES (CONCAT('Employee ID ', employeeID, ' does not exist'), NOW());
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE AddNewCustomer(IN customerID INT, IN customerName VARCHAR(100), IN customerDOB DATE, IN initialBalance DECIMAL(10, 2))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Log the error message
        INSERT INTO ErrorLog (ErrorMessage, ErrorDate)
        VALUES (CONCAT('Error adding new customer with ID ', customerID), NOW());
    END;

    -- Insert the new customer
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
    VALUES (customerID, customerName, customerDOB, initialBalance, NOW(), FALSE);

    IF ROW_COUNT() = 0 THEN
        -- Customer ID already exists, handle the exception
        INSERT INTO ErrorLog (ErrorMessage, ErrorDate)
        VALUES (CONCAT('Customer ID ', customerID, ' already exists'), NOW());
    END IF;
END //

DELIMITER ;


CALL SafeTransferFunds(1, 2, 500);
CALL UpdateSalary(1, 10);
CALL AddNewCustomer(3, 'Michael Scott', '1964-03-15', 2000);
CREATE TABLE ErrorLog (
    ErrorID INT PRIMARY KEY AUTO_INCREMENT,
    ErrorMessage VARCHAR(255),
    ErrorDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



select * from ErrorLog;
select * from Customers;

