import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class CustomersPanel extends JPanel {
    static Connection connection;
    static ResultSet resultSet;
    static Statement statement;
    static ArrayList<Order> order_list;
    static ArrayList customer_list;
    static Order order;
    static Customer customer;
    static boolean Customers_Panel_is_visible;

    static JPanel customers_panel = new JPanel();
    static JLabel customer_list_label = new JLabel();

    static JTable table;
    static String[] order_column_headers = {"Customer id", "Name", "Order Frequency"};
    static DefaultTableModel model = new DefaultTableModel();

    public CustomersPanel() throws SQLException {
        show_customers_panel();
        Customers_Panel_is_visible = true;
    }


    public void show_customers_panel() throws SQLException {
        customers_panel.setBounds(200,0,600,400);
        customers_panel.setBackground(Color.white);

        customer_list_label.setText("Customer List");
        customer_list_label.setBounds(410,30,200,40);
        customer_list_label.setFont(new Font(customer_list_label.getFont().getFontName(),Font.BOLD,20));

        table = new JTable(model);
        if (model.getColumnCount() == 0){ // add columns once
            model.addColumn("Col1");
            model.addColumn("Col2");
            model.addColumn("Col3");
            model.addRow(order_column_headers);
        }
        table.getColumnModel().getColumn(0).setWidth(100);
        table.getColumnModel().getColumn(1).setWidth(150);
        table.getColumnModel().getColumn(2).setWidth(130);

        table.setBounds(300,100, 500, 400);
        table.setEnabled(false);
        table.setRowSelectionAllowed(false);

        update_customer_list();
        display_customers();

        Frame1.frame.add(customer_list_label); Frame1.frame.add(table);
        Frame1.frame.add(customers_panel);

    }

    public static void update_customer_list() throws SQLException {
        // add all the names from order list and how many times they've bought
        ArrayList temp_customer_arraylist = new ArrayList<>(); // temp array to store orders to check for duplicates

        // go through each order in order list
        create_connection();
        String clear_customer_list_sql_query = "TRUNCATE customers";//clear customer list to repopulate later
        statement.executeUpdate(clear_customer_list_sql_query);

        order_list = new ArrayList<>();
        SalesOrderPanels.create_connection();
        while (SalesOrderPanels.resultSet.next()) {
            if (!temp_customer_arraylist.contains(SalesOrderPanels.resultSet.getString(3)) ) {
                temp_customer_arraylist.add(SalesOrderPanels.resultSet.getString(3));
                String update_customer_list_sql_query = "INSERT INTO customers VALUES (1," + "'" + SalesOrderPanels.resultSet.getString(3) + "'" + ",1" + ")";
                statement.executeUpdate(update_customer_list_sql_query);
            }
            else{ // if we find orders from the same customer, add 1 to previous order frequency
                String edit_customer_list_sql_query = "UPDATE customers SET order_frequency = order_frequency + 1 WHERE name = " + "'" +SalesOrderPanels.resultSet.getString(3) + "'";
                statement.executeUpdate(edit_customer_list_sql_query);
            }
        }

        String setUserVariable = "SET @autoid :=0;";
        statement.executeUpdate(setUserVariable);
        String updateProductsTable = "UPDATE customers set customer_id = @autoid := (@autoid+1);";
        statement.executeUpdate(updateProductsTable);
        String resetAutoIncrement = "ALTER table customers AUTO_INCREMENT = 1;";
        statement.executeUpdate(resetAutoIncrement);

    }

    public static void create_connection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/db_sql_tutorial";
        String username = "fola";
        String password = "fola";
        connection = DriverManager.getConnection(jdbcURL,username,password);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM customers");

    }
    public static ArrayList<Customer> customer_list() throws SQLException {
        customer_list = new ArrayList<>();
        create_connection();
        while (resultSet.next()){
            customer = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
            customer_list.add(customer);
        }
        return customer_list;
    }

    public static void display_customers() throws SQLException {
        ArrayList<Customer> list = customer_list();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        if (model.getRowCount() > 1){ // check if the table has more than just the header row
            for (int i = model.getRowCount() -1; i > 0; i--){ // remove old rows
                model.removeRow(i);
            }
        }

        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++) { // add updated rows
            row[0] = list.get(i).getCustomer_id();
            row[1] = list.get(i).getCustomer_name();
            row[2] = list.get(i).getOrder_frequency();
            model.addRow(row);
        }
    }
}
