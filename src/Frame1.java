import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Frame1 extends JFrame implements ActionListener {
    static JFrame frame = new JFrame();
    static JLabel place_order_label = new JLabel();
    static JLabel add_product_label = new JLabel();
    static JLabel sales_and_order_label = new JLabel();
    static JLabel manage_customers_label = new JLabel();
    ImageIcon icon1 =new ImageIcon( new ImageIcon("gear.png").getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH));
    ImageIcon icon2 =new ImageIcon( new ImageIcon("more.png").getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH));
    ImageIcon icon3 =new ImageIcon( new ImageIcon("package-box.png").getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH));
    ImageIcon icon4 =new ImageIcon( new ImageIcon("transfer-money.png").getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH));


    Frame1() throws SQLException {
        this.show_table_view();
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    public void show_table_view() throws SQLException {
        Panel1 panel1 = new Panel1();

        add_product_label.setText("Add Product");
        add_product_label.setBounds(20,50,170,20);
        add_product_label.setIcon(icon2);
        add_product_label.setForeground(Color.blue);

        add_product_label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                add_product_label.setForeground(Color.blue);
                place_order_label.setForeground(Color.black);
                sales_and_order_label.setForeground(Color.black);
                manage_customers_label.setForeground(Color.black);
            }
            @Override public void mousePressed(MouseEvent e) {}@Override public void mouseReleased(MouseEvent e) {}@Override public void mouseEntered(MouseEvent e) {}@Override public void mouseExited(MouseEvent e) {}
        });


        place_order_label.setText("Place Order");
        place_order_label.setBounds(20,80,170,20);
        place_order_label.setIcon(icon1);
        place_order_label.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                add_product_label.setForeground(Color.black);
                place_order_label.setForeground(Color.blue);
                sales_and_order_label.setForeground(Color.black);
                manage_customers_label.setForeground(Color.black);}
            @Override public void mousePressed(MouseEvent e) {}@Override public void mouseReleased(MouseEvent e) {}@Override public void mouseEntered(MouseEvent e) {}@Override public void mouseExited(MouseEvent e) {}
        });



        sales_and_order_label.setText("Sales & Order");
        sales_and_order_label.setBounds(20,110,170,20);
        sales_and_order_label.setIcon(icon3);
        sales_and_order_label.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                add_product_label.setForeground(Color.black);
                place_order_label.setForeground(Color.black);
                sales_and_order_label.setForeground(Color.blue);
                manage_customers_label.setForeground(Color.black);}
            @Override public void mousePressed(MouseEvent e) {}@Override public void mouseReleased(MouseEvent e) {}@Override public void mouseEntered(MouseEvent e) {}@Override public void mouseExited(MouseEvent e) {}
        });

        manage_customers_label.setText("Manage Customers");
        manage_customers_label.setBounds(20,140,170,20);
        manage_customers_label.setIcon(icon4);
        manage_customers_label.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                add_product_label.setForeground(Color.black);
                place_order_label.setForeground(Color.black);
                sales_and_order_label.setForeground(Color.black);
                manage_customers_label.setForeground(Color.blue);}
            @Override public void mousePressed(MouseEvent e) {}@Override public void mouseReleased(MouseEvent e) {}@Override public void mouseEntered(MouseEvent e) {}@Override public void mouseExited(MouseEvent e) {}
        });

        frame.setTitle("Online Store");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(235,235,244));
        frame.setVisible(true);

        frame.add(Panel1.button);
        frame.add(Panel1.button1);
        frame.add(Panel1.button2);
        frame.add(Panel1.table);
        frame.add(Panel1.panel1);
        frame.add(place_order_label);frame.add(add_product_label);frame.add(sales_and_order_label);frame.add(manage_customers_label);
    }

    public void main(String[] args) throws SQLException {
        this.show_table_view();
    }



}
