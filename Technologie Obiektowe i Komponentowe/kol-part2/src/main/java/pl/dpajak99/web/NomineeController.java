package pl.dpajak99.web;

//import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dpajak99.model.Member;

import java.util.logging.Logger;

@Controller
public final class NomineeController {
//  private static final Logger log = Logger.getLogger(NomineeController.class);
  private String thanksViewName;
  public void setThanksViewName(String thanksViewName) {
    this.thanksViewName = thanksViewName;
  }
  @RequestMapping(method = RequestMethod.GET)
  public Member form() { return new Member();}
  @RequestMapping(method = RequestMethod.POST)
  public String processFormData(Member member) {
//    log.info("Processing nominee: " + member);
    return thanksViewName;
  }}