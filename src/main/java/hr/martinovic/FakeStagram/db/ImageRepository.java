package hr.martinovic.FakeStagram.db;



import hr.martinovic.FakeStagram.model.Image;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository <Image, Long> {

    Iterable<Image> findAllByOwner(String owner);

	Optional<Image> findByIdAndOwner(Long id, String owner);

	boolean existsByIdAndOwner(Long id, String owner);
}

/*

@Repository
public class ImageRepository {

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert imageInserter;

    public ImageRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.imageInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("image")
                .usingGeneratedKeyColumns("id");
    }


    public Iterable<Image> findAll() {
        return jdbc.query("select id, name, date, type, fakeness from image", this::mapRowToImage);
    }

    public Image findOne(Long id) {
        return jdbc.queryForObject("select id, name, date, type,fakeness from image where id = ?", this::mapRowToImage, id);
    }

    public Image save(Image image) {
        image.setId(saveImageDetails(image));
        return image;
    }

    private long saveImageDetails(Image image) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", image.getName());
        values.put("date", image.getDate());
        values.put("type", image.getType());
        values.put("fakeness", image.getFakeness());

        return imageInserter.executeAndReturnKey(values).longValue();
    }

    private Image mapRowToImage(ResultSet rs, int rowNum) throws SQLException {
        Image image = new Image();

        image.setId(rs.getLong("id"));
        image.setDate(rs.getDate("date").toLocalDate());
        image.setName(rs.getString("name"));
        image.setType(Image.Type.valueOf(rs.getString("type")));
        image.setFakeness(rs.getDouble("fakeness"));

        return image;
    }

}

*/
