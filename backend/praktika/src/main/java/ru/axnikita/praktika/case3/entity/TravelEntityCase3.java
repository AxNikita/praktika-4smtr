package ru.axnikita.praktika.case3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.axnikita.praktika.case3.model.Rating;

import java.util.List;

@Entity
@Table(name = "travels")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class TravelEntityCase3 {
    // author
    // title
    // description
    // geolocation
    // imgUrl
    // price
    // rating
    // placeForVisit
    // placeCulture

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "geolocation")
    private List<String> geolocation;

    @Column(name = "imgUrl")
    private String imgUrl;

    @Column(name = "price")
    private Integer price;

    @Column(name = "rating")
    @JdbcTypeCode(SqlTypes.JSON)
    private Rating rating;

    @Column(name = "placeForVisit")
    private List<String> placeForVisit;

    @Column(name = "placeCulture")
    private List<String> placeCulture;

    public static TravelEntityCase3 defaultTravelOf(String author, String title, String description, List<String> geolocation,
                                                    String imgUrl, Integer price, Rating rating,
                                                    List<String> placeForVisit, List<String> placeCulture) {
        TravelEntityCase3 entity = new TravelEntityCase3();
        entity.setTitle(title);
        entity.setAuthor(author);
        entity.setDescription(description);
        entity.setGeolocation(geolocation);
        entity.setImgUrl(imgUrl);
        entity.setPrice(price);
        entity.setRating(rating);
        entity.setPlaceForVisit(placeForVisit);
        entity.setPlaceCulture(placeCulture);
        return entity;
    }

    public TravelEntityCase3 baseOnAddId(TravelEntityCase3 entity, long id) {
        this.id = id;
        this.author = entity.getAuthor();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.geolocation = entity.getGeolocation();
        this.imgUrl = entity.getImgUrl();
        this.price = entity.getPrice();
        this.rating = entity.getRating();
        this.placeForVisit = entity.getPlaceForVisit();
        this.placeCulture = entity.getPlaceCulture();
        return this;
    }
}
