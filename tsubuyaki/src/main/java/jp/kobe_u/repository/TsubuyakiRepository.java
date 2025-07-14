package jp.kobe_u.cs.daikibo.tsubuyaki;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TsubuyakiRepository extends CrudRepository<Tsubuyaki, Long> {
}

