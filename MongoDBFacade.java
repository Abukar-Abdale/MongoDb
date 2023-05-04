
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBFacade {
    private static final String DATABASE_NAME = "yourDatabaseName";
    private static final String COLLECTION_NAME_CUSTOMER = "customer";
    private static final String COLLECTION_NAME_EMPLOYEE = "employee";
    private final String connectionString;

    public MongoDBFacade(KeyReader keyReader) {
        connectionString = keyReader.getConnectionString();
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            this.createCollectionsIfNotExist(database);
        }
    }

    private void createCollectionsIfNotExist(MongoDatabase database) {
        boolean customerExists = false;
        boolean employeeExists = false;
        for (String collectionName : database.listCollectionNames()) {
            if (collectionName.equals(COLLECTION_NAME_CUSTOMER)) {
                customerExists = true;
            }
            if (collectionName.equals(COLLECTION_NAME_EMPLOYEE)) {
                employeeExists = true;
            }
        }
        if (!customerExists) {
            database.createCollection(COLLECTION_NAME_CUSTOMER);
        }
        if (!employeeExists) {
            database.createCollection(COLLECTION_NAME_EMPLOYEE);
        }
    }

    public void createCustomer(Customer customer) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_CUSTOMER);
            Document document = new Document("name", customer.getName())
                    .append("age", customer.getAge())
                    .append("address", customer.getAddress())
                    .append("customerNumber", customer.getCustomerNumber());
            collection.insertOne(document);
        } catch (Exception e) {
            System.out.println("Error creating customer: " + e.getMessage());
        }
    }



    public void createEmployee(Employee employee) {
        try {
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_EMPLOYEE);
            Document document = new Document("name", employee.getName())
                    .append("age", employee.getAge())
                    .append("address", employee.getAddress())
                    .append("employeeNumber", employee.getEmployeeNumber());
            collection.insertOne(document);
        } catch (Exception e) {
            System.out.println("Error creating employee: " + e.getMessage());
        }

    }

    public Customer readCustomer(int customerNumber) {
        try {
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_CUSTOMER);
            Document query = new Document("customerNumber", customerNumber);
            Document result = collection.find(query).first();
            if (result == null) {
                return null;
            }
            String name = result.getString("name");
            int age = result.getInteger("age");
            String address = result.getString("address");
            return new Customer(name, age, address, customerNumber);
        } catch (Exception e) {
            System.out.println("Error reading customer: " + e.getMessage());
            return null;
        }

    }

    public Employee readEmployee(int employeeNumber) {
    try {
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_EMPLOYEE);
        Document query = new Document("employeeNumber", employeeNumber);
        Document result = collection.find(query).first();
        if (result == null) {
            return null;
        }
        String name = result.getString("name");
        int age = result.getInteger("age");
        String address = result.getString("address");
        return new Employee(name, age, address, employeeNumber);
    } catch (Exception e) {
        System.out.println("Error reading employee: " + e.getMessage());
        return null;

    }
}

    public void updateCustomer(Customer customer) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_CUSTOMER);
            Document query = new Document("customerNumber", customer.getCustomerNumber());
            Document update = new Document("$set", new Document("name", customer.getName())
                    .append("age", customer.getAge())
                    .append("address", customer.getAddress()));
            collection.updateOne(query, update);
        } catch (Exception e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }

    public void updateEmployee(Employee employee) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_EMPLOYEE);
            Document query = new Document("employeeNumber", employee.getEmployeeNumber());
            Document update = new Document("$set", new Document("name", employee.getName())
                    .append("age", employee.getAge())
                    .append("address", employee.getAddress()));
            collection.updateOne(query, update);
        } catch (Exception e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }


    public void deleteCustomer(int customerNumber) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_CUSTOMER);
            Document query = new Document("customerNumber", customerNumber);
            collection.deleteOne(query);
        } catch (Exception e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }


    public void deleteEmployee(int employeeNumber) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_EMPLOYEE);
            Document query = new Document("employeeNumber", employeeNumber);
            collection.deleteOne(query);
        } catch (Exception e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }

    public void close() {
        try {
            MongoClient mongoClient = MongoClients.create(connectionString);
            mongoClient.close();
        } catch (Exception e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

}
