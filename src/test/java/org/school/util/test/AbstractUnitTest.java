package org.school.util.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Default unit test code.
 * @author cdacr
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    locations = {"file:src/main/webapp/WEB-INF/config/servlet-config.xml"}
)
@ActiveProfiles("test")
public abstract class AbstractUnitTest {
  /** */
  @Autowired
  private ApplicationContext context;

  /**
   * get context.
   * @return {@link ApplicationContext}
   */
  public final ApplicationContext getContext() {
    return context;
  }

  /**
   * set {@link ApplicationContext}.
   * @param context
   *          {@link ApplicationContext}
   */
  public final void setContext(final ApplicationContext context) {
    this.context = context;
  }

}
