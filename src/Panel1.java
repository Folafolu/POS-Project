import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Panel1 extends JPanel implements ActionListener {
    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    static Product product;
    static ArrayList<Product> product_list;
    int delete_prod_no_int;
    String delete_prod_no_string;
    int edit_prod_no_int;
    String edit_prod_no_string;
    static JButton button = new JButton();
    static JButton button1 = new JButton();
    static JButton button2 = new JButton();

    static String[] product_column_headers = {"Product no", "Name","Quantity", "Price ($)"};
    DefaultTableModel model = new DefaultTableModel();
    //static JTable table = new JTable(1, 4);
    static JTable table;
    static JPanel panel1 = new JPanel();



    public Panel1() throws SQLException {
        show_Panel1();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){ // Add Product Button
            System.out.println("h");
            Frame1.frame.add(new AddProductPanel());
        } else if (e.getSource() == button1) { // Edit Button
            System.out.println("o");
            edit_prod_no_string = JOptionPane.showInputDialog("Enter the Product no. 'To Edit'");
            if (edit_prod_no_string != null){
                edit_prod_no_int = Integer.parseInt(edit_prod_no_string); // do sth with this var (use it to prefill the textfields)
                Frame1.frame.add(new EditProductPanel());
            }

        } else if (e.getSource() == button2) { // Delete Button
            System.out.println("t");
            delete_prod_no_string = JOptionPane.showInputDialog("Enter the Product no 'To Delete'");
            if (delete_prod_no_string != null){
                delete_prod_no_int = Integer.parseInt(delete_prod_no_string);

                try {
                    // delete the row that matches with the entered product number
                    String del_sql_query = "DELETE FROM products WHERE Product_no = " + delete_prod_no_int;
                    statement = connection.createStatement();
                    statement.execute(del_sql_query);

                    // reset the ordering of the product number
                    String jdbcURL = "jdbc:mysql://localhost:3306/db_sql_tutorial";
                    String username = "fola";
                    String password = "fola";
                    try (Connection connection = DriverManager.getConnection(jdbcURL,username,password)){
                        try (Statement statement = connection.createStatement()){
                            String setUserVariable = "SET @autoid :=0;"; statement.executeUpdate(setUserVariable);
                            String updateProductsTable = "UPDATE products set Product_no = @autoid := (@autoid+1);"; statement.executeUpdate(updateProductsTable);
                            String resetAutoIncrement = "ALTER table products AUTO_INCREMENT = 1;"; statement.executeUpdate(resetAutoIncrement);
                        }
                    }
                } catch (SQLException ex) {throw new RuntimeException(ex);}

                // clear the old table, connect and display the new database
                try {
                    while (model.getRowCount() > 1){
                        model.removeRow(1);
                    }
                    create_connection();
                    display_products();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }


        }

    }

    public void show_Panel1() throws SQLException {
        create_connection();

        button.setBounds(220,40,90,25);
        button.addActionListener(this);
        button.setText("+ Add Product");
        button.setFocusable(false);
        button.setBackground(Color.black);
        button.setFont(new Font(button.getFont().getFontName(),Font.BOLD,10));
        button.setForeground(Color.white);
        button.setMargin(new Insets(0,0,0,0));

        button1.setBounds(650,40,90,25);
        button1.addActionListener(this);
        button1.setText("Edit Product");
        button1.setFocusable(false);
        button1.setBackground(Color.black);
        button1.setFont(new Font(button1.getFont().getFontName(),Font.BOLD,10));
        button1.setForeground(Color.white);
        button1.setMargin(new Insets(0,0,0,0));

        button2.setBounds(220,320,90,25);
        button2.addActionListener(this);
        button2.setText("Delete Product");
        button2.setFocusable(false);
        button2.setBackground(Color.black);
        button2.setFont(new Font(button2.getFont().getFontName(),Font.BOLD,10));
        button2.setForeground(Color.white);
        button2.setMargin(new Insets(0,0,0,0));

        panel1.setBackground(Color.white);
        panel1.setBounds(200,0,600,400);


        table = new JTable(model);
        model.addColumn("Col1");
        model.addColumn("Col2");
        model.addColumn("Col3");
        model.addColumn("Col4");
        model.addRow(product_column_headers);
        table.setBounds(220,100, 530, 200);
        display_products();

    }

    public void create_connection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/db_sql_tutorial";
        String username = "fola";
        String password = "fola";
        connection = DriverManager.getConnection(jdbcURL,username,password);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM products");
    }

    public ArrayList<Product> product_list() throws SQLException {
        product_list = new ArrayList<>();
        while (resultSet.next()){
            product = new Product(resultSet.getInt(1),resultSet.getString(2),
                    resultSet.getInt(3), (int) resultSet.getFloat(4));
            product_list.add(product);
        }
        return product_list;
    }

    public void display_products() throws SQLException {
        ArrayList<Product> list = product_list();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getProduct_no();
            row[1] = list.get(i).getProduct_name();
            row[2] = list.get(i).getProduct_quantity();
            row[3] = list.get(i).getProduct_price();
            model.addRow(row);
        }
    }

    public void main(String[] args) throws SQLException {
        this.show_Panel1();
    }

}
