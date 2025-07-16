package jp.kobe_u.cs.daikibo.tsubuyaki;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam; // この行を追加

@Controller
public class TsubuyakiController {
    @Autowired
    TsubuyakiService ts;

    // タイトル画面を表示
    @GetMapping("/")
    String showIndex() {
        return "index";
    }

    // メイン画面を表示（つぶやき検索もここ）
    @GetMapping("/read")
    String showTsubuyakiList(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Tsubuyaki> list;
        // keywordがnullでない、かつ空文字列でない場合は、検索処理を行う
        if (keyword != null && !keyword.isEmpty()) {
            list = ts.searchTsubuyaki(keyword);
        } else {
            // そうでなければ、全件表示
            list = ts.getAllTsubuyaki();
        }
        model.addAttribute("tsubuyakiList", list);
        model.addAttribute("tsubuyakiForm", new TsubuyakiForm());
        return "tsubuyaki_list";
    }

    // つぶやきを投稿
    @PostMapping("/read")
    String postTsubuyaki(@ModelAttribute("tsubuyakiForm") TsubuyakiForm form, Model model) {
        Tsubuyaki t = new Tsubuyaki();
        t.setName(form.getName());
        t.setComment(form.getComment());
        ts.postTsubuyaki(t);
        return "redirect:/read";
    }
}