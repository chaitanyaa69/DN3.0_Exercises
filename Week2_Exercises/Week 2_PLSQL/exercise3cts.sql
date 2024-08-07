use ctsdnplsql;

DELIMITER //

CREATE PROCEDURE ProcessMonthlyInterest()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE accountID INT;
    DECLARE balance DECIMAL(10, 2);
    DECLARE interestRate DECIMAL(5, 2) DEFAULT 0.01;
    DECLARE cursor1 CURSOR FOR SELECT AccountID, Balance FROM Accounts WHERE AccountType = 'Savings';
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cursor1;

    read_loop: LOOP
        FETCH cursor1 INTO accountID, balance;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- Update the balance with interest
        UPDATE Accounts
        SET Balance = Balance + (Balance * interestRate)
        WHERE AccountID = accountID;
    END LOOP;

    CLOSE cursor1;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE UpdateEmployeeBonus(IN departmentName VARCHAR(50), IN bonusPercentage DECIMAL(5, 2))
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Log the error message
        INSERT INTO ErrorLog (ErrorMessage, ErrorDate)
        VALUES (CONCAT('Error updating bonus for department ', departmentName), NOW());
    END;

    -- Update the salary with the bonus
    UPDATE Employees
    SET Salary = Salary + (Salary * (bonusPercentage / 100))
    WHERE Department = departmentName;
END //

DELIMITER ;


DELIMITER //

CREATE PROCEDURE TransferFunds(IN fromAccountID INT, IN toAccountID INT, IN transferAmount DECIMAL(10, 2))
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

CALL ProcessMonthlyInterest();
CALL UpdateEmployeeBonus('IT', 5);
CALL TransferFunds(1, 2, 200);

select * from Employees;





