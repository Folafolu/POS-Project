public class Customer {


    private int customer_id;
    private String customer_name;
    private int order_frequency;


    public Customer(int customer_id, String customer_name, int order_frequency) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.order_frequency = order_frequency;
    }
    public int getCustomer_id() {return customer_id;}

    public String getCustomer_name() {return customer_name;}

    public int getOrder_frequency() {return order_frequency;}


}
