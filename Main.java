import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            MongoDBFacade facade = new MongoDBFacade();

            // Create new customer and employee objects
            Customer customer = new Customer("Beetle Juice", 30, "123 Main St.", 1);
            Employee employee = new Employee("Joe Baiden", 25, "456 Oak St.", 100);

            // Insert customer and employee into the database
            facade.createPerson(customer);
            facade.createPerson(employee);

            // Retrieve customer and employee from the database using their IDs
            Person retrievedCustomer = facade.getPersonById();
            Person retrievedEmployee = facade.getPersonById();

            // Print the retrieved customer and employee
            System.out.println(retrievedCustomer);
            System.out.println(retrievedEmployee);

            // Update the customer and employee in the database
            customer.setAddress("789 Elm St.");
            employee.setAge(30);
            facade.updatePerson(customer);
            facade.updatePerson(employee);

            // Delete the customer and employee from the database
            facade.deletePerson();
            facade.deletePerson();

            // Get all people from the database
            List<Person> allPersons = facade.getAllPersons();

            // Print all people from the database
            allPersons.forEach(System.out::println);

            try {
                facade.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
