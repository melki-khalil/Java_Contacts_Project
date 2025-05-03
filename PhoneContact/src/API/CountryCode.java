package API;

public class CountryCode {
    private String name;
    private String dial_code;
    private String code;
    private int length;

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for dial_code
    public String getDial_code() {
        return dial_code;
    }

    public void setDial_code(String dial_code) {
        this.dial_code = dial_code;
    }

    // Getter and Setter for code
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // Getter and Setter for length
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    // Optional: toString method for display purposes
    @Override
    public String toString() {
        return name + " (+" + dial_code + ")";
    }
}
