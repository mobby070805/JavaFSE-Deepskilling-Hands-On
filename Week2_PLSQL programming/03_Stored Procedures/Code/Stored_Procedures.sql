
BEGIN EXECUTE IMMEDIATE 'DROP TABLE Accounts'; EXCEPTION WHEN OTHERS THEN NULL; END;
/
BEGIN EXECUTE IMMEDIATE 'DROP TABLE Employees'; EXCEPTION WHEN OTHERS THEN NULL; END;
/
BEGIN EXECUTE IMMEDIATE 'DROP TABLE Transactions'; EXCEPTION WHEN OTHERS THEN NULL; END;
/

--  Accounts table
CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerName VARCHAR2(100),
    AccountType VARCHAR2(20),
    Balance NUMBER
);

--  Employees table
CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Department VARCHAR2(50),
    Salary NUMBER
);


CREATE TABLE Transactions (
    TransID NUMBER GENERATED ALWAYS AS IDENTITY,
    FromAccount NUMBER,
    ToAccount NUMBER,
    Amount NUMBER,
    TransDate DATE DEFAULT SYSDATE
);


INSERT INTO Accounts VALUES (101, 'Madhesh', 'Savings', 20000);
INSERT INTO Accounts VALUES (102, 'Srinu', 'Current', 15000);
INSERT INTO Accounts VALUES (103, 'John', 'Savings', 30000);


INSERT INTO Employees VALUES (1, 'David', 'IT', 50000);
INSERT INTO Employees VALUES (2, 'Priya', 'HR', 45000);
INSERT INTO Employees VALUES (3, 'Kumar', 'IT', 52000);

COMMIT;

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    FOR acc IN (SELECT AccountID FROM Accounts WHERE AccountType = 'Savings') LOOP
        UPDATE Accounts
        SET Balance = Balance + (Balance * 0.01)
        WHERE AccountID = acc.AccountID;
    END LOOP;
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department IN VARCHAR2,
    p_bonus_percent IN NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_percent / 100)
    WHERE Department = p_department;
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account IN NUMBER,
    p_to_account IN NUMBER,
    p_amount IN NUMBER
) AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from_account;

    IF v_balance >= p_amount THEN
        UPDATE Accounts
        SET Balance = Balance - p_amount
        WHERE AccountID = p_from_account;

        UPDATE Accounts
        SET Balance = Balance + p_amount
        WHERE AccountID = p_to_account;

        INSERT INTO Transactions (FromAccount, ToAccount, Amount)
        VALUES (p_from_account, p_to_account, p_amount);

        COMMIT;
    ELSE
        DBMS_OUTPUT.PUT_LINE('Insufficient balance for transfer.');
    END IF;
END;
/


EXEC ProcessMonthlyInterest;

EXEC UpdateEmployeeBonus('IT', 10);

EXEC TransferFunds(101, 102, 3000);

SELECT * FROM Accounts;
SELECT * FROM Employees;
SELECT * FROM Transactions;

