
public class NameAndAddress {

    private String name;
    private String address;

    public NameAndAddress(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void display() {
        System.out.println("Name " + this.name);
        System.out.println("Address " + this.address);
    }

}
