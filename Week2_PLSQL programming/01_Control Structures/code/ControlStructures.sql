
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Loans';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE Customers';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/


CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Age NUMBER,
    Balance NUMBER,
    IsVIP CHAR(1),
    LoanInterestRate NUMBER
);


CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    DueDate DATE
);


INSERT INTO Customers VALUES (1, 'Madhesh', 65, 12000, 'N', 10);
INSERT INTO Customers VALUES (2, 'Srinu', 45, 8000, 'N', 9);
INSERT INTO Customers VALUES (3, 'John', 70, 20000, 'N', 11);

INSERT INTO Loans VALUES (201, 1, SYSDATE + 10);
INSERT INTO Loans VALUES (202, 2, SYSDATE + 5);
INSERT INTO Loans VALUES (203, 3, SYSDATE + 40);

COMMIT;


BEGIN
    FOR cust IN (SELECT CustomerID FROM Customers WHERE Age > 60) LOOP
        UPDATE Customers
        SET LoanInterestRate = LoanInterestRate - 1
        WHERE CustomerID = cust.CustomerID;
    END LOOP;
    COMMIT;
END;
/


BEGIN
    FOR cust IN (SELECT CustomerID FROM Customers WHERE Balance > 10000) LOOP
        UPDATE Customers
        SET IsVIP = 'Y'
        WHERE CustomerID = cust.CustomerID;
    END LOOP;
    COMMIT;
END;
/


SET SERVEROUTPUT ON;
BEGIN
    FOR cust IN (SELECT Name FROM Customers WHERE IsVIP = 'Y') LOOP
        DBMS_OUTPUT.PUT_LINE(cust.Name || ' is promoted to VIP.');
    END LOOP;
END;
/


DECLARE
    CURSOR loan_cursor IS
        SELECT l.LoanID, c.Name, l.DueDate
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.DueDate <= SYSDATE + 30;
BEGIN
    FOR rec IN loan_cursor LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan ' || rec.LoanID || ' for ' || rec.Name ||
                             ' is due on ' || TO_CHAR(rec.DueDate, 'DD-Mon-YYYY'));
    END LOOP;
END;
/

