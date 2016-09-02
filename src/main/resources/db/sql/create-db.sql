--DROP TABLE Employee IF EXISTS;

create table Employee(
      ID        INTEGER PRIMARY KEY,
      First_Name         VARCHAR(20),
      Last_Name          VARCHAR(20),
      Start_Date         DATE,
      End_Date           DATE,
      Salary             DOUBLE,
      City               VARCHAR(20),
      Description        VARCHAR(80)
);