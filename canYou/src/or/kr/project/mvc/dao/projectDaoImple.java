package or.kr.project.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import or.kr.project.dto.CategoryVO;
import or.kr.project.dto.MemberVO;
import or.kr.project.dto.ProductVO;
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
}