package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.util.StringUtil;
import com.cafe24.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@RequestMapping("")
	public String list(Model model) {
		List<BoardVo> list = boardService.getList();
		model.addAttribute("list",list);
//		System.out.println(list);
		
		return "board/list";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String search(@RequestParam String keyword, Model model) {
		System.out.println(keyword);
		List<BoardVo> list = boardService.getList(StringUtil.keywordBuilder(keyword));
		System.out.println(list);
		model.addAttribute("list",list);

		return "board/list";
	}
	
	@RequestMapping("/view/{no}")
	public String showView(@ModelAttribute BoardVo vo, 
											Model model,
						@CookieValue(value="storeIdCookie", required = false) Cookie storeIdCookie) {
		
		BoardVo view = boardService.getView(vo);
		model.addAttribute("vo",view);
		//System.out.println(view);
		return "board/view";
	}
	
	@RequestMapping("/delete/{no}")
	public String deleteView(@ModelAttribute BoardVo vo, Model model) {
		boardService.deleteView(vo);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/modify/{no}",method=RequestMethod.GET)
	public String showModifyView(@ModelAttribute BoardVo vo, Model model) {
		BoardVo view = boardService.getView(vo);
		model.addAttribute("vo",view);
		return "board/modify";
	}
	
	@RequestMapping(value="/modify/{no}", method=RequestMethod.POST)
	public String modifyView(@ModelAttribute BoardVo vo, Model model) {
		boardService.modifyView(vo);
		return "redirect:/board/view/"+vo.getNo();
	}
	
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardVo vo) {
		System.out.println("등록버튼시의 vo : " + vo);
		
		if(vo.getDepth()==0)
			boardService.insertView(vo);
		else 
			boardService.insertAnswerView(vo);
		
		return "redirect:/board";
	}
	
	@RequestMapping(value="/write/{no}", method = RequestMethod.GET)
	public String writeAnswer(@ModelAttribute BoardVo vo,Model model) {
		BoardVo view = boardService.getView(vo);
		System.out.println("넘어온 " + view);
		model.addAttribute("vo",view);
		
		return "board/write";
	}
	
}