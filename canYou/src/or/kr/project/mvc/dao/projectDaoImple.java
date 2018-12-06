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
		
		public List<ProjectVO> projectlist() {
			return ss.selectList("project.list");
		}

		public void modify(ProjectVO vo) {
			ss.update("project.modify", vo);
		}
		
		//������Ʈ�� �Ŀ��ϴ� �޼ҵ� 1 (�� ����)
		public void donateMoney(Map<String, Integer> m) {
			ss.update("project.donateMoney", m);
			//System.out.println("donateNo : "+m);
		}
		
		// ������Ʈ�� �Ŀ��� �ϴ� �޼ҵ�2
		public void donate(ProjectDonateVO vo) {
			ss.insert("project.donate", vo);
		}


		// ���� ���������� �ڽ��� ������ ������Ʈ�� ����� �������� �޼ҵ�
		public List<ProjectVO> myDonateProject(int num /* num�� ȸ���� ��� ��ȣ */) {
			System.out.println("���� �ѹ� ��:" + num);
			List<ProjectVO> list = ss.selectList("project.mydonate", num);
			return list;
		}

		// �Ŀ� ���1 (�� ��ȯ) ����� �ϰ� ����ؼ� �ϰ��� ��ȯ
		public void returnMoney(int memberNo /*���ǰ�*/) {
			Map<String, Integer> m = new HashMap<>();
			// ���� ��ȯ �ϱ� ���� �ϰ� ����ؼ� ������ �ݾ��� ã�� ���� ����
			int total=0;
			System.out.println("total ������ ��");
			//�ڽ��� �Ŀ��� ����� ��ȯ ����Ʈ
			List<ProjectDonateVO> list = ss.selectList("project.mydonatelist",memberNo);
			System.out.println("list ���� : "+list.size());
			//�ڽ��� �Ŀ��� ������Ʈ�� �Ŀ��� ��� ��� ���� �� ���� �Ѿ� ���
			for (ProjectDonateVO money : list) {
				total += money.getDonateMoney();
			}
			
			System.out.println("total : " + total);
			
			m.put("donateMoney", total);
			m.put("memberNo", memberNo);
			
			ss.update("project.returnMoney", m);
		}

		// �Ŀ� ���2 (�� ����)
		public void donateCancle(int donateNo) {
			ss.delete("project.cancle", donateNo);
		}
}