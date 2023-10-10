import java.time.LocalDate;

public class Person {

    private NameAndAddress nameAndAddress;
    private LocalDate dateOfBirth;

    public Person(String name, String address, LocalDate dateOfBirth) {

        this.nameAndAddress = new NameAndAddress(name, address);
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return this.nameAndAddress.getName();
    }

    public String getAddress() {
        return this.nameAndAddress.getAddress();
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void display() {
        this.nameAndAddress.display();
        System.out.println(" DOB          : " + this.dateOfBirth);
    }
}
