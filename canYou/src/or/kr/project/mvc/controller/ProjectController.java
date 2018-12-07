package or.kr.project.mvc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import or.kr.project.dto.CategoryVO;
import or.kr.project.dto.MemberVO;
import or.kr.project.dto.ProductVO;
import or.kr.project.dto.ProjectDonateVO;
import or.kr.project.dto.ProjectVO;
import or.kr.project.dto.ReplyVO;
import or.kr.project.mvc.dao.projectDaoImple;

@Controller
public class ProjectController {
	@Autowired
	private projectDaoImple dao;
	
	@RequestMapping(value="/")
	public String index(HttpServletRequest request, Model model) {
		return "index";
	}
	
	@RequestMapping(value="index")
	public String index2(HttpServletRequest request, Model model) {
		return "index";
	}
	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, Model model) {
		AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
		if (trustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())){	// 익명 상태에서 로그인 페이지 이동시
			return "login";
		}
		else{		// 로그인 상태에서 로그인 페이지로 이동하려고 할 때 index 페이지로 이동
			return "index";
		}
	}
	
	@RequestMapping(value="/denied")
	public String denied() {
		return "denied";
	}
	
	@RequestMapping(value="/error404")
	public String error404() {
		return "error404";
	}
	
	@RequestMapping(value="/error500")
	public String error500() {
		return "error500";
	}
	
	@RequestMapping(value="/proupform")		// 프로젝트 업로드 폼 페이지
	public ModelAndView proupform(HttpServletRequest request){
		List<CategoryVO> category=dao.casel();		// 프로젝트 업로드에 필요한 카테고리 목록을 가져옴
		
		// 세션에서 로그인 된 ID를 가져오는 작업
		SecurityContext impl=SecurityContextHolder.getContext();
		String implstr=impl.getAuthentication().getName(); 
		// 끝-------------------
		MemberVO vo=dao.memname(implstr);	// 가져온 ID를 토대로 회원 번호, 이름을 가져온다
		String name=vo.getMemberName();		// 이름을 name 변수에 저장
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("proupform");
		mav.addObject("category", category);	// 카테고리 목록 붙임
		mav.addObject("memberName", name);		// name을 object로 붙여서 같이 전송
		return mav;
	}
	
	@RequestMapping(value="/proup")		// 프로젝트 업로드
	public ModelAndView proup(@ModelAttribute("projvo") ProjectVO vo, MultipartFile mfile,
			String proname, String procnt, String proinfo, String procost,
			HttpServletRequest request) {
		ProductVO prodvo = new ProductVO();
		String[] pname=proname.split(",");
		String[] pcnt=procnt.split(",");
		String[] pinfo=proinfo.split(",");
		String[] pcost=procost.split(",");
		
		String img_path = "resources\\images";
		String r_path = request.getRealPath("/");
		String oriFn;
		
		if (mfile==null) {
			oriFn = "null.jpg";
		}else {
			oriFn = mfile.getOriginalFilename();
		}
		StringBuffer path = new StringBuffer();
		path.append(r_path).append(img_path).append("\\");
		path.append(oriFn);
		
		if(mfile!=null) {
			File f = new File(path.toString());
			try {
				mfile.transferTo(f);
			}catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		SecurityContext impl=SecurityContextHolder.getContext();	// 세션에서 spring security 정보를 가져옴
		String implstr=impl.getAuthentication().getName();	// security 정보에서 세션에 담겨있는 로그인 정보 중 ID 가져옴
		MemberVO vo2=dao.memname(implstr);	// ID를 토대로 회원정보 가져옴 (회원 번호, 회원 이름)
		vo.setMemberNo(vo2.getMemberNo());	// 프로젝트 테이블에 넣을 회원 번호를 넣음
		
		vo.setProjectMainImage(oriFn);
		dao.proin(vo);		// 프로젝트 업로드
		
		for(int i=0; i<pname.length; i++) {
			
			prodvo.setProductCnt(Integer.parseInt(pcnt[i]));
			prodvo.setProductName(pname[i]);
			prodvo.setProductInfo(pinfo[i]);
			prodvo.setProductCost(Integer.parseInt(pcost[i]));
			prodvo.setProjectNo(vo.getProjectNo());
			
			dao.prodin(prodvo);		// 상품 업로드
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/proupsuccess");
		return mav;
	}
	
	@RequestMapping(value="/proupsuccess")		// 프로젝트 업로드 성공시
	public ModelAndView proupsuccess(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("proupsuccess");
		return mav;
	}
	
	 @RequestMapping(value = "/imageUpload", method = RequestMethod.POST)	// 에디터에서 이미지 업로드
	 public void commUpload(HttpServletRequest request,HttpSession session, 
	   HttpServletResponse response, @RequestParam MultipartFile upload) {
	  OutputStream out = null;
	  PrintWriter printWriter = null;
	  response.setCharacterEncoding("EUC-KR");
	  response.setContentType("text/html;charset=EUC-KR");
	  try {
	   String fileName = upload.getOriginalFilename();
	   byte[] bytes = upload.getBytes();
	   String path = session.getServletContext().getRealPath("/resources/images");
	   String uploadPath = path + "\\" + fileName;// 저장경로
	   out = new FileOutputStream(new File(uploadPath));
	   out.write(bytes);
	   String callback = request.getParameter("CKEditorFuncNum");

	   printWriter = response.getWriter();

	   String fileUrl = "resources/images/" + fileName;// url경로
	   // 파일 업로드를 비동기식으로 연결할 때 Callback을 정의한다.
	   //printWriter.println("data:");
	   printWriter.println("<script type='text/javascript'>"
	     + "window.parent.CKEDITOR.tools.callFunction(" + callback
	     + ",'" + fileUrl + "','이미지를 업로드 하였습니다.'" + ")</script>");
	   printWriter.flush();

	  } catch (IOException e) {
	   e.printStackTrace();
	  } finally {
	   try {
	    if (out != null) {
	     out.close();
	    }
	    if (printWriter != null) {
	     printWriter.close();
	    }
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	  }

	  return;
	 }
	 
	
	 @RequestMapping("/update")
		public String updateView(Model m, HttpServletRequest req) {
		 	HttpSession s=req.getSession();
		 	String str=(String)s.getAttribute("projnum");
		 	
		 	ProjectVO vo=dao.modifyview(str);
		 	m.addAttribute("project", vo);
		 	
			return "ProjectModify";
		}

	@GetMapping("/list")
	public String listView(Model m, String num, HttpServletRequest req) {
		// project 관련한 정보 빼오기
		ProjectVO list = dao.projectlist(num);
		m.addAttribute("list",list);
		
		// project와 연결된 product 가져오기
		List<ProductVO> list2=dao.prodsel(num);
		m.addAttribute("prodlist", list2);
		
		// project와 연결된 member 정보 가져오기
		MemberVO mem=dao.memname2(list.getMemberNo());
		m.addAttribute("member", mem);
				
		//댓글 정보 가져오기
		List<ReplyVO> replylist = dao.replyList(num);
		m.addAttribute("replylist", replylist);
		System.out.println("replylist 크기 : "+ replylist.size());
		
		HttpSession s=req.getSession();
		s.setAttribute("projnum", num);
		
		return "detail";
	}

		// 프로젝트 수정
		@PostMapping("/modify")
		public ModelAndView modifyView(@ModelAttribute("projvo") ProjectVO vo, HttpServletRequest request) {
			// 프로젝트 수정 - 파일업로드
			String img_path = "resources\\images";
			String r_path = request.getRealPath("/");
			String oriFn = vo.getMultipartFile().getOriginalFilename();
			StringBuffer projectMainImage = new StringBuffer();
			projectMainImage.append(r_path).append(img_path).append("\\");
			projectMainImage.append(oriFn);
			File f = new File(projectMainImage.toString());
			System.out.println("projectMainImage : " + projectMainImage);
			try {
				vo.getMultipartFile().transferTo(f);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			vo.setProjectMainImage(vo.getMultipartFile().getOriginalFilename());
			
			// 프로젝트 모두수정 - 메소드 호출
			dao.modify(vo);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("redirect:list");
			return mav;
		}
		
		//프로젝트 둘러보기
		@RequestMapping("/lookaround")
		public String look(Model m) {
			List<CategoryVO> list = dao.categoryLookAround();
				
			m.addAttribute("list", list);
			
			return "lookaround";
		}
		
		//모든 프로젝트 둘러보기
		@RequestMapping("/AllList")
		public String AllList(Model m) {
			List<ProjectVO> list = dao.projectALLlist();
				
			m.addAttribute("list", list);
			return "AllList";
		}
		
		// 댓글 등록
	   @PostMapping("/reply1")
	   public String reply1(Model m, ReplyVO vo, HttpSession s) {
		   SecurityContext impl=SecurityContextHolder.getContext();	// 세션에서 spring security 정보를 가져옴
		   String implstr=impl.getAuthentication().getName();	// security 정보에서 세션에 담겨있는 로그인 정보 중 ID 가져옴
		   MemberVO vo2=dao.memname(implstr);	// ID를 토대로 회원정보 가져옴 (회원 번호, 회원 이름)
		   vo.setMemberNo(vo2.getMemberNo());	// 프로젝트 테이블에 넣을 회원 번호를 넣음
		   vo.setProjectNo(Integer.parseInt((String)s.getAttribute("projnum")));	// 세션에 등록한 projectNumber를 가져옴
		   s.removeAttribute("projnum");	// 사용한 session 속성 제거
		   
		   dao.replyInsert(vo); 

		   return "redirect:list?num="+vo.getProjectNo();
	   }

		// 마이페이지
		@RequestMapping(value = "/mylist")
		public String getDonateList(Model m) {
			SecurityContext impl=SecurityContextHolder.getContext();	// 세션에서 spring security 정보를 가져옴
			String implstr=impl.getAuthentication().getName();	// security 정보에서 세션에 담겨있는 로그인 정보 중 ID 가져옴
			MemberVO vo2=dao.memname(implstr);	// ID를 토대로 회원정보 가져옴 (회원 번호, 회원 이름)
			
			List<ProjectVO> list = dao.myDonateProject(vo2.getMemberNo());
			System.out.println(list.size());
			m.addAttribute("list", list);
			return "mypage";
		}

		// 후원할때 들어오는 메소드
		@RequestMapping(value = "/donate")
		public String donateProject(ProjectDonateVO vo) {
			SecurityContext impl=SecurityContextHolder.getContext();	// 세션에서 spring security 정보를 가져옴
			String implstr=impl.getAuthentication().getName();	// security 정보에서 세션에 담겨있는 로그인 정보 중 ID 가져옴
			MemberVO vo2=dao.memname(implstr);	// ID를 토대로 회원정보 가져옴 (회원 번호, 회원 이름)
			int memno = vo2.getMemberNo();
			
			
			vo.setMemberNo(memno);
			
			dao.donate(vo); //projectDonate 행 추가
			
			// 돈 차감
			Map<String, Integer> m = new HashMap<>();
			m.put("donateMoney", vo.getDonateMoney());
			m.put("memberNo", memno);
			
			dao.donateMoney(m); 
			
			return "redirect:/mylist";
		}

		// 후원 취소
		@RequestMapping(value = "/cancle")
		public String cancle(int projectNo/*이후에 프로젝트 선택해서 할 경우를 대비해서*/) {
			SecurityContext impl=SecurityContextHolder.getContext();	// 세션에서 spring security 정보를 가져옴
			String implstr=impl.getAuthentication().getName();	// security 정보에서 세션에 담겨있는 로그인 정보 중 ID 가져옴
			MemberVO vo2=dao.memname(implstr);	// ID를 토대로 회원정보 가져옴 (회원 번호, 회원 이름)
			int memno = vo2.getMemberNo();
			
			// 사용자의 돈을 반환
			dao.returnMoney(memno);
			// 돈 돌려 준 후에 행 삭제
			dao.donateCancle(memno);
			return "redirect:/list"; // 다시 리스트 화면으로
		}
}