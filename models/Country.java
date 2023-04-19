package models;

public class Country extends Model{
    private String country;

    public Country(int id) {
        super(id);
    }

    public Country(int id, String country) {
        super(id);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
