# JumioBookStoreApp
API backend operations for Jumio bookstore application

Functionalities
Customer can  retrieve the full list of books 
Customer can  search for a specific book based on the ISBN
Customer can create, update and send  an order.

PACKAGES

Contollers
1.BookControler to retrieve all books & CRUD operations for Book & search book function
2.CustomerController to retrieve all orders, create/update/send order

Services
1.Book-> BookService, IBookService
2.Customer-> CustomerService, ICustomerService
3.Order-> OrderService, IOrderService
4.Stock-> StockService, IStock Service

Data transfer objects -- for receiving object from controller & sending object to controller
1.BookDTO
2.CustomerDTO
3.OrderDTO
4.StockDTO
5.AuthorDTO
6.OrderItemDTO


Domain objects -- for db level operations
1.BookDO
2.CustomerDO
3.OrderDO
4.StockDO
5.AuthorDO
6.OrderItemDO

Util/Mappers -- for converting DTO obejct to DO objects or vice versa
1.AuthorMapper
2.BookMapper
3.CustomerMapper
4.OrderItemMapper
5.OrderMapper
6.StockMapper

Util/StaticValues -> using column names

Exceptions
1.ConstraintsViolationException - throws when posting data which is violated entity properties
2.DontHaveRightException - throws when customer and order is not belongs to each other but try to do with those unmatched id's of customerId&OrderID
3.EntityNotFoundException - throws when there is no entity with received Id.
4.InsufficientStorageException - throws when there is no sufficient storage book unit for OrderItem-Quantity

Security
1.SpringSecurityConfig - to configure credentials roles & permission criterias
2.AuthenticationEntryPoint- to configure authenticationEntryPoint in SpringSecurityConfig
