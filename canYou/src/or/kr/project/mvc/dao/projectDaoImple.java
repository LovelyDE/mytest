package or.kr.project.mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import or.kr.project.dto.CategoryVO;
import or.kr.project.dto.MemberVO;
import or.kr.project.dto.ProductVO;
import or.kr.project.dto.ProjectDonateVO;
import or.kr.project.dto.ProjectVO;
import or.kr.project.dto.ReplyVO;
import or.kr.project.dto.SearchVO;


@Repository
public class projectDaoImple implements projectDao{
		@Autowired
		private SqlSessionTemplate ss;
		
		public List<CategoryVO> casel(){
			return ss.selectList("project.casel");		// 카테고리 이름 가져오는 sql문
		}
		
		public MemberVO memname(String s) {		// 회원이 누구인지 가져오기 위한 sql문(회원 아이디로 가져옴)
			return ss.selectOne("project.memname", s);
		}
		
		public MemberVO memname2(int i) {
			return ss.selectOne("project.memname2", i);		// 회원이 누구인지 가져오기 위한 sql문 (회원 번호로 가져옴)
		}
		
		public void proin(ProjectVO vo) {		// 프로젝트 insert문
			ss.insert("project.proin", vo);
		}
		
		public void prodin(ProductVO vo) {		// 상품 insert문
			ss.insert("project.prodin", vo);
		}
		
		public List<ProductVO> prodsel(String s){	// 상품 select문
			return ss.selectList("project.prodsel", s);
		}
		
		public ProjectVO modifyview(String s) {
			return ss.selectOne("project.modifyview", s);
		}

		public void modify(ProjectVO vo) {
			ss.update("project.modify", vo);
		}
		
		public int prodcost(int i) {
			return ss.selectOne("project.prodcost", i);
		}
		
		//프로젝트에 후원하는 메소드 1 (돈 차감)
		public void donateMoney(Map<String, Integer> m) {
			ss.update("project.donateMoney", m);
			//System.out.println("donateNo : "+m);
		}
		
		// 프로젝트에 후원을 하는 메소드2
		public void donate(ProjectDonateVO vo) {
			ss.insert("project.donate", vo);	// 후원
			ss.update("project.prodput", vo);	// 상품을 빼냄
			
			ss.update("project.projectFundCnt", vo);	// 프로젝트의 카운트 수를 하나 늘림
			ss.update("project.projectCurCost",vo);		// 프로젝트의 모인 금액을 올림
		}


		// 마이 페이지에서 자신이 투자한 프로젝트의 목록을 가져오는 메소드
		public List<HashMap> myDonateProject(int num /* num은 회원의 멤버 번호 */) {
			System.out.println("들어온 넘버 값:" + num);
			List<HashMap> list = ss.selectList("project.mydonate", num);
			return list;
		}

		// 후원 취소1 (돈 반환)
		public void returnMoney(ProjectDonateVO vo) {
			Map<String, Integer> m = new HashMap<>();
			// 돈을 반환하기 위해 취소할 프로젝트에 투자한 돈을 가져옴
			int total=ss.selectOne("project.mydonatelist", vo);
			
			m.put("donateMoney", total);
			m.put("memberNo", vo.getMemberNo());
			
			ss.update("project.returnMoney", m);	// 반환해서 회원의 돈으로 추가시켜줌
		}

		// 후원 취소2 (행 삭제)
		public void donateCancle(ProjectDonateVO vo) {
			ss.delete("project.cancle", vo);	// 후원 취소
		}
		
		public ProjectVO projectlist(String num) {
			return ss.selectOne("project.list", num);
		}

		public List<CategoryVO> categoryLookAround() {
			return ss.selectList("project.lookaround");
		}
		
		// 프로젝트 댓글 등록

		public void replyInsert(ReplyVO vo) {
			ss.insert("project.replyInsert", vo);
		}

		// 댓글 리스트 보여줌

		public List<ReplyVO> replyList(String num) {
			return ss.selectList("project.replyList", num);
			
		}
		
		// 모든 프로젝트 둘러보기 -페이징 처리
		public List<ProjectVO> projectALLlist(SearchVO vo) {
			return ss.selectList("paging.AllList", vo);
		}
		// 모든 프로젝트 총 개수
		public int getTotalCount() {
			return ss.selectOne("paging.listTotal");
			
		}
		
		//마이페이지 - 내가만든 프로젝트 리스트
		public List<ProjectVO> myProjectlist(int num) {
			return ss.selectList("project.myProjectlist", num);
		}
		
		// 마이페이지-개인정보 수정
		public void editMyInfo(MemberVO vo) {
			ss.update("project.editinfo",vo);
		}
		
		//카테고리별 프로젝트의 수 구하기
		public int getCategoryCount(int categoryNo) {
			return ss.selectOne("paging.listCategory",categoryNo);
		}

		//카테고리별 프로젝트 보기
		public List<ProjectVO> lookCategoryProject(Map<String, String> categoryList) {
			System.out.println("-----------------------------");
			System.out.println(categoryList.get("categoryNo"));
			System.out.println(categoryList.get("begin"));
			System.out.println(categoryList.get("end"));
			System.out.println("-----------------------------");
			
			List<ProjectVO> list = ss.selectList("paging.paginglist",categoryList);
			System.out.println("list size : "+list.size());
			
			return list;
		}
}