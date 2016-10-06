package org.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Login Controller.
 *
 * @author cdacr
 */
@Controller
public final class LoginController {

  /**
   *
   * @param modelMap.
   * @return {@link String}
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(final ModelMap modelMap) {
    System.out.println("In the login method");
    return "login";
  }

  /**
   *
   * @param modelMap.
   * @return {@link String}
   */
  @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
  public String loginError(final ModelMap modelMap) {
    System.out.println("In the login error method");
    modelMap.addAttribute("error", "true");

    return "login";
  }

  /**
   *
   * @param modelMap.
   * @return {@link String}
   */
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(final ModelMap modelMap) {
    return "logout";
  }

  /**
   *
   * @param modelMap.
   * @return {@link String}
   */
  @RequestMapping(value = "/403", method = RequestMethod.GET)
  public String error403(final ModelMap modelMap) {
    return "403";
  }
}
