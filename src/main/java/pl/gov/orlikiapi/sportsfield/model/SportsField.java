package pl.gov.orlikiapi.sportsfield.model;

import pl.gov.orlikiapi.sportsfieldtype.model.SportsFieldType;
import pl.gov.orlikiapi.sportsvenue.model.SportsVenue;

import javax.persistence.*;

@Entity
@Table(name = "sports_field")
public class SportsField {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn (name = "type_id", referencedColumnName = "id")
    private SportsFieldType sportsFieldType;

    @ManyToOne(optional = false)
    @JoinColumn (name = "sports_venue_id", referencedColumnName = "id")
    private SportsVenue sportsVenue;

    public SportsField() {

    }

    public SportsField(long id, SportsFieldType sportsFieldType, SportsVenue sportsVenue) {
        this.id = id;
        this.sportsFieldType = sportsFieldType;
        this.sportsVenue = sportsVenue;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SportsFieldType getSportsFieldType() {
        return sportsFieldType;
    }

    public void setSportsFieldType(SportsFieldType sportsFieldType) {
        this.sportsFieldType = sportsFieldType;
    }

    public SportsVenue getSportsVenue() {
        return sportsVenue;
    }

    public void setSportsVenue(SportsVenue sportsVenue) {
        this.sportsVenue = sportsVenue;
    }

    @Override
    public String toString() {
        return sportsVenue.getCity() + ", " + sportsVenue.getStreet() + " [" + sportsFieldType.getType() + "]";
    }
}
