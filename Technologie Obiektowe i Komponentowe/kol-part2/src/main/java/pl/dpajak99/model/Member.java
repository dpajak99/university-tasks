package pl.dpajak99.model;

public class Member {
    private String firstName;
    private String lastName;
    public Member() {}

    public Member(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }


    public String toString() {
        return firstName + " " + lastName;
    }
}