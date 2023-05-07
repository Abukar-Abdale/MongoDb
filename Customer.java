import org.bson.Document;

public class Customer extends Person {
    private int customerNumber;

    public Customer(String name, int age, String address, int customerNumber) {
        super(name, age, address);
        this.customerNumber = customerNumber;
    }



    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Override
    public Document toDoc() {
        return super.toDoc().append("customer_number", customerNumber);
    }
}