import org.bson.Document;

public class Customer extends Person {
    private final int customerNumber;

    public Customer(String name, int age, String address, int customerNumber) {
        super(name, age, address);
        this.customerNumber = customerNumber;
    }



    public int getCustomerNumber() {
        return customerNumber;
    }


    @Override
    public Document toDoc() {
        return super.toDoc().append("customer_number", customerNumber);
    }
}