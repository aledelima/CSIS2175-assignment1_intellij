import java.time.LocalDate;

public class Account {

    private int id;
    private double balance;
    private Person person;
    private LocalDate dateOfCreation;

    public Account() {}

    public Account(Integer id, double balance, Person person, LocalDate dateOfCreation) {

        this.id = id;
        this.balance = balance;
        this.person = person;
        this.dateOfCreation = dateOfCreation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void display() {
        System.out.println("Account holder information");
        this.person.display();
        System.out.println(" id           : " +  this.id);
        System.out.printf(" balance      : %.1f\n", this.balance);
        System.out.println(" Date         : " + this.dateOfCreation);
    }
}
