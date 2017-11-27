package com.tunguyen.shop.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import com.tunguyen.shop.domain.User;
import com.tunguyen.shop.exception.AuthenticationException;
import com.tunguyen.shop.service.UserService;

@Controller
public class LoginController implements ApplicationListener<ApplicationEvent> {
	
	public static final String USER_ = "user";

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@GetMapping("/login")
	public String login(Locale locale, Model model, HttpSession session, @RequestParam(required = false) String code) {
		System.out.println("code: " + code);
		if (code != null) {
			try {
				String urlString = "https://graph.facebook.com/v2.11/oauth/access_token?client_id=1857101377934421&redirect_uri=http://localhost:8080/shop/login&client_secret=19a77c6a0965dc70225f06ec17010f07&code="
						+ code;
				JSONObject accessTokenObj = getFace(urlString);
				String accessToken = accessTokenObj.getString("access_token");
				System.out.println("access token: " + accessToken);

				String userString = "https://graph.facebook.com/me?access_token=" + accessToken
						+ "&fields=email,name,birthday,picture&type=small";
				JSONObject userInfo = getFace(userString);
				System.out.println("user info: " + userInfo.toString());

				User u = new User();
				u.setPassword("###!!asdff123AAA#######");
				try {
					String birthday = userInfo.getString("birthday");
					System.out.println("birthday: " + birthday);
					SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
					Date dateCreated = dt.parse(birthday);
					u.setDateCreated(dateCreated);
				} catch (JSONException e) {
				}

				u.setEmail(userInfo.getString("email"));
				u.setUsername(userInfo.getString("name"));
				JSONObject pict = userInfo.getJSONObject("picture");
				System.out.println("pict: " + pict.toString());
				JSONObject pictData = pict.getJSONObject("data");
				u.setImage(pictData.getString("url"));

				User user = userService.loginFace(u);
				if (user == null) {
					return "redirect:/login";
				}

				session.setAttribute(USER_, user);
				// return "redirect:/";
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:/login";
			}
		}

		if (session.getAttribute(USER_) != null) {
			System.out.println("session not null");
			return "redirect:/";
		}
		model.addAttribute("user", new User());
		return "login";
	}
		
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, Locale locale,
			HttpSession session) throws AuthenticationException {
		logger.info("login controller");
		if (result.hasErrors()) {
			return "login";
		}
		User loginUser = userService.login(user);
		if (loginUser != null) {
			session.setAttribute(USER_, loginUser);
		} else {
			model.addAttribute("wrongcridentical", true);
			return "login";
		}
		return "redirect:/";
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		ServletRequestHandledEvent evt = (ServletRequestHandledEvent) event;
		if (evt.getRequestUrl().contains("login")) {
			logger.info(evt.getProcessingTimeMillis() + " milisec - login");
		}
	}
	
	public JSONObject getFace(String urlString) {
		URL url;
		try {
			url = new URL(urlString);
			URLConnection conn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			JSONObject jsonObject = new JSONObject(response.toString());
			System.out.println("JSONObject getFace: " + response.toString());
			in.close();
			return jsonObject;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
