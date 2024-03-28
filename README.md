Database Dara Processing
Topic: Implementation of Data Access Architectural Patterns.

Objective: Familiarize oneself with the architectural patterns of data access: Data Mapper, Data Row Gateway, Active Record, Data Access Object (DAO), or Repository.

Brief Theoretical Information:

All necessary information is provided in Lecture 15.
Link to an implementation of the pattern.

Task:

Rewrite the previous practical using one of the data access patterns:

Data Mapper
Data Row Gateway
Active Record
DAO (equivalent to Data Mapper but with a different name in the Java world)
Repository
Attempt to make entity classes Immutable (the lecturer is not sure if it's a good practice and couldn't verify).

Optionally, use the Object Pool pattern for connections (Connection) or utilize an existing library.

For entity classes, use the Builder pattern (any implementation, but the best one is demonstrated in the GitHub repository through internal interfaces).

If it's DAO/Repository (Data Mapper):

Use the Singleton pattern for classes implementing CRUD.
Utilize Simple Factory or Factory Method. Creating objects of classes with CRUD (e.g., PostDao) should only be done through the factory!
If a record is not found by PK, throw YOUR error. Handle potential errors similarly. Always inherit from RuntimeException.
Complex mapping (optional, as it's complicated) — if there's a One-to-Many relationship, for instance, each Post has a collection of Comments, then it should be a collection of objects. And in Comment — one object of Post.
Many-to-Many — the third auxiliary table (e.g., post_tag) is not an entity!!! How to retrieve data? Each class (e.g., PostDao) should implement CRUD methods using the auxiliary table.
