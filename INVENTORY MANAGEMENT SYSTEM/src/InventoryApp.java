package modul;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InventoryApp extends JFrame {
    private Company company;
    private double totalBalance = 0.0;

    public InventoryApp() {
        company = new Company("OCAK-GÜLER COMPANY");
        initUI();
    }

    private void initUI() {
        setTitle("Inventory Management System");
        setSize(1000, 800);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));
        
        JButton addProductButton = new JButton("Register New Product Line");
        JButton addModelButton = new JButton("Define Product Model");
        JButton sellModelButton = new JButton("Process Model Sale");
        JButton viewProductsButton = new JButton("Review Inventory Portfolio");
        JButton balanceButton = new JButton("Financial Overview");


        panel.add(addProductButton);
        panel.add(addModelButton);
        panel.add(sellModelButton);
        panel.add(viewProductsButton);
        panel.add(balanceButton);

        addProductButton.addActionListener(e -> addProductDialog());
        addModelButton.addActionListener(e -> addModelDialog());
        sellModelButton.addActionListener(e -> sellModelDialog());
        viewProductsButton.addActionListener(e -> viewProductsDialog());
        balanceButton.addActionListener(e -> showBalance());

        add(panel);
    }

    private void addProductDialog() {
        String productName = JOptionPane.showInputDialog(this, "Enter Product Name:");
        if (productName != null && !productName.isEmpty()) {
            company.addProduct(new Product(productName));
            JOptionPane.showMessageDialog(this, "Product added successfully!");
        }
    }

    private void addModelDialog() {
        String productName = JOptionPane.showInputDialog(this, "Enter Product Name:");
        Product product = company.getProduct(productName);

        if (product != null) {
            String modelId = JOptionPane.showInputDialog(this, "Enter Model ID - Name:");
            double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Price:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Stock Quantity:"));

            product.addModel(new Model(modelId, price, stock));
            JOptionPane.showMessageDialog(this, "Model added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Product not found.");
        }
    }

    private void sellModelDialog() {
        String productName = JOptionPane.showInputDialog(null, "Enter Product Name:");
        Product product = company.getProduct(productName);

        if (product != null) {
            String modelId = JOptionPane.showInputDialog(null, "Enter Model ID:");
            Model model = product.getModel(modelId);

            if (model != null) {
                String[] saleOptions = {"Online Sale (Domestic)", "Online Sale (International)", "In-store Sale"};
                String saleChoice = (String) JOptionPane.showInputDialog(null, "Select Sale Type:", "Inventory Management System",
                        JOptionPane.QUESTION_MESSAGE, null, saleOptions, saleOptions[0]);

                int quantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Quantity to Sell:"));
                double totalPrice;

                switch (saleChoice) {
                    case "Online Sale (International)":
                        String[] countries = {"Germany", "USA", "France", "Italy"};
                        String country = (String) JOptionPane.showInputDialog(null, "Select Country:",
                                "International Sale", JOptionPane.QUESTION_MESSAGE, null, countries, countries[0]);

                        double internationalShippingFee = 200.0;
                        double taxRate = 0.02;
                        totalPrice = model.getPrice() * quantity * (1 + taxRate) + internationalShippingFee;;
                        model.sell(quantity, totalPrice, "International - " + country);
                        totalBalance = totalPrice;
                        JOptionPane.showMessageDialog(null, "Total Price (with tax and shipping): " + totalPrice + " for " + country);
                        break;

                    case "Online Sale (Domestic)":
                        String[] provinces = {"Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Aksaray", "Amasya", "Ankara",
                                "Antalya", "Artvin", "Aydın", "Balıkesir", "Bartın", "Batman", "Bayburt", "Bilecik",
                                "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum",
                                "Denizli", "Diyarbakır", "Düzce", "Edirne", "Elazığ", "Erzincan", "Erzurum",
                                "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Iğdır",
                                "Isparta", "İstanbul", "İzmir", "Kahramanmaraş", "Karabük", "Karaman", "Kars",
                                "Kastamonu", "Kayseri", "Kırıkkale", "Kırklareli", "Kırşehir", "Kilis",
                                "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Mardin", "Mersin",
                                "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Osmaniye", "Rize",
                                "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Şanlıurfa", "Şırnak",
                                "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Uşak", "Van", "Yalova",
                                "Yozgat", "Zonguldak"};

                        String province = (String) JOptionPane.showInputDialog(null, "Select Province:",
                                "Domestic Sale", JOptionPane.QUESTION_MESSAGE, null, provinces, provinces[0]);

                        double domesticShippingFee = 50.0;
                        totalPrice = model.getPrice() * quantity + domesticShippingFee;
                        model.sell(quantity, totalPrice, "Domestic Sale - " + province);
                        totalBalance += totalPrice;
                        JOptionPane.showMessageDialog(null, "Total Price (with shipping): " + totalPrice + " (Domestic, " + province + ")");
                        break;

                    case "In-store Sale":
                        String[] cities = {"Istanbul", "Ankara", "Sivas", "Izmir"};
                        String city = (String) JOptionPane.showInputDialog(null, "Select Store Location:",
                                "In-store Sale", JOptionPane.QUESTION_MESSAGE, null, cities, cities[0]);

                        totalPrice = model.getPrice() * quantity;
                        model.sell(quantity, totalPrice, "In-store - " + city);
                        totalBalance += totalPrice;
                        JOptionPane.showMessageDialog(null, "Total Price: " + totalPrice + " (In-store pickup at " + city + ")");
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Model not found.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Product not found.");
        }
    }

    private void viewProductsDialog() {
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setText(company.listProducts());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(this, scrollPane, "Products List", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showBalance() {
    JTextArea textArea = new JTextArea(10, 30);
    textArea.setText("Total Balance from Sales: " + totalBalance + "\n\nSales Details:\n");

    double overallRevenue = 0;
  
    for (Product product : company.products.values()) {
        for (Model model : product.models.values()) {
            for (String saleDetail : model.getSaleDetails()) {
                textArea.append(saleDetail + "\n");
                
                
            }
            overallRevenue += model.getPrice() * model.sold;  
        }
    }

    textArea.append("\nTotal Revenue from All Products: " + overallRevenue);

    JScrollPane scrollPane = new JScrollPane(textArea);
    JOptionPane.showMessageDialog(this, scrollPane, "Sales Details", JOptionPane.INFORMATION_MESSAGE);
}


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            InventoryApp app = new InventoryApp();
            app.setVisible(true);
        });
    }
}

class Company {
    private String name;
    public HashMap<String, Product> products;

    public Company(String name) {
        this.name = name;
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    public Product getProduct(String name) {
        return products.get(name);
    }

    public String listProducts() {
        StringBuilder sb = new StringBuilder("Company: " + name + "\n");
        for (Product product : products.values()) {
            sb.append(product.toString()).append("\n");
        }
        return sb.toString();
    }
}

class Product {
    private String name;
    public HashMap<String, Model> models;

    public Product(String name) {
        this.name = name;
        this.models = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addModel(Model model) {
        models.put(model.getId(), model);
    }

    public Model getModel(String id) {
        return models.get(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Product: " + name + "\n");
        for (Model model : models.values()) {
            sb.append("   ").append(model.toString()).append("\n");
        }
        return sb.toString();
    }
}

class Model {
    private String id;
    private double price;
    private int stock;
    int sold;
    private double revenue;
    private ArrayList<String> saleDetails;

    public Model(String id, double price, int stock) {
        this.id = id;
        this.price = price;
        this.stock = stock;
        this.sold = 0;
        this.revenue = 0.0;
        this.saleDetails = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void sell(int quantity, double saleRevenue, String location) {
        if (quantity <= stock) {
            stock -= quantity;
            sold += quantity;
            revenue += saleRevenue;
            saleDetails.add("Sold " + quantity + " at " + location + " for " + saleRevenue);
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient stock!");
        }
    }

    public ArrayList<String> getSaleDetails() {
        return saleDetails;
    }

    @Override
    public String toString() {
        return "Model ID: " + id + ", Price: " + price + ", Stock: " + stock + ", Sold: " + sold +
                ", Revenue: " + revenue;
    }
}
