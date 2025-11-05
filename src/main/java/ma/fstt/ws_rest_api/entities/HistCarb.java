package ma.fstt.ws_rest_api.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "hist_carb")
public class HistCarb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    @ManyToOne
    @JoinColumn(name = "carburant_id")
    private Carburant carburant;


    @Column(name = "date", nullable = false)
    private LocalDate date = LocalDate.now(); // valeur default

    private BigDecimal prix;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Carburant getCarburant() {
        return carburant;
    }

    public void setCarburant(Carburant carburant) {
        this.carburant = carburant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public HistCarb(Long id, Station station, Carburant carburant, LocalDate date, BigDecimal prix) {
        this.id = id;
        this.station = station;
        this.carburant = carburant;
        this.date = date;
        this.prix = prix;
    }
    public HistCarb() {}
}