package pl.coderslab.model;

import pl.coderslab.dao.EmployeeDao;

import java.time.LocalDate;

public class PurchaseOrder {

    private int id;
    private int employeeId;
    private int carId;
    private int statusId;
    private LocalDate dateOfEntry;
    private LocalDate plannedRepair;
    private LocalDate dateOfStartRepair;
    private String problemDescription;
    private String fixingDescription;
    private double costForClient;
    private double costOfSpare;
    private double costOfEmployee;
    private double workingHour;

    public PurchaseOrder() {
    }
    EmployeeDao employeeDao = new EmployeeDao();

    public PurchaseOrder(int employeeId, int carId, int statusId, LocalDate dateOfEntry, LocalDate plannedRepair, LocalDate dateOfStartRepair, String problemDescription, String fixingDescription, double costOfSpare, double workingHour) {
        this.employeeId = employeeId;
        this.carId = carId;
        this.statusId = statusId;
        this.dateOfEntry = dateOfEntry;
        this.plannedRepair = plannedRepair;
        this.dateOfStartRepair = dateOfStartRepair;
        this.problemDescription = problemDescription;
        this.fixingDescription = fixingDescription;
        this.costOfSpare = costOfSpare;
        this.costOfEmployee = employeeDao.read(employeeId).getWorkingHour();
        this.workingHour = workingHour;
        this.costForClient = (this.costOfEmployee * this.workingHour + this.costOfSpare) * 2.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public LocalDate getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(LocalDate dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public LocalDate getPlannedRepair() {
        return plannedRepair;
    }

    public void setPlannedRepair(LocalDate plannedRepair) {
        this.plannedRepair = plannedRepair;
    }

    public LocalDate getDateOfStartRepair() {
        return dateOfStartRepair;
    }

    public void setDateOfStartRepair(LocalDate dateOfStartRepair) {
        this.dateOfStartRepair = dateOfStartRepair;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getFixingDescription() {
        return fixingDescription;
    }

    public void setFixingDescription(String fixingDescription) {
        this.fixingDescription = fixingDescription;
    }

    public double getCostForClient() {
        return costForClient;
    }

    public void setCostForClient(double costForClient) {
        this.costForClient = costForClient;
    }

    public double getCostOfSpare() {
        return costOfSpare;
    }

    public void setCostOfSpare(double costOfSpare) {
        this.costOfSpare = costOfSpare;
    }

    public double getCostOfEmployee() {
        return costOfEmployee;
    }

    public void setCostOfEmployee(double costOfEmployee) {
        this.costOfEmployee = costOfEmployee;
    }

    public double getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(double workingHour) {
        this.workingHour = workingHour;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", carId=" + carId +
                ", statusId=" + statusId +
                ", dateOfEntry=" + dateOfEntry +
                ", plannedRepair=" + plannedRepair +
                ", dateOfStartRepair=" + dateOfStartRepair +
                ", problemDescription='" + problemDescription + '\'' +
                ", fixingDescription='" + fixingDescription + '\'' +
                ", costForClient=" + costForClient +
                ", costOfSpare=" + costOfSpare +
                ", costOfEmployee=" + costOfEmployee +
                ", workingHour=" + workingHour +
                '}';
    }
}
