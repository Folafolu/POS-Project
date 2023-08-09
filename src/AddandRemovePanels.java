import javax.swing.*;

public class AddandRemovePanels extends JPanel {
    public AddandRemovePanels() {
    }

    public static void remove_Panel1(){
        Frame1.frame.remove(Panel1.panel1);
        Frame1.frame.remove(Panel1.button);
        Frame1.frame.remove(Panel1.button1);
        Frame1.frame.remove(Panel1.button2);
        Frame1.frame.remove(Panel1.table);
        Frame1.frame.repaint();
    }
    public static void add_Panel1(){
        Frame1.frame.add(Panel1.button);
        Frame1.frame.add(Panel1.button1);
        Frame1.frame.add(Panel1.button2);
        Frame1.frame.add(Panel1.table);
        Frame1.frame.add(Panel1.panel1);
        Frame1.frame.repaint();
    }
    public static void remove_AddProductPanel(){
        Frame1.frame.remove(AddProductPanel.product_name);
        Frame1.frame.remove(AddProductPanel.add_product_label);
        Frame1.frame.remove(AddProductPanel.product_price);
        Frame1.frame.remove(AddProductPanel.product_quantity);
        Frame1.frame.remove(AddProductPanel.product_name_text);
        Frame1.frame.remove(AddProductPanel.product_price_text);
        Frame1.frame.remove(AddProductPanel.product_quantity_text);
        Frame1.frame.remove(AddProductPanel.back_button);
        Frame1.frame.remove(AddProductPanel.add_button);
        Frame1.frame.remove(AddProductPanel.add_product_panel);
        Frame1.frame.repaint();
    }
    public static void add_AddProductPanel(){
        Frame1.frame.add(AddProductPanel.product_name);
        Frame1.frame.add(AddProductPanel.add_product_label);
        Frame1.frame.add(AddProductPanel.product_price);
        Frame1.frame.add(AddProductPanel.product_quantity);
        Frame1.frame.add(AddProductPanel.product_name_text);
        Frame1.frame.add(AddProductPanel.product_price_text);
        Frame1.frame.add(AddProductPanel.product_quantity_text);
        Frame1.frame.add(AddProductPanel.back_button);
        Frame1.frame.add(AddProductPanel.add_button);
        Frame1.frame.add(AddProductPanel.add_product_panel);
        Frame1.frame.repaint();
    }

    public static void remove_EditProductPanel(){
        Frame1.frame.remove(EditProductPanel.product_name);
        Frame1.frame.remove(EditProductPanel.edit_product_label);
        Frame1.frame.remove(EditProductPanel.product_price);
        Frame1.frame.remove(EditProductPanel.product_quantity);
        Frame1.frame.remove(EditProductPanel.product_name_text);
        Frame1.frame.remove(EditProductPanel.product_price_text);
        Frame1.frame.remove(EditProductPanel.product_quantity_text);
        Frame1.frame.remove(EditProductPanel.back_button);
        Frame1.frame.remove(EditProductPanel.save_button);
        Frame1.frame.remove(EditProductPanel.edit_product_panel);
        Frame1.frame.repaint();
    }

    public static void remove_PlaceOrderPanels(){
        Frame1.frame.remove(PlaceOrderPanels.product_name_label);
        Frame1.frame.remove(PlaceOrderPanels.product_quantity_label);
        Frame1.frame.remove(PlaceOrderPanels.product_price_label);
        Frame1.frame.remove(PlaceOrderPanels.add_to_cart_button);
        Frame1.frame.remove(PlaceOrderPanels.comboBox);
        Frame1.frame.remove(PlaceOrderPanels.price_label);
        Frame1.frame.remove(PlaceOrderPanels.product_quantity_textfield);
        Frame1.frame.remove(PlaceOrderPanels.order_list_name);
        Frame1.frame.remove(PlaceOrderPanels.order_list_quantity);
        Frame1.frame.remove(PlaceOrderPanels.order_list_price);
        Frame1.frame.remove(PlaceOrderPanels.clear_order_list_button);
        Frame1.frame.remove(PlaceOrderPanels.checkout_button);
        Frame1.frame.remove(PlaceOrderPanels.place_order_panel_top);
        Frame1.frame.remove(PlaceOrderPanels.place_order_panel_bottom);
        Frame1.frame.repaint();
    }
}
