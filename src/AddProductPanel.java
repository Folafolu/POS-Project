import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddProductPanel extends JPanel implements ActionListener {
    static boolean Add_Product_Panel_is_visible;

    static JPanel add_product_panel = new JPanel();
    static JLabel product_name = new JLabel();
    static JLabel product_price = new JLabel();
    static JLabel product_quantity = new JLabel();
    static JLabel add_product_label = new JLabel();
    static JTextField product_name_text = new JTextField();
    static JTextField product_price_text = new JTextField();
    static JTextField product_quantity_text = new JTextField();
    static JButton add_button = new JButton();
    static JButton back_button = new JButton();

    AddProductPanel(){
        show_AddProductPanel();
        Add_Product_Panel_is_visible = true;
    }


    public void show_AddProductPanel(){
        add_product_label.setText("Add Product");
        add_product_label.setBounds(410,30,200,40);
        add_product_label.setForeground(Color.black);
        add_product_label.setFont(new Font(add_product_label.getFont().getFontName(),Font.BOLD,20));

        product_name.setText("Product Name");
        product_name.setBounds(430,100,140,20);
        product_name.setForeground(Color.black);

        product_price.setText("Price");
        product_price.setBounds(300,170,140,20);
        product_price.setForeground(Color.black);

        product_quantity.setText("Quantity");
        product_quantity.setBounds(600,170,140,20);
        product_quantity.setForeground(Color.black);

        add_product_panel.setBounds(200,0,600,400); //200,0,600,400
        add_product_panel.setBackground(Color.white);

        product_name_text.setBounds(390,120,170,28);
        product_price_text.setBounds(270,190,100, 28);
        product_quantity_text.setBounds(575,190, 100, 28);

        add_button.setBounds(430,300,90,25);
        add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String add_sql_query = "INSERT INTO products VALUES (1," + "'" + product_name_text.getText().substring(0,product_name_text.getText().length()) + "'" + "," + Integer.parseInt(product_quantity_text.getText()) + " ," + Integer.valueOf(product_price_text.getText()) + ")";
                    Panel1.statement.execute(add_sql_query);

                    String setUserVariable = "SET @autoid :=0;";
                    Panel1.statement.executeUpdate(setUserVariable);
                    String updateProductsTable = "UPDATE products set Product_no = @autoid := (@autoid+1);";
                    Panel1.statement.executeUpdate(updateProductsTable);
                    String resetAutoIncrement = "ALTER table products AUTO_INCREMENT = 1;";
                    Panel1.statement.executeUpdate(resetAutoIncrement);

                    // clear the old table, connect and display the new database
                    while (Panel1.model.getRowCount() > 1) {
                        Panel1.model.removeRow(1);
                    }

                    Panel1.display_products();
                    AddandRemovePanels.remove_AddProductPanel();
                    AddandRemovePanels.add_Panel1();


                } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
                add_button.removeActionListener(this);

            }
        });
        add_button.setText("Add Product");
        add_button.setFocusable(false);
        add_button.setBackground(Color.black);
        add_button.setFont(new Font(add_button.getFont().getFontName(),Font.BOLD,10));
        add_button.setForeground(Color.white);
        add_button.setMargin(new Insets(0,0,0,0));

        back_button.setBounds(220,40,60,25);
        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddandRemovePanels.remove_AddProductPanel();
                AddandRemovePanels.add_Panel1();
                back_button.removeActionListener(this);
            }
        });
        back_button.setText("<< Back");
        back_button.setFocusable(false);
        back_button.setBackground(Color.black);
        back_button.setFont(new Font(back_button.getFont().getFontName(),Font.BOLD,10));
        back_button.setForeground(Color.white);
        back_button.setMargin(new Insets(0,0,0,0));


        Frame1.frame.add(add_product_label);
        Frame1.frame.add(product_name);Frame1.frame.add(product_price);Frame1.frame.add(product_quantity);
        Frame1.frame.add(product_name_text);
        Frame1.frame.add(product_price_text);
        Frame1.frame.add(product_quantity_text);
        Frame1.frame.add(add_button);
        Frame1.frame.add(back_button);
        Frame1.frame.add(add_product_panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
