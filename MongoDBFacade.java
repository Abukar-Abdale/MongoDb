import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBFacade {
    private static final String DATABASE_NAME = "yourDatabaseName";
    private static final String COLLECTION_NAME_CUSTOMER = "customer";
    private static final String COLLECTION_NAME_EMPLOYEE = "employee";
    private String connectionString;
    private MongoDatabase database;

    public MongoDBFacade(KeyReader keyReader) {
        connectionString = keyReader.getConnectionString();
        MongoClient mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(DATABASE_NAME);
    }

}


    public void createCustomer(Customer customer) {
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_CUSTOMER);
        Document document = new Document("name", customer.getName())
                .append("age", customer.getAge())
                .append("address", customer.getAddress())
                .append("customerNumber", customer.getCustomerNumber());
        collection.insertOne(document);
    }

    public void createEmployee(Employee employee) {
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_EMPLOYEE);
        Document document = new Document("name", employee.getName())
                .append("age", employee.getAge())
                .append("address", employee.getAddress())
                .append("employeeNumber", employee.getEmployeeNumber());
        collection.insertOne(document);
    }

    public Customer readCustomer(int customerNumber) {
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_CUSTOMER);
        Document query = new Document("customerNumber", customerNumber);
        Document document = collection.find(query).first();
        if (document == null) {
            return null;
        }
        return new Customer(document.getString("name"), document.getInteger("age"), document.getString("address"), document.getInteger("customerNumber"));
    }

    public Employee readEmployee(int employeeNumber) {
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_EMPLOYEE);
        Document query = new Document("employeeNumber", employeeNumber);
        Document document = collection.find(query).first();
        if (document == null) {
            return null;
        }
        return new Employee(document.getString("name"), document.getInteger("age"), document.getString("address"), document.getInteger("employeeNumber"));
    }

    public void updateCustomer(Customer customer) {
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_CUSTOMER);
        Document query = new Document("customerNumber", customer.getCustomerNumber());
        Document update = new Document("$set", new Document("name", customer.getName())
                .append("age", customer.getAge())
                .append("address", customer.getAddress()));
        collection.updateOne(query, update);
    }

    public void updateEmployee(Employee employee) {
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_EMPLOYEE);
        Document query = new Document("employeeNumber", employee.getEmployeeNumber());
        Document update = new Document("$set", new Document("name", employee.getName())
                .append("age", employee.getAge())
                .append("address", employee.getAddress()));
        collection.updateOne(query, update);
    }

    public void deleteCustomer(int customerNumber) {
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_CUSTOMER);
        Document query = new Document("customerNumber", customerNumber);
        collection.deleteOne(query);
    }

    public void deleteEmployee(int employeeNumber) {
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_EMPLOYEE);
        Document query = new Document("employeeNumber", employeeNumber);
        collection.deleteOne(query);
    }
}
