package pl.dpajak99.web;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class Application {
  @InitBinder("subscriber")
  public void initSubscriberBinder(WebDataBinder binder) {
    binder.setAllowedFields(new String[] {
      "firstName", "lastName", "email"
    });
  }
  @InitBinder("unsubscriber")
  public void initUnsubscriberBinder(WebDataBinder binder) {
    binder.setAllowedFields(new String[] { "email" });
  }
}
