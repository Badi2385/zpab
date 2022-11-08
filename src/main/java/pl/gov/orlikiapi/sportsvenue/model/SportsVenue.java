package pl.gov.orlikiapi.sportsvenue.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sports_venue")
public class SportsVenue {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    public SportsVenue() {

    }

    public SportsVenue(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return city + ", " + street;
    }
}
