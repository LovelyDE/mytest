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
			return ss.selectList("project.casel");		// ī�װ� �̸� �������� sql��
		}
		
		public MemberVO memname(String s) {		// ȸ���� �������� �������� ���� sql��(ȸ�� ���̵�� ������)
			return ss.selectOne("project.memname", s);
		}
		
		public void proin(ProjectVO vo) {		// ������Ʈ insert��
			ss.insert("project.proin", vo);
		}
		
		public void prodin(ProductVO vo) {		// ��ǰ insert��
			ss.insert("project.prodin", vo);
		}
}