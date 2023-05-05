import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;


public class MongoDBFacade {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> employeeCollection;
    private MongoCollection<Document> customerCollection;

    public MongoDBFacade(String connectionString, String databaseName) {
        if (connectionString == null || connectionString.isEmpty()) {
            throw new IllegalArgumentException("connectionString cannot be null or empty");
        }
        mongoClient = MongoClients.create(connectionString);
        try {
            database = mongoClient.getDatabase(databaseName);
            employeeCollection = database.getCollection("employee");
        } catch (MongoException e) {
            System.err.println("Error while connecting to MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addEmployee(Employee employee) {
        customerCollection = database.getCollection("employee");

        Document document = new Document("name", employee.getName())
                .append("age", employee.getAge())
                .append("address", employee.getAddress())
                .append("employeeNumber", employee.getEmployeeNumber());
        try {
            employeeCollection.insertOne(document);
        } catch (MongoException e) {
            System.err.println("Error while adding employee to MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addCustomer(Customer customer) {
        customerCollection = database.getCollection("customer");

        customerCollection = database.getCollection("customer");

        Document document = new Document("name", customer.getName())
                .append("age", customer.getAge())
                .append("address", customer.getAddress())
                .append("customerNumber", customer.getCustomerNumber());
        try {
            customerCollection.insertOne(document);
        } catch (MongoException e) {
            System.err.println("Error while adding customer to MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            employeeCollection.find().forEach(document -> {
                Employee employee = new Employee(
                        document.getString("name"),
                        document.getInteger("age"),
                        document.getString("address"),
                        document.getInteger("employeeNumber")
                );
                employees.add(employee);
            });
        } catch (MongoException e) {
            System.err.println("Error while getting employees from MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
        return employees;
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            customerCollection.find().forEach(document -> {
                Customer customer = new Customer(
                        document.getString("name"),
                        document.getInteger("age"),
                        document.getString("address"),
                        document.getInteger("customerNumber")
                );
                customers.add(customer);
            });
        } catch (MongoException e) {
            System.err.println("Error while getting customers from MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
        return customers;
    }

    public void close() {
        if (mongoClient != null) {
            try {
                mongoClient.close();
            } catch (MongoException e) {
                System.err.println("Error while closing MongoDB client: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
