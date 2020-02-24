package hr.martinovic.FakeStagram.db;

import hr.martinovic.FakeStagram.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <Users, String> {
}
