// class for items

public class Item {

    String name; 
    String category;
    String sellerFName;
    String sellerLName;
    String sellerEmail;
    double price; 

    public Item(String name, String category, String first, String last, String email, double price){
        this.name = name; 
        this.category = category; 
        this.sellerFName = first;
        this.sellerLName = last; 
        this.sellerEmail = email;
        this.price = price; 
    }

    public String toString(){
        return "Name: " + name + 
        "\nCategory: " + category + 
        "\nSeller: " + sellerFName + " " + sellerLName +
        "\nEmail: " + sellerEmail + 
        "\nPrice: $" + price; 
    }

    
}
