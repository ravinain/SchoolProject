package org.school.util.test;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test Util.
 * @author cdacr
 *
 */
public final class TestUtil {

  /** */
  private TestUtil() {
  }

  /**
   * Remove proxy.
   * @param bean
   *          {@link Object}
   * @return Object {@link Object}
   * @throws Exception
   *           Exception
   */
  public static Object unwrapProxy(final Object bean) throws Exception {
    /*
     * If the given object is a proxy, set the return value as the object being
     * proxied, otherwise return the given object.
     */
    Object retBean = bean;
    if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {
      final Advised advised = (Advised) bean;
      retBean = advised.getTargetSource().getTarget();
    }
    return retBean;
  }

  /**
   * Convert Json object into String.
   * @param obj
   *          object
   * @return {@link String}
   */
  public static String asJsonString(final Object obj) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final String jsonContent = mapper.writeValueAsString(obj);
      return jsonContent;
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Object asStringJson(final String str, final Class type) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(str, type);
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Object asStringJson(final String str,
      final Class wrapper,
      final Class element) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(str,
          mapper.getTypeFactory().constructCollectionType(wrapper,
              element));
    } catch (final Exception e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }
}
