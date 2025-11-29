Create Database Lab5

USE Lab5

create table Users(
    Id int primary key identity (1,1),
    Fullname nvarchar(255) not null unique ,
    Email nvarchar(255) not null  unique ,
    Password nvarchar(50),
)

create table Visitors(
    Id int primary key,
    VisitCount int not null
)

INSERT INTO Users (Fullname, Email, Password) VALUES
(N'Nguyễn Văn A', N'nguyenvana@example.com', N'Password@123'),
(N'Trần Thị B', N'tranthib@example.com', N'Abc12345'),
(N'Lê Hoàng C', N'lehoangc@example.com', N'MatKhau!999')

insert into Visitors(Id, Visitcount) values
(1,0)


select * from Users
select * from Visitors