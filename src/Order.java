import java.sql.Time;
import java.util.Date;

public class Order {
    private int order_id;
    private String customer_name;
    private float total_cost;
    private Date order_date;
    private Time order_time;


    public Order(int order_id, Date order_date, String customer_name, float total_cost, Time order_time){
        this.order_id = order_id;
        this.customer_name = customer_name;
        this.total_cost = total_cost;
        this.order_date = order_date;
        this.order_time = order_time;
    }
    public int getOrder_id(){return order_id;}
    public String getCustomer_name(){return customer_name;}
    public float getTotal_cost(){return total_cost;}
    public Date getOrder_date(){return order_date;}
    public Time getOrder_time() {return order_time;}
}



