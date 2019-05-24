package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.security.Auth;
import com.cafe24.mysite.security.AuthUser;
import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo,
			BindingResult result,
			Model model) {
		
		if( result.hasErrors() ) {
			model.addAllAttributes(result.getModel());
			System.out.println(result.getModel());
			return "/user/join";
		}
		userService.join(userVo);
		
		// userDao.insert(userVo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
//	@ExceptionHandler(UserDaoException.class)
//	public String handleUserDaoException() {
//		System.out.println("하잇! 에러");
//		return "error/exception";
//	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/";
	}
	
	@Auth
	@RequestMapping( value="/update", method=RequestMethod.GET )
	public String update(
		@AuthUser UserVo authUser,
		Model model ){
		UserVo userVo = userService.getUser( authUser.getNo() );
		model.addAttribute( "userVo", userVo );
		return "user/update";
	}
}
