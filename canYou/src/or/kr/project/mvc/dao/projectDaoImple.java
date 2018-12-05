package or.kr.project.mvc.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import or.kr.project.dto.CategoryVO;
import or.kr.project.dto.ProjectVO;


@Repository
public class projectDaoImple implements projectDao{
		@Autowired
		private SqlSessionTemplate ss;
		
		public List<CategoryVO> casel(){
			return ss.selectList("project.casel");
		}
		
		public String memname(int num) {
			return ss.selectOne("project.memname", num);
		}
		
		public void proin(ProjectVO vo) {
			ss.insert("project.proin", vo);
		}
}