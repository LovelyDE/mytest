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
		
		public void proin(ProjectVO vo) {		// 프로젝트 insert문
			ss.insert("project.proin", vo);
		}
		
		public void prodin(ProductVO vo) {		// 상품 insert문
			ss.insert("project.prodin", vo);
		}
		
		public List<ProjectVO> projectlist() {
			return ss.selectList("project.list");
		}

		public void modify(ProjectVO vo) {
			ss.update("project.modify", vo);
		}
		
		//프로젝트에 후원하는 메소드 1 (돈 차감)
		public void donateMoney(Map<String, Integer> m) {
			ss.update("project.donateMoney", m);
			//System.out.println("donateNo : "+m);
		}
		
		// 프로젝트에 후원을 하는 메소드2
		public void donate(ProjectDonateVO vo) {
			ss.insert("project.donate", vo);
		}


		// 마이 페이지에서 자신이 투자한 프로젝트의 목록을 가져오는 메소드
		public List<ProjectVO> myDonateProject(int num /* num은 회원의 멤버 번호 */) {
			System.out.println("들어온 넘버 값:" + num);
			List<ProjectVO> list = ss.selectList("project.mydonate", num);
			return list;
		}

		// 후원 취소1 (돈 반환) 현재는 일괄 취소해서 일괄로 반환
		public void returnMoney(int memberNo /*임의값*/) {
			Map<String, Integer> m = new HashMap<>();
			// 돈을 반환 하기 전에 일괄 취소해서 나오는 금액을 찾기 위한 변수
			int total=0;
			System.out.println("total 계산까지 옴");
			//자신이 후원한 목록을 반환 리스트
			List<ProjectDonateVO> list = ss.selectList("project.mydonatelist",memberNo);
			System.out.println("list 길이 : "+list.size());
			//자신이 후원한 프로젝트의 후원을 모두 취소 했을 때 나올 총액 계산
			for (ProjectDonateVO money : list) {
				total += money.getDonateMoney();
			}
			
			System.out.println("total : " + total);
			
			m.put("donateMoney", total);
			m.put("memberNo", memberNo);
			
			ss.update("project.returnMoney", m);
		}

		// 후원 취소2 (행 삭제)
		public void donateCancle(int donateNo) {
			ss.delete("project.cancle", donateNo);
		}
}