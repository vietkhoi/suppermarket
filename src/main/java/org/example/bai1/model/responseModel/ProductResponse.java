package org.example.bai1.model.responseModel;

public class ProductResponse {
    private Long id;
    private String name;
    private double price;
    private boolean isStock;
    private String description;
    private String categoryName;


    public ProductResponse(Long id, String name, double price, boolean isStock, String description, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isStock = isStock;
        this.description = description;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStock() {
        return isStock;
    }

    public void setStock(boolean stock) {
        isStock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}

