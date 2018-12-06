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
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import or.kr.project.mvc.dao.projectDaoImple;

@Controller
public class ProjectController {
	@Autowired
	private projectDaoImple dao;
	
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request, Model model) {
		return "index";
	}
	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, Model model) {
		AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
		if (trustResolver.isAnonymous(SecurityContextHolder.getContext().getAuthentication())){	// �͸� ���¿��� �α��� ������ �̵���
			return "login";
		}
		else{		// �α��� ���¿��� �α��� �������� �̵��Ϸ��� �� �� index �������� �̵�
			return "index";
		}
	}
	
	@RequestMapping(value="/denied")
	public String denied() {
		return "denied";
	}
	
	@RequestMapping(value="/proupform")		// ������Ʈ ���ε� �� ������
	public ModelAndView proupform(HttpServletRequest request){
		List<CategoryVO> category=dao.casel();		// ������Ʈ ���ε忡 �ʿ��� ī�װ� ����� ������
		
		// ���ǿ��� �α��� �� ID�� �������� �۾�
		SecurityContext impl=SecurityContextHolder.getContext();
		String implstr=impl.getAuthentication().getName(); 
		// ��-------------------
		MemberVO vo=dao.memname(implstr);	// ������ ID�� ���� ȸ�� ��ȣ, �̸��� �����´�
		String name=vo.getMemberName();		// �̸��� name ������ ����
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("proupform");
		mav.addObject("category", category);	// ī�װ� ��� ����
		mav.addObject("memberName", name);		// name�� object�� �ٿ��� ���� ����
		return mav;
	}
	
	@RequestMapping(value="/proup")		// ������Ʈ ���ε�
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
		
		SecurityContext impl=SecurityContextHolder.getContext();	// ���ǿ��� spring security ������ ������
		String implstr=impl.getAuthentication().getName();	// security �������� ���ǿ� ����ִ� �α��� ���� �� ID ������
		MemberVO vo2=dao.memname(implstr);	// ID�� ���� ȸ������ ������ (ȸ�� ��ȣ, ȸ�� �̸�)
		vo.setMemberNo(vo2.getMemberNo());	// ������Ʈ ���̺� ���� ȸ�� ��ȣ�� ����
		
		vo.setProjectMainImage(oriFn);
		dao.proin(vo);		// ������Ʈ ���ε�
		
		for(int i=0; i<pname.length; i++) {
			
			prodvo.setProductCnt(Integer.parseInt(pcnt[i]));
			prodvo.setProductName(pname[i]);
			prodvo.setProductinfo(pinfo[i]);
			prodvo.setProductCost(Integer.parseInt(pcost[i]));
			prodvo.setProjectNo(vo.getProjectNo());
			
			dao.prodin(prodvo);		// ��ǰ ���ε�
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/proupsuccess");
		return mav;
	}
	
	@RequestMapping(value="/proupsuccess")		// ������Ʈ ���ε� ������
	public ModelAndView proupsuccess(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("proupsuccess");
		return mav;
	}
	
	 @RequestMapping(value = "/imageUpload", method = RequestMethod.POST)	// �����Ϳ��� �̹��� ���ε�
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
	   String uploadPath = path + "\\" + fileName;// ������
	   out = new FileOutputStream(new File(uploadPath));
	   out.write(bytes);
	   String callback = request.getParameter("CKEditorFuncNum");

	   printWriter = response.getWriter();

	   String fileUrl = "resources/images/" + fileName;// url���
	   // ���� ���ε带 �񵿱������ ������ �� Callback�� �����Ѵ�.
	   //printWriter.println("data:");
	   printWriter.println("<script type='text/javascript'>"
	     + "window.parent.CKEDITOR.tools.callFunction(" + callback
	     + ",'" + fileUrl + "','�̹����� ���ε� �Ͽ����ϴ�.'" + ")</script>");
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
		public String updateView() {
			return "list";
		}

		@RequestMapping("/list")
		public String listView(Model m) {
			List<ProjectVO> list = dao.projectlist();
			m.addAttribute("list", list);
			return "detail";
		}

		// �ڽ��� �ø� ������Ʈ ����
		@RequestMapping("/modify")
		public String modifyView(ProjectVO vo) {
			dao.modify(vo);
			return "redirect:/list";
		}

		// ����������
		@RequestMapping(value = "/mylist")
		public String getDonateList(Model m) {
			SecurityContext impl=SecurityContextHolder.getContext();	// ���ǿ��� spring security ������ ������
			String implstr=impl.getAuthentication().getName();	// security �������� ���ǿ� ����ִ� �α��� ���� �� ID ������
			MemberVO vo2=dao.memname(implstr);	// ID�� ���� ȸ������ ������ (ȸ�� ��ȣ, ȸ�� �̸�)
			
			List<ProjectVO> list = dao.myDonateProject(vo2.getMemberNo());
			System.out.println(list.size());
			m.addAttribute("list", list);
			return "mypage";
		}

		// �Ŀ��Ҷ� ������ �޼ҵ�
		@RequestMapping(value = "/donate")
		public String donateProject(ProjectDonateVO vo) {
			SecurityContext impl=SecurityContextHolder.getContext();	// ���ǿ��� spring security ������ ������
			String implstr=impl.getAuthentication().getName();	// security �������� ���ǿ� ����ִ� �α��� ���� �� ID ������
			MemberVO vo2=dao.memname(implstr);	// ID�� ���� ȸ������ ������ (ȸ�� ��ȣ, ȸ�� �̸�)
			int memno = vo2.getMemberNo();
			
			
			vo.setMemberNo(memno);
			
			dao.donate(vo); //projectDonate �� �߰�
			
			// �� ����
			Map<String, Integer> m = new HashMap<>();
			m.put("donateMoney", vo.getDonateMoney());
			m.put("memberNo", memno);
			
			dao.donateMoney(m); 
			
			return "redirect:/mylist";
		}

		// �Ŀ� ���
		@RequestMapping(value = "/cancle")
		public String cancle(int projectNo/*���Ŀ� ������Ʈ �����ؼ� �� ��츦 ����ؼ�*/) {
			SecurityContext impl=SecurityContextHolder.getContext();	// ���ǿ��� spring security ������ ������
			String implstr=impl.getAuthentication().getName();	// security �������� ���ǿ� ����ִ� �α��� ���� �� ID ������
			MemberVO vo2=dao.memname(implstr);	// ID�� ���� ȸ������ ������ (ȸ�� ��ȣ, ȸ�� �̸�)
			int memno = vo2.getMemberNo();
			
			// ������� ���� ��ȯ
			dao.returnMoney(memno);
			// �� ���� �� �Ŀ� �� ����
			dao.donateCancle(memno);
			return "redirect:/list"; // �ٽ� ����Ʈ ȭ������
		}
}