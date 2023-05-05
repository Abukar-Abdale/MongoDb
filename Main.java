import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            KeyReader keyReader = new KeyReader();
            String connectionString = keyReader.getConnectionString();

            if (connectionString == null || connectionString.isEmpty()) {
                logger.warn("Connection string not found in key file. Please enter the connection string:");
                Scanner scanner = new Scanner(System.in);
                connectionString = scanner.nextLine();
            }

            var mongoDBFacade = new MongoDBFacade(connectionString, "Person");

            PersonFacade personFacade = new PersonFacade();
            Employee employee = personFacade.setName("John").setAge(30).setAddress("123 Main St").createEmployee(1);
            Customer customer = personFacade.setName("Jane").setAge(40).setAddress("456 Elm St").createCustomer(2);

            mongoDBFacade.addEmployee(employee);
            mongoDBFacade.addCustomer(customer);

            List<Employee> employees = mongoDBFacade.getEmployees();
            logger.info("Employees:");
            for (Employee e : employees) {
                logger.info(e.toString());
            }

            List<Customer> customers = mongoDBFacade.getCustomers();
            logger.info("Customers:");
            for (Customer c : customers) {
                logger.info(c.toString());
            }

            mongoDBFacade.close();
        } catch (IOException e) {
            logger.error("Error reading file: {}", e.getMessage());
        }
    }
}
