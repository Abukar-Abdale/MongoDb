import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.connection.ClusterConnectionMode;
import com.mongodb.connection.ClusterType;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class MongoDBFacade {
    private static final Logger logger = LoggerFactory.getLogger(MongoDBFacade.class);
    private MongoClient mongoClient;
    private MongoCollection<Document> customerCollection;
    private MongoCollection<Document> employeeCollection;

    public MongoDBFacade() {
        logger.info("Creating MongoDBFacade instance...");

        // Fetch connection string from KeyReader
        String connectionString = KeyReader.getConnectionString();

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        try {
            mongoClient = MongoClients.create(settings);
            MongoDatabase database = mongoClient.getDatabase("myDatabase");

            // check if the database already exists
            if (mongoClient.listDatabaseNames().into(new ArrayList<>()).contains("myDatabase")) {
                logger.info("Database already exists");
            } else {
                // create the database and the required collections
                database.createCollection("Customer");
                database.createCollection("Employee");
                logger.info("Database created");
            }

            // check that the MongoClient instance is connected to the MongoDB server
            if (mongoClient.getClusterDescription().getType() == ClusterType.STANDALONE
                    && mongoClient.getClusterDescription().getClusterSettings().getMode() == ClusterConnectionMode.SINGLE) {
                throw new RuntimeException("MongoClient instance is not connected to the MongoDB server.");
            }

            customerCollection = database.getCollection("Customer");
            employeeCollection = database.getCollection("Employee");
        } catch (MongoException e) {
            // handle exception
            logger.error("Error creating MongoDBFacade instance", e);
        }
    }


    public void createPerson(Person person) {
        logger.info("Creating person: {}", person);
        try {
            if (person instanceof Customer) {
                customerCollection.insertOne(new Document("_id", person)
                        .append("name", person.getName())
                        .append("age", person.getAge())
                        .append("address", person.getAddress())
                        .append("customer_number", ((Customer) person).getCustomerNumber()));
            } else if (person instanceof Employee) {
                employeeCollection.insertOne(new Document("_id", person)
                        .append("name", person.getName())
                        .append("age", person.getAge())
                        .append("address", person.getAddress())
                        .append("employee_number", ((Employee) person).getEmployeeNumber()));
            }
        } catch (Exception e) {
            logger.error("Error creating person: {}", e.getMessage());
        }
    }



    public Person getPersonById() {
        logger.info("Getting person by ID...");
        try  {
            Document customerDoc = customerCollection.find(Filters.eq("_id", 1)).first();
            if (customerDoc != null) {
                return new Customer(customerDoc.getString("name"), customerDoc.getInteger("age"),
                        customerDoc.getString("address"), customerDoc.getInteger("customer_number"));
            }
            Document employeeDoc = employeeCollection.find(Filters.eq("_id", 100)).first();
            if (employeeDoc != null) {
                return new Employee(employeeDoc.getString("name"), employeeDoc.getInteger("age"),
                        employeeDoc.getString("address"), employeeDoc.getInteger("employee_number"));
            }
            return null;
        } catch (Exception e) {
            logger.error("Error getting person by ID: {}", e.getMessage());
            return null;
        }
    }

    public void updatePerson(Person person) {
        logger.info("Updating person: {}", person);
        try  {
            if (person instanceof Customer) {
                customerCollection.updateOne(Filters.eq("_id", 1),
                        new Document("$set", new Document("name", person.getName())
                                .append("age", person.getAge())
                                .append("address", person.getAddress())
                                .append("customer_number", ((Customer) person).getCustomerNumber())));
            } else if (person instanceof Employee) {
                employeeCollection.updateOne(Filters.eq("_id", 100),
                        new Document("$set", new Document("name", person.getName())
                                .append("age", person.getAge())
                                .append("address", person.getAddress())
                                .append("employee_number", ((Employee) person).getEmployeeNumber())));
            }
        } catch (Exception e) {
            logger.error("Error updating person: {}", e.getMessage());
        }
    }

    public void deletePerson() {
        logger.info("Deleting person...");
        try  {
            customerCollection.deleteOne(Filters.eq("_id", 1));
            employeeCollection.deleteOne(Filters.eq("_id", 100));
        } catch (Exception e) {
            logger.error("Error deleting person: {}", e.getMessage());
        }

    }

    public List<Person> getAllPersons() {
        logger.info("Getting all persons...");
        try  {
            List<Person> allPersons = new ArrayList<>();
            for (Document customerDoc : customerCollection.find()) {
                allPersons.add(new Customer(customerDoc.getString("name"), customerDoc.getInteger("age"),
                        customerDoc.getString("address"), customerDoc.getInteger("customer_number")));
            }
            for (Document employeeDoc : employeeCollection.find()) {
                allPersons.add(new Employee(employeeDoc.getString("name"), employeeDoc.getInteger("age"),
                        employeeDoc.getString("address"), employeeDoc.getInteger("employee_number")));
            }
            return allPersons;
        } catch (Exception e) {
            logger.error("Error getting all persons: {}", e.getMessage());
            return null;
        }

        public void close() {
        mongoClient.close();
    }

    }


}
