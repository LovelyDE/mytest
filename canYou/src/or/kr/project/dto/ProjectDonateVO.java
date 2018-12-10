package or.kr.project.dto;

public class ProjectDonateVO {
	int donateNo, memberNo, projectNo, productNo, donateMoney;
	String payOption, donateDay;
	public int getDonateNo() {
		return donateNo;
	}
	public void setDonateNo(int donateNo) {
		this.donateNo = donateNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(int projectNo) {
		this.projectNo = projectNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getDonateMoney() {
		return donateMoney;
	}
	public void setDonateMoney(int donateMoney) {
		this.donateMoney = donateMoney;
	}
	public String getPayOption() {
		return payOption;
	}
	public void setPayOption(String payOption) {
		this.payOption = payOption;
	}
	
}
