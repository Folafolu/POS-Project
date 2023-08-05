public class Product {
    private int product_no;
    private String product_name;
    private int product_quantity;
    private int product_price;




    public Product(int product_no, String product_name, int product_quantity, int product_price) {
        this.product_no = product_no;
        this.product_name = product_name;
        this.product_quantity = product_quantity;
        this.product_price = product_price;
    }
    public int getProduct_no(){return product_no;}
    public String getProduct_name() {return product_name;}
    public int getProduct_quantity(){return product_quantity;}
    public int getProduct_price(){return product_price;}
}
