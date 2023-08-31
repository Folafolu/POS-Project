import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SalesOrderPanels extends JPanel {
    static boolean Sales_Order_Panels_is_visible;

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    static ArrayList<Order> order_list;
    static Order order;
    static JTable table;
    static String[] order_column_headers = {"Order id", "Order date", "Customer name","Total cost", "Order time"};
    static DefaultTableModel model = new DefaultTableModel();

    static JPanel sales_order_panel_main = new JPanel();
    static JPanel total_sales_today_panel = new JPanel();
    static JLabel total_sales_today_label_name = new JLabel();
    static JLabel total_sales_today_label = new JLabel("$0.00");

    static JPanel all_time_sales_panel = new JPanel();
    static float all_time_sales_float = 0;
    static JLabel all_time_sales_label_name = new JLabel();
    static JLabel all_time_sales_label = new JLabel("$0.00");
    static JPanel total_products_in_store_panel = new JPanel();
    static JLabel total_products_in_store_label_name = new JLabel();
    static JLabel total_products_in_store_label = new JLabel("0");

    public SalesOrderPanels() throws SQLException {
        show_sales_order_panels();
        Sales_Order_Panels_is_visible = true;
    }

    public void show_sales_order_panels() throws SQLException {

        sales_order_panel_main.setBounds(200,0,600,400);sales_order_panel_main.setBackground(Color.white);
        total_sales_today_panel.setBounds(220,20,140,80);total_sales_today_panel.setBackground(new Color(255, 223, 211));
        all_time_sales_panel.setBounds(420,20,140,80);all_time_sales_panel.setBackground(new Color(199,215,240));
        total_products_in_store_panel.setBounds(620,20,140,80);total_products_in_store_panel.setBackground(new Color(236,213,227));

        total_sales_today_label.setBounds(240,60,110,20);total_sales_today_label.setFont(new Font(total_sales_today_label.getFont().getFontName(),Font.BOLD,25));
        total_sales_today_label_name.setText("Sales Today");total_sales_today_label_name.setBounds(240,35,100,20);total_sales_today_label_name.setFont(new Font(total_sales_today_label_name.getFont().getFontName(),Font.BOLD,12));

        // calculating all-time sales
        create_connection();
        resultSet = statement.executeQuery("SELECT SUM(Total_cost) FROM orders");
        if (resultSet.next()){
            all_time_sales_label.setText("$" + String.valueOf(resultSet.getFloat(1)));
        }

        // calculating total sales today
        create_connection();
        resultSet = statement.executeQuery("SELECT SUM(Total_cost) FROM orders WHERE Order_date = " + "'" + java.time.LocalDate.now() + "'");
        if (resultSet.next()){
            total_sales_today_label.setText("$" + String.valueOf(resultSet.getFloat(1)));
        }

        all_time_sales_label.setBounds(440,60,110,20);all_time_sales_label.setFont(new Font(all_time_sales_label.getFont().getFontName(),Font.BOLD,25));
        all_time_sales_label_name.setText("All-Time Sales");all_time_sales_label_name.setBounds(440,35,100,20);all_time_sales_label_name.setFont(new Font(all_time_sales_label_name.getFont().getFontName(),Font.BOLD,12));

        total_products_in_store_label.setBounds(640,60,110,20);total_products_in_store_label.setFont(new Font(total_products_in_store_label.getFont().getFontName(),Font.BOLD,25));
        total_products_in_store_label_name.setText("Product Count");total_products_in_store_label_name.setBounds(640,35,100,20);total_products_in_store_label_name.setFont(new Font(total_products_in_store_label_name.getFont().getFontName(),Font.BOLD,12));
        total_products_in_store_label.setText(String.valueOf(Panel1.product_list.size()));

        table = new JTable(model);


        if (model.getColumnCount() == 0){ // add columns once
            model.addColumn("Col1");
            model.addColumn("Col2");
            model.addColumn("Col3");
            model.addColumn("Col4");
            model.addColumn("Col5");
            model.addRow(order_column_headers);
        }

        table.getColumnModel().getColumn(1).setWidth(100);
        table.getColumnModel().getColumn(2).setWidth(150);
        table.getColumnModel().getColumn(3).setWidth(100);
        table.getColumnModel().getColumn(4).setWidth(100);

        table.setBounds(220,140, 600, 300);
        table.setEnabled(false);
        table.setRowSelectionAllowed(false);

        create_connection();
        String setUserVariable = "SET @autoid :=0;";
        statement.executeUpdate(setUserVariable);
        String updateProductsTable = "UPDATE orders set Order_id = @autoid := (@autoid+1);";
        statement.executeUpdate(updateProductsTable);
        String resetAutoIncrement = "ALTER table orders AUTO_INCREMENT = 1;";
        statement.executeUpdate(resetAutoIncrement);
        display_orders();

        Frame1.frame.add(all_time_sales_label_name);Frame1.frame.add(total_sales_today_label_name);Frame1.frame.add(total_products_in_store_label_name);
        Frame1.frame.add(total_sales_today_label);Frame1.frame.add(all_time_sales_label);Frame1.frame.add(total_products_in_store_label);
        Frame1.frame.add(total_sales_today_panel);Frame1.frame.add(all_time_sales_panel);Frame1.frame.add(total_products_in_store_panel);
        Frame1.frame.add(table);

        Frame1.frame.add(sales_order_panel_main);

    }


    public static void create_connection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/db_sql_tutorial";
        String username = "fola";
        String password = "fola";
        connection = DriverManager.getConnection(jdbcURL,username,password);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY Order_date DESC, Order_time DESC");


    }
    public static ArrayList<Order> order_list() throws SQLException {
        order_list = new ArrayList<>();
        create_connection();
        while (resultSet.next()){
            order = new Order(resultSet.getInt(1), resultSet.getDate(2),resultSet.getString(3),resultSet.getFloat(4),resultSet.getString(5));
            order_list.add(order);
        }
        return order_list;
    }

    public static void display_orders() throws SQLException {
        ArrayList<Order> list = order_list();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        if (model.getRowCount() > 1){ // check if the table has more than just the header row
            for (int i = model.getRowCount() -1; i > 0; i--){ // remove old rows
                model.removeRow(i);
            }
        }

        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) { // add updated rows
            row[0] = list.get(i).getOrder_id();
            row[1] = list.get(i).getOrder_date();
            row[2] = list.get(i).getCustomer_name();
            row[3] = list.get(i).getTotal_cost();
            row[4] = list.get(i).getOrder_time();
            model.addRow(row);
        }
    }
}
