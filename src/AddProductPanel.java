import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductPanel extends JPanel implements ActionListener {

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
        add_button.addActionListener(this);
        add_button.setText("Add Product");
        add_button.setFocusable(false);
        add_button.setBackground(Color.black);
        add_button.setFont(new Font(add_button.getFont().getFontName(),Font.BOLD,10));
        add_button.setForeground(Color.white);
        add_button.setMargin(new Insets(0,0,0,0));

        back_button.setBounds(220,40,60,25);
        back_button.addActionListener(this);
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
        Frame1.frame.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back_button){
            AddandRemovePanels.remove_AddProductPanel();
            AddandRemovePanels.add_Panel1();
        }
        else if (e.getSource() == add_button){
            AddandRemovePanels.remove_AddProductPanel();
            AddandRemovePanels.add_Panel1();
        }
    }
}
