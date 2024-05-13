# LibraryManagementSystem
Backend for library management using SpringBoot, Hibernate, JPA. 
Here I am creating a Library management in which  I am creating the following tables.
1 BOOK : storing data for the book including book name,  book no. , cost, type (ENUM) , Author
2 Author:  storing data for author including author name , email, address
3 Student: Student enrolled in the library and his name, contact, email, address
4 Transection:  All the transaction takes place between student and book, Issuing new book to student, Returning book to the student, Calculating the fine Amount for the delay in return.

Follows MVC architecture to make code readable and clear to work.
All the exceptions are handled  and testing is also done.

TECH STACK USE:

1. Spring Boot
2. HIBERNATE
3. JPA queries
4. MY SQL

API'S CREATED :
1. Add Student 
localhost:8080/Student/addStudent :
                                        {
                                            "phoneNo" : "",
                                            "name" : ""
                                            ,"email" : ""
                                            ,"address" : ""
                                        }
2. Create Book
   localhost:8080/Book/create :
                                  {
                                  "name" : "",
                                  "bookNo" : "",
                                  "type" : ""
                                  ,"cost" :""  
                                  ,"authorName" : ""
                                  ,"authorEmail" : ""
                               }
3. Filter Book
   localhost:8080/Book/filter?prefix=AUTHORNAME&operator=EQUAL&value=kethi : in this param are passed as key value pair

4. Create Transaction
   localhost:8080/Trx/create :
                                 {
                                  "studentContact" : "",
                                  "bookNo" : "",
                                  "paidAmnt": ""
                               }
5. Return Transaction
   localhost:8080/Trx/return :
                                     {
                                    "trxId" : "",
                                    "StudentContact" : ""
                                   }


