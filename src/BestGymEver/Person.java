package BestGymEver;

public class Person {

    protected String name;
    protected String socialSecurityNumber;
    protected int dateForLastPayedFee;


    public Person(String name, String socialSecurityNumber, int dateForLastPayedFee) {
        this.name = name;
        this.socialSecurityNumber = socialSecurityNumber;
        this.dateForLastPayedFee = dateForLastPayedFee;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public int getDateForLastPayedFee() {
        return dateForLastPayedFee;
    }

    public void setDateForLastPayedFee(int dateForLastPayedFee) {
        this.dateForLastPayedFee = dateForLastPayedFee;
    }

    @Override
    public String toString() {
        return "Person, " +
                "Name: " + name +
                " SocialSecurityNumber: " + socialSecurityNumber +
                " DateForLastPayedFee: " + dateForLastPayedFee;
    }


}
