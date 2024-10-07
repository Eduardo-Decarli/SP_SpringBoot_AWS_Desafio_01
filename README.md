# Library Manager

This system manages a library, where the system manager can register other managers, register books, loans, and returns of loans. The system was developed to be entirely operated through the terminal, using Java technology and integrated with a database.

## Data Flow

1. The system asks the user whether they want to log in as a Member (using email as the access method) or register a new Member.

2. The system presents a menu where the user can choose between viewing book options, members, loans, and authors.

- Books
    - Register a new book
        - To register a book, the system will ask for an author. The user can associate an existing author or create a new one.
    - List books in the database

- Authors
    - List authors in the system
    - Register a new author

- Members
    - List members in the system
    - Register a new member in the system

- Loans
    - Register a loan
        - The system asks if the user wants to list books by author for the loan or not
        - The system requests the ISBN of a book to register for the loan
    - Register a return
        - The system asks whether to return by active or late loans

- report
    - financial report
        - The system generates a report of the sum in bigDecimal of all loan rates with complete status
    - members with late loans
        - The system generates a report of all members with late loans

## Technologies Used

- VSCode
- IntelliJ
- Java 21
- Git
- GitHub
- MySQL
- DrawIo

## Key Features

- The system uses **Lambda** and **Stream** to sort books alphabetically by title.

- The late fee is calculated as **BigDecimal** and is set to a scale of 3 digits after the decimal point.

- Use of **HashSet** to list Members with overdue loans in the Member report.

- Use of a generic interface that overrides in **LoanServices** and **MemberServices** classes for report generation.

## Database Features

- AuthorDAO Class
    - Search author by ID.
    - Search author by name.
    - Search all authors.
    - Insert a new author.

- BookDAO Class
    - Insert a new Book
    - Select a book by ISBN.
    - Select books by Author
    - Select all books in database
    - Update quantity of book (minus)
    - Update quantity of book (plus)
    - Select a book by id

- MemberDAO Class
    - Insert a member into the database
    - Search a member by email
    - Select Member by id
    - Select all members in database

- LoanDAO Class
    - Insert a new loan in database.
    - Select a loan by status(ACTIVE, LATE or COMPLETE).
    - Select all loans in database.
    - Select Loan by id.
    - Update loan to Complete.

## Preventive Logic

- Does not allow registering two Members with the same email.

- Does not allow submitting an empty email to the database.

- A book's ISBN must be 13 digits long.

- Does not allow registering two authors with the same name.

- Does not allow lending a book that is not in stock.

- Does not allow registering loans with incorrect past or future dates.

- Does not allow assigning a status to a loan that has already been completed.

## How to Use the Application

1. Clone the repository or manually download the application from GitHub.

2. Open it with IntelliJ or another IDE of your choice.

3. Configure the database connection in the **db.properties** file.

4. Open SQL Workbench and execute the database creation command, located in utilities > LibraryDB.

5. Run the code from the IDE.