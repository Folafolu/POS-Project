import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame1 extends JFrame implements ActionListener {
    static JFrame frame = new JFrame();
    static JButton button = new JButton();
    static JButton button1 = new JButton();
    static JButton button2 = new JButton();

    static JTable table = new JTable(7,5);

    static JLabel place_order_label = new JLabel();
    static JLabel add_product_label = new JLabel();
    static JLabel sales_and_order_label = new JLabel();
    static JLabel manage_customers_label = new JLabel();

    static JPanel panel = new JPanel();

    static JTextField delete_textfield = new JTextField("Please input the product No. of the item you wish to remove");

    Frame1(){
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

    public void show_table_view(){
        place_order_label.setText("> Add Product");
        place_order_label.setBounds(20,50,100,20);
        place_order_label.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                place_order_label.setForeground(Color.red);}
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });


        add_product_label.setText("> Place Order");
        add_product_label.setBounds(20,80,100,20);
        add_product_label.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                add_product_label.setForeground(Color.red);}
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });

        sales_and_order_label.setText("> Sales & Order");
        sales_and_order_label.setBounds(20,110,110,20);
        sales_and_order_label.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                sales_and_order_label.setForeground(Color.red);}
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });

        manage_customers_label.setText("> Manage Customers");
        manage_customers_label.setBounds(20,140,140,20);
        manage_customers_label.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                manage_customers_label.setForeground(Color.red);}
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
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


        table.setBounds(220,100, 530, 200);

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
    public void main(String[] args) {

        this.show_table_view();
    }




}
