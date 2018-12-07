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
		
		public MemberVO memname2(int i) {
			return ss.selectOne("project.memname2", i);		// ȸ���� �������� �������� ���� sql�� (ȸ�� ��ȣ�� ������)
		}
		
		public void proin(ProjectVO vo) {		// ������Ʈ insert��
			ss.insert("project.proin", vo);
		}
		
		public void prodin(ProductVO vo) {		// ��ǰ insert��
			ss.insert("project.prodin", vo);
		}
		
		public List<ProductVO> prodsel(String s){	// ��ǰ select��
			return ss.selectList("project.prodsel", s);
		}
		
		public ProjectVO modifyview(String s) {
			return ss.selectOne("project.modifyview", s);
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
		
		public ProjectVO projectlist(String num) {
			return ss.selectOne("project.list", num);
		}

		public List<CategoryVO> categoryLookAround() {
			return ss.selectList("project.lookaround");
		}
		
		// ������Ʈ ��� ���

		public void replyInsert(ReplyVO vo) {
			ss.insert("project.replyInsert", vo);
		}

		// ��� ����Ʈ ������

		public List<ReplyVO> replyList(String num) {
			return ss.selectList("project.replyList", num);
			
		}
		
		// ��� ������Ʈ ����Ʈ
		public List<ProjectVO> projectALLlist() {
			
			return ss.selectList("project.AllList");
		}
}