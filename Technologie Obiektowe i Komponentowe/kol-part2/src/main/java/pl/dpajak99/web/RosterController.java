package pl.dpajak99.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.dpajak99.model.Member;

@Controller
public final class RosterController {

    private List<Member> members = new ArrayList<>();

    public RosterController() {
      members.add(new Member("Jan", "Kowalski"));
      members.add(new Member("Piotr", "Piotrowski"));
      members.add(new Member("Antoni", "Antonowski"));
      members.add(new Member("Kamil", "Kamilski"));

    }
    @RequestMapping("/list")
    public void list(Model model) {
      model.addAttribute(members);
    }

    @RequestMapping("/member")
    public void member(@RequestParam("id") Integer id, Model model) {
        model.addAttribute(members.get(id));
    }

    @RequestMapping("/")
    public String welcome(ModelMap model) {
      model.addAttribute("test", "test123");
        return "ok";
    }
}