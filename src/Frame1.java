import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;

public class Frame1 extends JFrame implements ActionListener {
    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    static Product product;
    static ArrayList<Product> product_list;


    static JFrame frame = new JFrame();
    static JButton button = new JButton();
    static JButton button1 = new JButton();
    static JButton button2 = new JButton();

    static String[] product_column_headers = {"Product no", "Name","Quantity", "Price ($)"};
    DefaultTableModel model = new DefaultTableModel();
    //static JTable table = new JTable(1, 4);
    static JTable table;



    static JLabel place_order_label = new JLabel();
    static JLabel add_product_label = new JLabel();
    static JLabel sales_and_order_label = new JLabel();
    static JLabel manage_customers_label = new JLabel();

    static JPanel panel = new JPanel();


    Frame1() throws SQLException {
        this.show_table_view();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            System.out.println("h");
            frame.add(new AddProductPanel());
        } else if (e.getSource() == button1) {
            System.out.println("o");
        } else if (e.getSource() == button2) {
            System.out.println("t");
        }
    }

    public void show_table_view() throws SQLException {
        create_connection();

        place_order_label.setText("> Add Product");
        place_order_label.setBounds(20,50,100,20);
        place_order_label.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                place_order_label.setForeground(Color.red);}
            @Override public void mousePressed(MouseEvent e) {}@Override public void mouseReleased(MouseEvent e) {}@Override public void mouseEntered(MouseEvent e) {}@Override public void mouseExited(MouseEvent e) {}
        });


        add_product_label.setText("> Place Order");
        add_product_label.setBounds(20,80,100,20);
        add_product_label.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                add_product_label.setForeground(Color.red);}
            @Override public void mousePressed(MouseEvent e) {}@Override public void mouseReleased(MouseEvent e) {}@Override public void mouseEntered(MouseEvent e) {}@Override public void mouseExited(MouseEvent e) {}
        });

        sales_and_order_label.setText("> Sales & Order");
        sales_and_order_label.setBounds(20,110,110,20);
        sales_and_order_label.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                sales_and_order_label.setForeground(Color.red);}
            @Override public void mousePressed(MouseEvent e) {}@Override public void mouseReleased(MouseEvent e) {}@Override public void mouseEntered(MouseEvent e) {}@Override public void mouseExited(MouseEvent e) {}
        });

        manage_customers_label.setText("> Manage Customers");
        manage_customers_label.setBounds(20,140,140,20);
        manage_customers_label.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                manage_customers_label.setForeground(Color.red);}
            @Override public void mousePressed(MouseEvent e) {}@Override public void mouseReleased(MouseEvent e) {}@Override public void mouseEntered(MouseEvent e) {}@Override public void mouseExited(MouseEvent e) {}
        });

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

        panel.setBackground(Color.white);
        panel.setBounds(200,0,600,400);

        table = new JTable(model);
        model.addColumn("Col1");
        model.addColumn("Col2");
        model.addColumn("Col3");
        model.addColumn("Col4");
        model.addRow(product_column_headers);
        table.setBounds(220,100, 530, 200);
        display_products();

        frame.setTitle("Add Product");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(235,235,244));
        frame.setVisible(true);

        frame.add(button);
        frame.add(button1);
        frame.add(button2);
        frame.add(table);
        frame.add(panel);
        frame.add(place_order_label);frame.add(add_product_label);frame.add(sales_and_order_label);frame.add(manage_customers_label);

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

        this.show_table_view();
    }



}
