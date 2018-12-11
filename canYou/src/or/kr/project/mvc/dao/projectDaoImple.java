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
		
		@Override
		public List<CategoryVO> casel(){
			return ss.selectList("project.casel");		// ī�װ� �̸� �������� sql��
		}
		
		@Override
		public MemberVO memname(String s) {		// ȸ���� �������� �������� ���� sql��(ȸ�� ���̵�� ������)
			return ss.selectOne("project.memname", s);
		}
		
		@Override
		public MemberVO memname2(int i) {
			return ss.selectOne("project.memname2", i);		// ȸ���� �������� �������� ���� sql�� (ȸ�� ��ȣ�� ������)
		}
		
		@Override
		public void proin(ProjectVO vo) {		// ������Ʈ insert��
			ss.insert("project.proin", vo);
		}
		
		@Override
		public void prodin(ProductVO vo) {		// ��ǰ insert��
			ss.insert("project.prodin", vo);
		}
		
		@Override
		public List<ProductVO> prodsel(String s){	// ��ǰ select��
			return ss.selectList("project.prodsel", s);
		}
		
		@Override
		public ProjectVO modifyview(String s) {
			return ss.selectOne("project.modifyview", s);
		}

		@Override
		public void modify(ProjectVO vo) {
			ss.update("project.modify", vo);
		}
		
		@Override
		public int prodcost(int i) {
			return ss.selectOne("project.prodcost", i);
		}
		
		//������Ʈ�� �Ŀ��ϴ� �޼ҵ� 1 (�� ����)
		@Override
		public void donateMoney(Map<String, Integer> m) {
			ss.update("project.donateMoney", m);
			//System.out.println("donateNo : "+m);
		}
		
		// ������Ʈ�� �Ŀ��� �ϴ� �޼ҵ�2
		@Override
		public void donate(ProjectDonateVO vo) {
			ss.insert("project.donate", vo);	// �Ŀ�
			ss.update("project.prodput", vo);	// ��ǰ�� ����
			
			ss.update("project.projectFundCnt", vo);	// ������Ʈ�� ī��Ʈ ���� �ϳ� �ø�
			ss.update("project.projectCurCost",vo);		// ������Ʈ�� ���� �ݾ��� �ø�
		}


		// ���� ���������� �ڽ��� ������ ������Ʈ�� ����� �������� �޼ҵ�
		@Override
		public List<HashMap> myDonateProject(int num /* num�� ȸ���� ��� ��ȣ */) {
			System.out.println("���� �ѹ� ��:" + num);
			List<HashMap> list = ss.selectList("project.mydonate", num);
			return list;
		}

		// �Ŀ� ���1 (�� ��ȯ)
		@Override
		public void returnMoney(ProjectDonateVO vo) {
			Map<String, Integer> m = new HashMap<>();
			// ���� ��ȯ�ϱ� ���� ����� ������Ʈ�� ������ ���� ������
			int total=ss.selectOne("project.mydonatelist", vo);
			
			m.put("donateMoney", total);
			m.put("memberNo", vo.getMemberNo());
			
			ss.update("project.returnMoney", m);	// ��ȯ�ؼ� ȸ���� ������ �߰�������
		}

		// �Ŀ� ���2 (�� ����)
		@Override
		public void donateCancle(ProjectDonateVO vo) {
			ss.delete("project.cancle", vo);	// �Ŀ� ���
		}
		
		@Override
		public ProjectVO projectlist(String num) {
			return ss.selectOne("project.list", num);
		}

		@Override
		public List<CategoryVO> categoryLookAround() {
			return ss.selectList("project.lookaround");
		}
		
		// ������Ʈ ��� ���

		@Override
		public void replyInsert(ReplyVO vo) {
			ss.insert("project.replyInsert", vo);
		}

		// ��� ����Ʈ ������
		@Override
		public List<ReplyVO> replyList(String num) {
			return ss.selectList("project.replyList", num);
			
		}
		
		// ��� ������Ʈ �ѷ����� -����¡ ó��
		@Override
		public List<ProjectVO> projectALLlist(SearchVO vo) {
			return ss.selectList("paging.AllList", vo);
		}
		// ��� ������Ʈ �� ����
		@Override
		public int getTotalCount() {
			return ss.selectOne("paging.listTotal");
			
		}
		
		//���������� - �������� ������Ʈ ����Ʈ
		@Override
		public List<ProjectVO> myProjectlist(int num) {
			return ss.selectList("project.myProjectlist", num);
		}
		
		// ����������-�������� ����
		@Override
		public void editMyInfo(MemberVO vo) {
			ss.update("project.editinfo",vo);
		}
		
		//ī�װ��� ������Ʈ�� �� ���ϱ�
		@Override
		public int getCategoryCount(int categoryNo) {
			return ss.selectOne("paging.listCategory",categoryNo);
		}

		//ī�װ��� ������Ʈ ����
		@Override
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