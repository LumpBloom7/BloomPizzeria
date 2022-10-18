# BloomPizzeria

The interface to the world'd renowned BloomPizzeria.

## How to run

All terminal commands are run from the root directory of this project.

### Setup the database locally

Before running the program, it is recommended that the database and its contents are initialized first

In MYSQL:

```sql
SOURCE InitializeDatabase.sql
SOURCE SampleMenu.sql
SOURCE SampleData.sql
```

### Setting the correct SQL credentials in the program

In order for the program to successfully connect to your local database, it needs to know some access credentials.

In the file `src\main\java\Database\Database.java - Line 15`, you will find a line of code that looks like the following

```java
    conn = DriverManager.getConnection("jdbc:mysql://localhost/BloomPizzeria", "root", "root");
```

It should be adjusted to look like the following

```java
    conn = DriverManager.getConnection("jdbc:mysql://localhost/BloomPizzeria", "<YOUR USER HERE>", "<YOUR PASSWORD HERE>");
```

### Running the program

You can run the program using this command

```sh
./gradlew run
```

You can also run it via the main method located within `LoginScreen.java`.
