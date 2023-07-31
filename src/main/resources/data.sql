--Tabla Books
delete from books;
alter table books alter column book_id restart with 1;
insert into books(title, author, year_published, publisher, cost) values
    ('Cien años de soledad', 'Gabriel García Márquez', 1967, 'Editorial Sudamericana', 40000.80),
    ('To Kill a Mockingbird', 'Harper Lee', 1960, 'J. B. Lippincott & Co.', 10000.99),
    ('1984', 'George Orwell', 1949, 'Secker & Warburg', 12000.95),
    ('Harry Potter and the Sorcerers Stone', 'J.K. Rowling', 1997, 'Bloomsbury', 19000.99),
    ('The Great Gatsby', 'F. Scott Fitzgerald', 1925, 'Charles Scribners Sons', 8000.50),
    ('The Lord of the Rings: The Fellowship of the Ring', 'J.R.R. Tolkien', 1954, 'George Allen & Unwin', 15000.00),
    ('Pride and Prejudice', 'Jane Austen', 1813, 'T. Egerton', 9000.95),
    ('The Catcher in the Rye', 'J.D. Salinger', 1951, 'Little, Brown and Company', 11000.25),
    ('To All the Boys Ive Loved Before', 'Jenny Han', 2014, 'Simon & Schuster', 13000.99),
    ('The Hunger Games', 'Suzanne Collins', 2008, 'Scholastic Corporation', 14000.50),
    ('The Da Vinci Code', 'Dan Brown', 2003, 'Doubleday', 16000.95);

-- Tabla Customers

delete from customers;
alter table customers alter column customer_id restart with 1;
insert into customers(document_number, first_name, last_name, email, phone) values
    ('123456789', 'John', 'Doe', 'john.doe@example.com', '1234567890'),
    ('987654321', 'Jane', 'Smith', 'jane.smith@example.com', '9876543210'),
    ('555555555', 'Michael', 'Johnson', 'michael.johnson@example.com', '5555555555'),
    ('111111111', 'Emily', 'Williams', 'emily.williams@example.com', '1111111111'),
    ('444444444', 'James', 'Brown', 'james.brown@example.com', '4444444444'),
    ('999999999', 'Jessica', 'Jones', 'jessica.jones@example.com', '9999999999'),
    ('777777777', 'William', 'Anderson', 'william.anderson@example.com', '7777777777'),
    ('222222222', 'Sarah', 'Lee', 'sarah.lee@example.com', '2222222222'),
    ('888888888', 'David', 'Martin', 'david.martin@example.com', '8888888888'),
    ('666666666', 'Laura', 'Taylor', 'laura.taylor@example.com', '6666666666');

-- Tabla Stock

delete from stock;
alter table stock alter column stock_id restart with 1;
insert into stock(book_id, quantity, location) values
    (1, 3, 'bookshelf A'),
    (2, 5, 'bookshelf B'),
    (3, 7, 'bookshelf A'),
    (4, 8, 'bookshelf C'),
    (5, 2, 'bookshelf A'),
    (6, 4, 'bookshelf B'),
    (7, 5, 'bookshelf A'),
    (8, 6, 'bookshelf C'),
    (9, 7, 'bookshelf B'),
    (10, 8, 'bookshelf C'),
    (11, 9, 'bookshelf B');