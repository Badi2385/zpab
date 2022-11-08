package pl.gov.orlikiapi.sportsfieldtype.model;

import javax.persistence.*;

@Entity
@Table(name = "sports_field_type")
public class SportsFieldType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    public SportsFieldType() {

    }

    public SportsFieldType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SportsField Type [id=" + id + ", type=" + type + "]";
    }
}
