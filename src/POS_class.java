import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class POS_class {
    Frame1 myFrame = new Frame1();


    public static void main(String[] args) throws IOException, SQLException {

        String jdbcURL = "jdbc:mysql://localhost:3306/db_sql_tutorial";
        String username = "fola";
        String password = "fola";

        Connection connection = DriverManager.getConnection(jdbcURL,username,password);

        //String sql = "INSERT INTO customers(customer_id, first_name, last_name, country, score) ";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

        //while (resultSet.next()){
            //System.out.println(resultSet.getString(2));
        //}

        ResultSetMetaData rsmd = resultSet.getMetaData();
        DefaultTableModel model = new DefaultTableModel();


        resultSet.next();



        String[][] data = {{resultSet.getString(2), "First Name", "Last Name"},
                {"1", "Fola", "Idris"}};
        String[] col = {"Id", "first name","last name"};


        Frame1 myFrame1 = new Frame1();

        //JTable table = new JTable(data, col);
        //table.setBounds(160,100, 300, 100);


        //table.setModel(new DefaultTableModel("x"));
        //table.setVisible(true);
        //table.setBounds(30,40,200,300);
        //table.setGridColor(Color.black);
        //table.setVisible(true);

        //JPanel panel = new JPanel();
        //panel.setSize(300,120);
        //panel.setSize(200,200);
        //panel.setBackground(Color.red);
        //panel.add(table);

        //panel.setVisible(true);


//        JFrame frame = new JFrame();
//        frame.setTitle("Add Product");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,500);
//        frame.setLayout(null);
//        frame.getContentPane().setBackground(new Color(244,244,244));
//        //frame.add(panel);
//        //frame.add(table);
//
//        frame.add(new JButton("add_Product_button"));
//        frame.setVisible(true);

        //Scanner readEnterKey = new Scanner(System.in);

        //while (readEnterKey.nextLine() == "\n"){
            //System.out.println(table.getValueAt(0,0));
        //}

    }


}
