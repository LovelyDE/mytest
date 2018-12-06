package or.kr.project.mvc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import or.kr.project.dto.ProjectVO;
import or.kr.project.mvc.dao.projectDaoImple;

@Controller
public class ProjectController {
	@Autowired
	private projectDaoImple dao;
	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, Model model) {
		return "login";
	}
	
	@RequestMapping(value="/denied")
	public String denied() {
		return "denied";
	}
	
	@RequestMapping(value="/proupform")		// ������Ʈ ���ε� �� ������
	public ModelAndView proupform(HttpServletRequest request){
		List<CategoryVO> category=dao.casel();		// ������Ʈ ���ε忡 �ʿ��� ī�װ� ����� ������
		
		SecurityContextImpl impl=(SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		String implstr=impl.getAuthentication().getName();
		MemberVO vo=dao.memname(implstr);
		String name=vo.getMemberName();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("proupform");
		mav.addObject("category", category);
		mav.addObject("memberName", name);
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
		
		SecurityContextImpl impl=(SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		String implstr=impl.getAuthentication().getName();
		MemberVO vo2=dao.memname(implstr);
		vo.setMemberNo(vo2.getMemberNo());
		
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
}