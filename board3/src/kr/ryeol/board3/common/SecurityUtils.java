package kr.ryeol.board3.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;

import kr.ryeol.board3.model.UserModel;

public class SecurityUtils {
	
	// true: 로그아웃 상태, false: 로그인 상태
	public static boolean isLogout(HttpServletRequest request) {
		return getLoginUser(request) == null;
	}
	
	public static UserModel getLoginUser(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		return (UserModel) hs.getAttribute("loginUser");
	}
	
	public static int getLoingUserPk(HttpServletRequest request) {
		UserModel loginUser = getLoginUser(request);
		return loginUser.getI_user();
	}
	
	public static String getSecurePassword(String password, String salt) {
		
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] byteSalt = Base64.decodeBase64(salt);
			md.update(byteSalt);
			byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
	
	public static String getSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return Base64.encodeBase64String(salt);
	}
	

}
