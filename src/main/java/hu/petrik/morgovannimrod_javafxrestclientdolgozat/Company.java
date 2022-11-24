package hu.petrik.morgovannimrod_javafxrestclientdolgozat;

public class Company {
    int id;
    String companyName;
    String companyPhone;
    int totalEmployee;
    String creditCard;

    public Company(int id, String companyName, String companyPhone, int totalEmployee, String creditCard) {
        this.id = id;
        this.companyName = companyName;
        this.companyPhone = companyPhone;
        this.totalEmployee = totalEmployee;
        this.creditCard = creditCard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public int getTotalEmployee() {
        return totalEmployee;
    }

    public void setTotalEmployee(int totalEmployee) {
        this.totalEmployee = totalEmployee;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
