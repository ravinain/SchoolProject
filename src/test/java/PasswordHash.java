import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHash {

  @Test
  public void test() {
    final String password = "password";
    final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    final String hashedPassword = passwordEncoder.encodePassword(password, null);
    System.out.println(hashedPassword);
  }

  @Test
  public void testBcrypt() {
    final String password = "password";
    final BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
    final String hashedPassword = pwdEncoder.encode(password);
    System.out.println(hashedPassword);
  }
}
