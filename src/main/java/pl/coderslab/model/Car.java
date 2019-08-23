package pl.coderslab.model;

import java.time.LocalDate;

public class Car {

    private int id;
    private int clientId;
    private String model;
    private String brand;
    private LocalDate yearOfManufacture;
    private String dataPlate;
    private LocalDate technicalReview;



    public Car() {
    }

    public Car(int clientId, String model, String brand, LocalDate yearOfManufacture, String dataPlate, LocalDate technicalReview) {
        this.clientId = clientId;
        this.model = model;
        this.brand = brand;
        this.yearOfManufacture = yearOfManufacture;
        this.dataPlate = dataPlate;
        this.technicalReview = technicalReview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDate getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(LocalDate yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getDataPlate() {
        return dataPlate;
    }

    public void setDataPlate(String dataPlate) {
        this.dataPlate = dataPlate;
    }

    public LocalDate getTechnicalReview() {
        return technicalReview;
    }

    public void setTechnicalReview(LocalDate technicalReview) {
        this.technicalReview = technicalReview;
    }

    @Override
    public String toString() {
        return "car{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                ", dataPlate=" + dataPlate +
                ", technicalReview=" + technicalReview +
                '}';
    }
}