package or.kr.project.word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hwpf.converter.AbstractWordConverter;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import or.kr.project.dto.MemberVO;
import or.kr.project.dto.ProductVO;
import or.kr.project.dto.ProjectDonateVO;
import or.kr.project.dto.ProjectVO;

public class WordBuilder{
	// 주석 부분 vo 해야함
	public void wordCD(/*Map<String, Object> map, */HttpServletRequest req, HttpServletResponse resp) throws Exception {
		/*// 프로젝트 진행자의 정보를 담은 vo 객체 받아오기
		MemberVO mvo = (MemberVO)map.get("member");
		ProjectVO pvo = (ProjectVO)map.get("project");
		ProjectDonateVO dvo = (ProjectDonateVO)map.get("projectDonate");
		@SuppressWarnings("unchecked")
		List<ProductVO> list = (List<ProductVO>)map.get("productList");*/
		
		// 빈 문서 만들기
		XWPFDocument document = new XWPFDocument();
		
		// Table 생성 문서 내에서의 position은 0
		XWPFTable table = document.createTable(16,3);

		// 셀병합 수직, 수평
		// 첫번째 행 수평적 셀 병합
		mergeCellHorizontally(table, 0, 0, 2);
		// 두번째 행 수평적 셀 병합
		mergeCellHorizontally(table, 1, 0, 2);
		// 여덟번째 행 수평적 셀 병합
		mergeCellHorizontally(table, 7, 0, 2);
		// 열네번째행 수평적 셀 병합
		mergeCellHorizontally(table, 13, 0, 2);
		// 열다섯번째 행 수평적 셀 병합
		mergeCellHorizontally(table, 14, 0, 2);
		
		// Table 첫번째 행
		XWPFTableRow firstRow = table.getRow(0);
		firstRow.getCell(0).setColor("3366FF");
		firstRow.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);

		XWPFParagraph paragraph = firstRow.getCell(0).getParagraphs().get(0);
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		
		XWPFRun paragraphRunOne = paragraph.createRun();
		paragraphRunOne.setBold(true);
		paragraphRunOne.setFontSize(16);
		paragraphRunOne.setText("프로젝트 보고서");

		// Table 두번째 행
		XWPFTableRow secondRow = table.getRow(1);
		secondRow.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
		
		paragraph = secondRow.getCell(0).getParagraphs().get(0);
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		
		paragraphRunOne = paragraph.createRun();
		paragraphRunOne.setBold(true);
		paragraphRunOne.setFontSize(11);
		paragraphRunOne.setText("진행자 정보");
		
		// Table 세번째 행
		XWPFTableRow thirdRow = table.getRow(2);
		thirdRow.getCell(0).setText("프로필 이미지");
		thirdRow.getCell(1).setText("진행자 이름");
		thirdRow.getCell(2).setText("조현무"/*mvo.getMemberName()*/);
		
		XWPFTableRow fourthRow = table.getRow(3);
		fourthRow.getCell(1).setText("진행자 전화번호");
		fourthRow.getCell(2).setText("010-7156-1122" /*mvo.getMemberPhone()*/);
		
		XWPFTableRow fifthRow = table.getRow(4);
		fifthRow.getCell(1).setText("진행자 이메일");
		fifthRow.getCell(2).setText("chomu2004@naver,com" /*mvo.getMemberEmail()*/);
		
		XWPFTableRow sixthRow = table.getRow(5);
		sixthRow.getCell(1).setText("진행자 성별");
		sixthRow.getCell(2).setText("남" /*mvo.getMemberGender()*/);
		
		XWPFTableRow seventhRow = table.getRow(6);
		seventhRow.getCell(1).setText("진행자 나이");
		seventhRow.getCell(2).setText("25" /*mvo.getMemberAge()*/);
		
		// 프로필 이미지 부분 셀을 합쳐서 하나로
		mergeCellVertically(table, 0, 2, 6);
		
		for(int i=2;i<=6;i++) {
			table.getRow(i).getCell(1).setColor("6699FF");
		}
		
		// 프로젝트 정보 부분
		XWPFTableRow eighthRow = table.getRow(7);
		eighthRow.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
		
		paragraph = eighthRow.getCell(0).getParagraphs().get(0);
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		
		paragraphRunOne = paragraph.createRun();
		paragraphRunOne.setBold(true);
		paragraphRunOne.setFontSize(11);
		paragraphRunOne.setText("프로젝트 정보");
		
		XWPFTableRow ninethRow = table.getRow(8);
		ninethRow.getCell(0).setText("프로젝트 대표 이미지");
		ninethRow.getCell(1).setText("프로젝트 등급");
		ninethRow.getCell(2).setText("A" /*pvo.getProjectStep()*/);
		
		XWPFTableRow tenRow = table.getRow(9);
		tenRow.getCell(1).setText("프로젝트 이름");
		tenRow.getCell(2).setText("맛있는 떡볶이" /*pvo.getProjectName()*/);
		
		XWPFTableRow elevenRow = table.getRow(10);
		elevenRow.getCell(1).setText("프로젝트 시작 날짜");
		elevenRow.getCell(2).setText("18.12.07" /*pvo.getProjectStartDate()*/);
		
		XWPFTableRow twelveRow = table.getRow(11);
		twelveRow.getCell(1).setText("프로젝트 마감 날짜");
		twelveRow.getCell(2).setText("18.12.28" /*pvo.getProjectEndDate()*/);
		
		XWPFTableRow thirteenRow = table.getRow(12);
		thirteenRow.getCell(1).setText("프로젝트 활동 지역");
		thirteenRow.getCell(2).setText("가산 디지털 단지" /*이거안정함*/);
		
		// 프로젝트 프로필 이미지 병합 부분
		mergeCellVertically(table, 0, 8, 12);
		
		for(int i=8;i<=12;i++) {
			table.getRow(i).getCell(1).setColor("6699FF");
		}
		
		// 프로젝트 상세정보, 스토리
		XWPFTableRow fourteenRow = table.getRow(13);
		fourteenRow.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
		
		paragraph = fourteenRow.getCell(0).getParagraphs().get(0);
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		
		paragraphRunOne = paragraph.createRun();
		paragraphRunOne.setBold(true);
		paragraphRunOne.setFontSize(11);
		paragraphRunOne.setText("프로젝트 상세정보 및 스토리");
		
		XWPFTableRow fifteenRow = table.getRow(14);
		fifteenRow.getCell(0).setText("프로젝트 스토리 뭐시기뭐시기" /*pvo.getProjectStory()*/);
				
		// 상품 상세 설명
		XWPFTableRow sixteenRow = table.getRow(15);
		sixteenRow.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
		
		paragraph = sixteenRow.getCell(0).getParagraphs().get(0);
		paragraph.setAlignment(ParagraphAlignment.CENTER);
		
		paragraphRunOne = paragraph.createRun();
		paragraphRunOne.setBold(true);
		paragraphRunOne.setFontSize(11);
		paragraphRunOne.setText("프로젝트 상품");
		
		mergeCellHorizontally(table, 15, 0, 2);
		
		/*// 상품 관련 표 만들기
		for (ProductVO productVO : list) {
			// 위쪽의 프로젝트 상품에 관한 정보
			XWPFTableRow list_Row = table.getRow(15);
			list_Row.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
			
			paragraph = list_Row.getCell(0).getParagraphs().get(0);
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			
			paragraphRunOne = paragraph.createRun();
			paragraphRunOne.setBold(true);
			paragraphRunOne.setFontSize(11);
			paragraphRunOne.setText("프로젝트 상품");
			
			// 상품 설명 행
			list_Row = table.createRow();
			list_Row.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
			
			paragraph = list_Row.getCell(0).getParagraphs().get(0);
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			paragraphRunOne = paragraph.createRun();
			paragraphRunOne.setFontSize(10);
			paragraphRunOne.setText("상품 개수"); // 1열 완료
			
			paragraph = list_Row.createCell().getParagraphs().get(0);
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			paragraphRunOne = paragraph.createRun();
			paragraphRunOne.setFontSize(10);
			paragraphRunOne.setText(Integer.toString(productVO.getProductCnt())); // 1열 완료
			
			// 상품 설명 행 3
			paragraph = list_Row.createCell().getParagraphs().get(0);
			
		}*/
		
		System.out.println("create_table 생성!");
		
		// 파일 전송을 위한 마임타입과 헤더 설정
		resp.setContentType("Application/Msword");
		resp.setHeader("Content-Disposition",
				"attachment; filename=create_table.docx");
		
		// 요청한 브라우저와의 아웃풋 스트림 연결
		OutputStream outStream = resp.getOutputStream();
		
		document.write(outStream);
		outStream.close();
		System.out.println("create_table 전송!");
	}
	//세로 부분의 셀을 합치는 함수
		private static void mergeCellVertically(XWPFTable table, int col, int fromRow, int toRow){
		    for(int rowIndex = fromRow; rowIndex <= toRow; rowIndex++){
		        XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
		        if(rowIndex == fromRow){
		        	// 합치기 시작하는 처음 세로셀은 RESTART로 설정
		            cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
		        }else{
		        	// 합치기 시작하는 처음 세로셀은 CONTINUE로 설정
		            cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
		        }
		    }
		}
		
		//가로 부분의 셀을 합치는 함수
		private static void mergeCellHorizontally(XWPFTable table, int row, int fromCol, int toCol){
		    for(int colIndex = fromCol; colIndex <= toCol; colIndex++){
		        XWPFTableCell cell = table.getRow(row).getCell(colIndex);
		        if(colIndex == fromCol){
		            // 합치기 시작하는 처음 가로셀은 RESTART로 설정
		            cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
		        }else{
		        	// 합쳐지는 가로 셀은 CONTINUE로 설정
		            cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
		        }
		    }
		}
		
		private static void create_Product_Row(XWPFTable table, XWPFParagraph paragraph, 
				XWPFRun paragraphRunOne, ProductVO productVO) {
			// 상품 설명 행
			XWPFTableRow list_Row = table.createRow();
			list_Row.getCell(0).setVerticalAlignment(XWPFVertAlign.CENTER);
			
			paragraph = list_Row.getCell(0).getParagraphs().get(0);
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			paragraphRunOne = paragraph.createRun();
			paragraphRunOne.setFontSize(10);
			paragraphRunOne.setText("상품 개수"); // 1열 완료
			
			paragraph = list_Row.createCell().getParagraphs().get(0);
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			paragraphRunOne = paragraph.createRun();
			paragraphRunOne.setFontSize(10);
			paragraphRunOne.setText(Integer.toString(productVO.getProductCnt())); // 1열 완료			
		}
}


