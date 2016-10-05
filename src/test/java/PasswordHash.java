import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHash {

	@Test
	public void test() {
		String password = "password";
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		
		String hashedPassword = passwordEncoder.encodePassword(password, null);
		System.out.println(hashedPassword);
	}

	@Test
	public void testBcrypt() {
		String password = "password";
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		String hashedPassword = pwdEncoder.encode(password);
		System.out.println(hashedPassword);
	}
}
