import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Panel1 extends JPanel implements ActionListener {
    static boolean Panel_is_visible;
    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    static Product product;
    static ArrayList<Product> product_list;
    int delete_prod_no_int;
    String delete_prod_no_string;
    static int edit_prod_no_int;
    String edit_prod_no_string;
    static JButton button = new JButton();
    static JButton button1 = new JButton();
    static JButton button2 = new JButton();

    static String[] product_column_headers = {"Product no", "Name","Quantity", "Price ($)"};
    static DefaultTableModel model = new DefaultTableModel();
    static JTable table;
    static JPanel panel1 = new JPanel();



    public Panel1() throws SQLException {
        show_Panel1();
        Panel_is_visible = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void show_Panel1() throws SQLException {
        button.setBounds(220,40,90,25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddandRemovePanels.remove_Panel1();
                new AddProductPanel();
            }
        });
        button.setText("+ Add Product");
        button.setFocusable(false);
        button.setBackground(Color.black);
        button.setFont(new Font(button.getFont().getFontName(),Font.BOLD,10));
        button.setForeground(Color.white);
        button.setMargin(new Insets(0,0,0,0));

        button1.setBounds(650,40,90,25);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit_prod_no_string = JOptionPane.showInputDialog("Enter the Product no. 'To Edit'");
                if (edit_prod_no_string != null){
                    edit_prod_no_int = Integer.parseInt(edit_prod_no_string); // do sth with this var (use it to prefill the textfields)
                    AddandRemovePanels.remove_Panel1(); // Remove Panel1
                    Frame1.frame.add(new EditProductPanel());
                }
            }
        });
        button1.setText("Edit Product");
        button1.setFocusable(false);
        button1.setBackground(Color.black);
        button1.setFont(new Font(button1.getFont().getFontName(),Font.BOLD,10));
        button1.setForeground(Color.white);
        button1.setMargin(new Insets(0,0,0,0));

        button2.setBounds(220,320,90,25);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                            try {
                                String setUserVariable = "SET @autoid :=0;"; statement.executeUpdate(setUserVariable);
                                String updateProductsTable = "UPDATE products set Product_no = @autoid := (@autoid+1);"; statement.executeUpdate(updateProductsTable);
                                String resetAutoIncrement = "ALTER table products AUTO_INCREMENT = 1;"; statement.executeUpdate(resetAutoIncrement);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    } catch (SQLException ex) {throw new RuntimeException(ex);}

                    // clear the old table, connect and display the new database
                    try {
                        while (model.getRowCount() > 1){
                            model.removeRow(1);
                        }
                        display_products();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
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
        table.setEnabled(false);
        table.setRowSelectionAllowed(false);

        display_products();

        Frame1.frame.add(button);
        Frame1.frame.add(button1);
        Frame1.frame.add(button2);
        Frame1.frame.add(table);
        Frame1.frame.add(panel1);


    }

    public static void create_connection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/db_sql_tutorial";
        String username = "fola";
        String password = "fola";
        connection = DriverManager.getConnection(jdbcURL,username,password);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM products");

    }

    public static ArrayList<Product> product_list() throws SQLException {
        product_list = new ArrayList<>();
        create_connection();
        while (resultSet.next()){
            product = new Product(resultSet.getInt(1),resultSet.getString(2),
                    resultSet.getInt(3), (int) resultSet.getFloat(4));
            product_list.add(product);
        }
        return product_list;
    }

    public static void display_products() throws SQLException {
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
        //this.show_Panel1();
    }

}
