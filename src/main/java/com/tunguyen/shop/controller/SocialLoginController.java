package com.tunguyen.shop.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.tunguyen.shop.domain.User;
import com.tunguyen.shop.service.UserService;

@Controller
@PropertySource("classpath:host.properties")
public class SocialLoginController {
	
	@Value("${host}")
	private String host;

	@Autowired
	LoginController lgolinController;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/sociallogin", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestBody String email, HttpSession session, Locale locale){
		
		ScopeBuilder scopeBuilder = new ScopeBuilder();
		scopeBuilder.addPermission(FacebookPermissions.PUBLIC_PROFILE);
		scopeBuilder.addPermission(FacebookPermissions.EMAIL);
		scopeBuilder.addPermission(FacebookPermissions.USER_BIRTHDAY);
		
		FacebookClient client = new DefaultFacebookClient(Version.LATEST);
		String loginDialogUrlString = client.getLoginDialogUrl("1857101377934421", host + "/login",
				scopeBuilder);
		System.out.println(loginDialogUrlString);

		return "{\"chao\":\"" + loginDialogUrlString + "\"}";
	}

	@RequestMapping(value = "/googlelogin")
	public @ResponseBody String googleLogin(@RequestBody String client_id_token, HttpSession session) {
		
		JSONObject idTokenObj = new JSONObject(client_id_token);
		String id_token = idTokenObj.getString("id_token");
		String GOOGLE_CLIENT_ID = "45263658763-9ibfn75ie12u9l13oo1use8vrenh1m03.apps.googleusercontent.com";
		try {

			System.out.println("id_token " + id_token);
			JacksonFactory jacksonFactory = new JacksonFactory();
			GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier(new NetHttpTransport(),
					jacksonFactory);
			GoogleIdToken token = GoogleIdToken.parse(jacksonFactory, id_token);

			if (googleIdTokenVerifier.verify(token)) {
				GoogleIdToken.Payload payload = token.getPayload();
				if (!GOOGLE_CLIENT_ID.equals(payload.getAudience())) {
					throw new IllegalArgumentException("Audience mismatch");
				} else if (!GOOGLE_CLIENT_ID.equals(payload.getAuthorizedParty())) {
					throw new IllegalArgumentException("Client ID mismatch");
				}
				
				User user = new User();
				user.setEmail(payload.getEmail());
				user.setUsername((String) payload.get("name"));
				user.setPassword("###!!asdff123AAA#######");
				user.setImage((String) payload.get("picture"));
				System.out.println("google image: " + (String) payload.get("picture"));

				User logedinuser = userService.loginGo(user);
				if (logedinuser == null) {
					return "redirect:/login";
				}
				session.setAttribute(LoginController.USER_, logedinuser);
				return "{\"chao\":\"/shop\"}";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "/shop/login";
	}

}
