use ctsdnplsql;

DELIMITER //

CREATE FUNCTION CalculateAge(dob DATE) RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE age INT;
    SET age = YEAR(CURDATE()) - YEAR(dob);
    IF MONTH(CURDATE()) < MONTH(dob) OR (MONTH(CURDATE()) = MONTH(dob) AND DAY(CURDATE()) < DAY(dob)) THEN
        SET age = age - 1;
    END IF;
    RETURN age;
END //

DELIMITER ;


DELIMITER //

CREATE FUNCTION CalculateMonthlyInstallment(loanAmount DECIMAL(10, 2), annualInterestRate DECIMAL(5, 2), loanDurationYears INT) RETURNS DECIMAL(10, 2)
DETERMINISTIC
BEGIN
    DECLARE monthlyInterestRate DECIMAL(5, 4);
    DECLARE numberOfPayments INT;
    DECLARE monthlyInstallment DECIMAL(10, 2);

    SET monthlyInterestRate = annualInterestRate / 12 / 100;
    SET numberOfPayments = loanDurationYears * 12;

    IF monthlyInterestRate > 0 THEN
        SET monthlyInstallment = loanAmount * monthlyInterestRate / (1 - POW(1 + monthlyInterestRate, -numberOfPayments));
    ELSE
        SET monthlyInstallment = loanAmount / numberOfPayments;
    END IF;

    RETURN monthlyInstallment;
END //

DELIMITER ;


DELIMITER //

CREATE FUNCTION hasSufficientBal(accountID INT, amount DECIMAL(10, 2)) RETURNS BOOLEAN
READS SQL DATA
BEGIN
    DECLARE balance DECIMAL(10, 2);

    -- Ensure only one row is selected by using LIMIT 1
    SELECT Balance INTO balance 
    FROM Accounts 
    WHERE AccountID = accountID
    LIMIT 1;

    IF balance IS NULL THEN
        -- Handle the case where no account is found
        RETURN FALSE;
    ELSEIF balance >= amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
END //

DELIMITER ;

SELECT CalculateAge('1985-05-15');
SELECT CalculateMonthlyInstallment(10000, 5, 3);
SELECT hasSufficientBal(1, 500);




