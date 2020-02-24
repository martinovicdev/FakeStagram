package hr.martinovic.FakeStagram.db;


import hr.martinovic.FakeStagram.model.Authorities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, String> {
}
