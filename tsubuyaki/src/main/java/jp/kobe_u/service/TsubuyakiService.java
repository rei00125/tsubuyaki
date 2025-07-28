package jp.kobe_u.cs.daikibo.tsubuyaki;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TsubuyakiService {
    @Autowired
    TsubuyakiRepository repo; // レポジトリ

    // つぶやきを投稿
    public Tsubuyaki postTsubuyaki(Tsubuyaki t) {
        String name = t.getName();
        if (name == null || name.length() == 0) {
            t.setName("名無しさん");
        }
        t.setCreatedAt(new Date());
        return repo.save(t);
    }

    // 全つぶやきを取得
    public List<Tsubuyaki> getAllTsubuyaki() {
        Iterable<Tsubuyaki> found = repo.findAll();
        ArrayList<Tsubuyaki> list = new ArrayList<>();
        found.forEach(list::add);
        return list;
    }

    // つぶやきをキーワードで検索する
    public List<Tsubuyaki> searchTsubuyaki(String keyword) {
        return repo.findByCommentContaining(keyword);
    }
}