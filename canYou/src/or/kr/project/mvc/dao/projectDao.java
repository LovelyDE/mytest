package or.kr.project.mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import or.kr.project.dto.CategoryVO;
import or.kr.project.dto.MemberVO;
import or.kr.project.dto.ProductVO;
import or.kr.project.dto.ProjectDonateVO;
import or.kr.project.dto.ProjectVO;
import or.kr.project.dto.ReplyVO;
import or.kr.project.dto.SearchVO;

public interface projectDao {
	public List<CategoryVO> casel();
	public MemberVO memname(String s);
	public MemberVO memname2(int i);
	public void proin(ProjectVO vo);
	public void prodin(ProductVO vo);
	public List<ProductVO> prodsel(String s);
	public ProjectVO modifyview(String s);
	public void modify(ProjectVO vo);
	public int prodcost(int i);
	public void donateMoney(Map<String, Integer> m);
	public void donate(ProjectDonateVO vo);
	public List<HashMap> myDonateProject(int num /* num은 회원의 멤버 번호 */);
	public void returnMoney(ProjectDonateVO vo);
	public void donateCancle(ProjectDonateVO vo);
	public ProjectVO projectlist(String num);
	public List<CategoryVO> categoryLookAround();
	public void replyInsert(ReplyVO vo);
	public List<ReplyVO> replyList(String num);
	public List<ProjectVO> projectALLlist(SearchVO vo);
	public int getTotalCount();
	public List<ProjectVO> myProjectlist(int num);
	public void editMyInfo(MemberVO vo);
	public int getCategoryCount(int categoryNo);
	public List<ProjectVO> lookCategoryProject(Map<String, String> categoryList);
}
