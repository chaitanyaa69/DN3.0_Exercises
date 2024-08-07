use ctsdnplsql;
ALTER TABLE Customers ADD IsVIP BOOLEAN DEFAULT FALSE;

DELIMITER //

CREATE PROCEDURE ApplyDiscountToSeniorCustomers()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE customerID INT;
    DECLARE age INT;
    DECLARE cursor1 CURSOR FOR SELECT CustomerID, YEAR(CURDATE()) - YEAR(DOB) AS age FROM Customers;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cursor1;

    read_loop: LOOP
        FETCH cursor1 INTO customerID, age;
        IF done THEN
            LEAVE read_loop;
        END IF;

        IF age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = customerID;
        END IF;
    END LOOP;

    CLOSE cursor1;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE PromoteVIPCustomers()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE customerID INT;
    DECLARE balance DECIMAL(10, 2);
    DECLARE cursor1 CURSOR FOR SELECT CustomerID, Balance FROM Customers;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cursor1;

    read_loop: LOOP
        FETCH cursor1 INTO customerID, balance;
        IF done THEN
            LEAVE read_loop;
        END IF;

        IF balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = TRUE
            WHERE CustomerID = customerID;
        END IF;
    END LOOP;

    CLOSE cursor1;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE SendLoanDueReminders()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE loanID INT;
    DECLARE customerID INT;
    DECLARE customerName VARCHAR(100);
    DECLARE endDate DATE;
    DECLARE cursor1 CURSOR FOR SELECT LoanID, CustomerID, EndDate FROM Loans WHERE EndDate BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 30 DAY);
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cursor1;

    read_loop: LOOP
        FETCH cursor1 INTO loanID, customerID, endDate;
        IF done THEN
            LEAVE read_loop;
        END IF;

        SELECT Name INTO customerName FROM Customers WHERE CustomerID = customerID;

        -- This is where the reminder message is printed
        SELECT CONCAT('Reminder: Customer ', customerName, ' has a loan due on ', endDate) AS ReminderMessage;
    END LOOP;

    CLOSE cursor1;
END //

DELIMITER ;


CALL ApplyDiscountToSeniorCustomers();
CALL PromoteVIPCustomers();
CALL SendLoanDueReminders();

select * from Customers;

