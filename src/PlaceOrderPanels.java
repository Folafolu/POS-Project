
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PlaceOrderPanels extends JPanel implements ActionListener {
    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    static boolean Place_Order_Panels_is_visible;
    static JPanel place_order_panel_top = new JPanel();
    static JPanel place_order_panel_bottom = new JPanel();
    static JLabel product_name_label = new JLabel();
    static JLabel product_quantity_label = new JLabel();
    static JLabel product_price_label = new JLabel();
    ArrayList<Product> product_list_combo_box;
    static JComboBox comboBox;
//    static JLabel order_list_name = new JLabel();
//    static JLabel order_list_quantity = new JLabel();
//    static JLabel order_list_price = new JLabel();
    static JTextField product_quantity_textfield = new JTextField();
    static JButton add_to_cart_button = new JButton();
    static JButton clear_order_list_button = new JButton();
    static JButton checkout_button = new JButton();
    static JLabel price_label  = new JLabel();
    static ArrayList order_history_array = new ArrayList<>();
    static JLabel total_order_cost_label = new JLabel();
    static JLabel total_order_cost_amount = new JLabel();
    static int order_list_buffer = 0;
    static String customers_name;
    //static ActionListener clear_actionlistener;
    public PlaceOrderPanels() throws SQLException {
        show_place_order_panels();
        Place_Order_Panels_is_visible = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    public void show_place_order_panels() throws SQLException {
        // top panel
        place_order_panel_top.setBackground(Color.white);
        place_order_panel_top.setBounds(200,20,600,100);

        product_name_label.setText("Products");
        product_name_label.setBounds(220,30,140,20);
        product_name_label.setForeground(Color.black);

        product_quantity_label.setText("Quantity");
        product_quantity_label.setBounds(380,30,140,20);
        product_quantity_label.setForeground(Color.black);

        product_price_label.setText("Price ($)");
        product_price_label.setBounds(470,30,140,20);
        product_price_label.setForeground(Color.black);

        place_order_panel_bottom.setBackground(Color.white);
        place_order_panel_bottom.setBounds(200,140,600,230);

        comboBox = new JComboBox<>();
        comboBox.setBounds(220,60,100,25);
        refresh_combo_box();

        total_order_cost_label.setText("Total: $"); total_order_cost_amount.setText("0.00");
        total_order_cost_label.setBounds(220,310,100,25);total_order_cost_amount.setBounds(288,310,100,25);
        total_order_cost_label.setFont(new Font(total_order_cost_label.getFont().getFontName(),Font.BOLD,18));
        total_order_cost_amount.setFont(new Font(total_order_cost_amount.getFont().getFontName(),Font.BOLD,18));


        product_quantity_textfield.setBounds(380,60,40,25);

        price_label.setBounds(470,60,40,25);
        price_label.setText("$0.00");
        product_quantity_textfield.setText("1");
        product_quantity_textfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // quantity can not be zero or null
                if (product_quantity_textfield == null || product_quantity_textfield.getText() == "0" || product_quantity_textfield.getText() == ""){
                    product_quantity_textfield.setText("1");
                }for (int i = 0; i < Panel1.product_list.size(); i++) {
                    if (Panel1.product_list.get(i).getProduct_name() == comboBox.getSelectedItem()) {
                        price_label.setText("$" +Integer.toString(Panel1.product_list.get(i).getProduct_price() * Integer.parseInt(product_quantity_textfield.getText())));
                    }}}});

        //constantly update price based on combo box option and product quantity
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
               // if (comboBox.getSelectedItem() == "Select") {
                   // price_label.setText("$0.00");
                //} else {
                    // quantity can not be zero or null
                    if (product_quantity_textfield == null || product_quantity_textfield.getText() == "0") {
                        product_quantity_textfield.setText("1");
                    }
                    for (int i = 0; i < Panel1.product_list.size(); i++) {
                        if (Panel1.product_list.get(i).getProduct_name() == comboBox.getSelectedItem()) {
                            price_label.setText("$" + Integer.toString(Panel1.product_list.get(i).getProduct_price() * Integer.parseInt(product_quantity_textfield.getText())));
                        }}}}
    //}
    );

        add_to_cart_button.setBounds(650,60,80,25);
        ActionListener add_to_cart_actionlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // List of order history
                order_list_buffer = 0;

                order_history_array.add(comboBox.getSelectedItem());order_history_array.add(product_quantity_textfield.getText());order_history_array.add(price_label.getText());
                // calculate the total order cost
                total_order_cost_amount.setText(Float.toString(Float.parseFloat(total_order_cost_amount.getText()) + Float.parseFloat(price_label.getText().substring(1))));

                // print out the order list added to cart

                for (int i = 0; i< order_history_array.size(); i+=3){
                    AddandRemovePanels.remove_PlaceOrderPanels();
                    Frame1.frame.add(new JLabel(order_history_array.get(i).toString())).setBounds(220,160 + order_list_buffer,130,20);
                    Frame1.frame.add(new JLabel(order_history_array.get(i+1).toString())).setBounds(380,160 + order_list_buffer,30,20);
                    Frame1.frame.add(new JLabel(order_history_array.get(i+2).toString())).setBounds(470,160 + order_list_buffer,50,20);

                    AddandRemovePanels.add_PlaceOrderPanels();
                    //Frame1.frame.repaint();
                    order_list_buffer += 20;
                }

                //Reset to add new product to cart
                comboBox.setSelectedItem("Select");product_quantity_textfield.setText("1");price_label.setText("$0.00");


            }
        };

        if(add_to_cart_button.getActionListeners().length == 0){ // only add action listener once
            add_to_cart_button.addActionListener(add_to_cart_actionlistener);
        }

        add_to_cart_button.setText("+ Add To Cart ");add_to_cart_button.setFocusable(false);
        add_to_cart_button.setBackground(Color.black);
        add_to_cart_button.setFont(new Font(add_to_cart_button.getFont().getFontName(),Font.BOLD,10));
        add_to_cart_button.setForeground(Color.white);
        add_to_cart_button.setMargin(new Insets(0,0,0,0));

        clear_order_list_button.setBounds(600,310,50,25);

        ActionListener clear_actionlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Remove all the items added to cart
                Component[] components = Frame1.frame.getContentPane().getComponents();
                for (int i = 0; i< order_history_array.size(); i++) {  // Remove each label in the Order list
                    for (Component component : components) {
                        if (component instanceof JLabel) {
                            JLabel label = (JLabel) component;
                            if (label.getText().equals(order_history_array.get(i))) {
                                Frame1.frame.remove(label);
                                //Frame1.frame.revalidate();
                                Frame1.frame.repaint();
                            }
                        }
                    }
                }
                order_history_array.clear();
                total_order_cost_amount.setText("0.00"); // Reset the total to zero
                //AddandRemovePanels.remove_PlaceOrderPanels();
                //AddandRemovePanels.add_PlaceOrderPanels();
            }
        };
        if (clear_order_list_button.getActionListeners().length == 0){ // only add action listener once
            clear_order_list_button.addActionListener(clear_actionlistener);
        }

        clear_order_list_button.setText("Clear");clear_order_list_button.setFocusable(false);
        clear_order_list_button.setBackground(Color.white);
        clear_order_list_button.setFont(new Font(clear_order_list_button.getFont().getFontName(),Font.BOLD,10));
        clear_order_list_button.setForeground(Color.black);
        clear_order_list_button.setMargin(new Insets(0,0,0,0));

        checkout_button.setBounds(660,310,70,25);

        ActionListener checkout_actionlistener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customers_name = JOptionPane.showInputDialog(null,"Enter your Name: ");
                try {
                    create_connection();
                    // create the AM/PM time format using dateformat
                    DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
                    String dateString = dateFormat.format(new Date()).toString();

                    // insert into orders table
                    String add_sql_query = "INSERT INTO orders VALUES (1,"  + "'"+java.time.LocalDate.now()+"'"  + "," + "'"+customers_name +"'"  + "," + Float.parseFloat(total_order_cost_amount.getText()) + "," + "'" +dateString + "'" + ")";
                    PlaceOrderPanels.statement.execute(add_sql_query);

                    String setUserVariable = "SET @autoid :=0;";
                    PlaceOrderPanels.statement.executeUpdate(setUserVariable);
                    String updateOrderssTable = "UPDATE orders set Order_id = @autoid := (@autoid+1);";
                    PlaceOrderPanels.statement.executeUpdate(updateOrderssTable);
                    String resetAutoIncrement = "ALTER table orders AUTO_INCREMENT = 1;";
                    PlaceOrderPanels.statement.executeUpdate(resetAutoIncrement);

                    Frame1.sales_and_order_label.dispatchEvent(new MouseEvent(Frame1.sales_and_order_label,MouseEvent.MOUSE_CLICKED,System.currentTimeMillis(),0,0,0,1,false));
                    JOptionPane.showMessageDialog(null,customers_name + ", Your Order has been placed","Order Complete", JOptionPane.PLAIN_MESSAGE);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };

        if (checkout_button.getActionListeners().length == 0){ // only add action listener once
            checkout_button.addActionListener(checkout_actionlistener);
        }

        checkout_button.setText("Checkout");checkout_button.setFocusable(false);
        checkout_button.setBackground(new Color(0,49,113));
        checkout_button.setFont(new Font(checkout_button.getFont().getFontName(),Font.BOLD,10));
        checkout_button.setForeground(Color.white);
        checkout_button.setMargin(new Insets(0,0,0,0));



        Frame1.frame.add(product_name_label);Frame1.frame.add(product_quantity_label);Frame1.frame.add(product_price_label);
        Frame1.frame.add(add_to_cart_button); Frame1.frame.add(comboBox); Frame1.frame.add(product_quantity_textfield);
        Frame1.frame.add(price_label); Frame1.frame.add(checkout_button); Frame1.frame.add(clear_order_list_button);
        Frame1.frame.add(total_order_cost_label);Frame1.frame.add(total_order_cost_amount);
        //Frame1.frame.add(order_list_name);Frame1.frame.add(order_list_quantity);Frame1.frame.add(order_list_price);
        Frame1.frame.add(place_order_panel_top);
        Frame1.frame.add(place_order_panel_bottom);
        Frame1.frame.repaint();

    }

    public void refresh_combo_box() throws SQLException {
        Panel1.create_connection();
        comboBox.removeAllItems();

        product_list_combo_box = Panel1.product_list;
        for (int i = 0; i < product_list_combo_box.size(); i++) {
            comboBox.addItem(product_list_combo_box.get(i).getProduct_name());
        }
        comboBox.addItem("Select");
        comboBox.setSelectedItem("Select");
    }


    public static void clearOrderList(){
        if (order_history_array.size() > 0 ){
            add_to_cart_button.removeActionListener(add_to_cart_button.getActionListeners()[0]);
            clear_order_list_button.doClick(); // clear the order list when clicking out of the place order panel

            if (customers_name == null){ // Check if the customer has checked out
                total_order_cost_amount.setText("0.00");
            }
        }
    }

    public static void create_connection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/db_sql_tutorial";
        String username = "fola";
        String password = "fola";
        connection = DriverManager.getConnection(jdbcURL,username,password);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM orders");

    }
}
