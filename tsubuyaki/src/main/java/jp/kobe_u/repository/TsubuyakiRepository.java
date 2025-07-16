package jp.kobe_u.cs.daikibo.tsubuyaki;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TsubuyakiRepository extends CrudRepository<Tsubuyaki, Long> {
    // コメントの部分一致で検索するためのカスタム・クエリ
    public List<Tsubuyaki> findByCommentContaining(String keyword);
}
