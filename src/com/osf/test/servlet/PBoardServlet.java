package com.osf.test.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.osf.test.service.PBoardService;
import com.osf.test.service.impl.PBoardServiceImpl;

public class PBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String savePath = "D:\\study\\workspace\\osf-jsp\\WebContent\\upload"; // 이렇게 쓰면 지금은 편하지만 나중에 개발 핼때는
	private PBoardService pbs = new PBoardServiceImpl(); // os마다 계속
	// 변경해야 한다 .

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.replace("/pboard/", "");
		if ("list".equals(uri)) {
			request.setAttribute("pBoardList", pbs.selectPBoardList());
			RequestDispatcher rd = request.getRequestDispatcher("/views/photo_board/list.jsp");
			rd.forward(request, response);
			return;
		} else {
			try {
				int pbNum = Integer.parseInt(uri);
				request.setAttribute("pBoard", pbs.selectPBoard(pbNum));
				RequestDispatcher rd = request.getRequestDispatcher("/views/photo_board/view.jsp");
				rd.forward(request, response);
				return;
			} catch (NumberFormatException e) {
				throw new ServletException("상세조회는 번호조회만 가능합니다 .");
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.replace("/pboard/", "");
		if ("insert".equals(uri)) {
			DiskFileItemFactory dfifactory = new DiskFileItemFactory();// 톰캣 아님 commons
			String tmpPath = System.getProperty("java.io.tmpdir");
			File tmpFile = new File(tmpPath);
			dfifactory.setRepository(tmpFile);
			dfifactory.setSizeThreshold(10 * 1024 * 1024); // 임시파일 용량 설정

			ServletFileUpload sfu = new ServletFileUpload(dfifactory);// 톰캣 아님 commons
			sfu.setSizeMax(20 * 1024 * 1024); // 파일 전체 용량
			sfu.setFileSizeMax(20 * 1024 * 1024); // 파일 하나의 용량
			try {
				List<FileItem> fileList = sfu.parseRequest(request); // parse 분석하다. 파일과 다른 것을 나누어 준다.
				Map<String, String> pBoard = new HashMap<>();
				for (int i = 0; i < fileList.size(); i++) {
					FileItem fi = fileList.get(i);
					if (fi.isFormField()) {// 파일이 아닌것
						pBoard.put(fi.getFieldName(), fi.getString("UTF-8"));

					} else { // 파일 일때
						String rfileName = fi.getName();
						String extName = rfileName.substring(rfileName.lastIndexOf(".") + 1);
						String fileName = System.currentTimeMillis() + "";
						File saveFile = new File(savePath + "\\" + fileName + "." + extName); // 이거보다 다른것을 많이 쓰만지 "\\"
																								// 이해부족으로 이걸함.
						pBoard.put("pb_file_path", rfileName);
						pBoard.put("pb_real_path", "/upload/" + fileName + "." + extName);
						fi.write(saveFile);
					}

				}
				if ((pbs.insertPBoard(pBoard)) == 1) {
					request.setAttribute("msg", "집중해라!");
					request.setAttribute("url", "/views/photo_board/insert.jsp");
					RequestDispatcher rd = request.getRequestDispatcher("/views/result.jsp");
					rd.forward(request, response);
					return;
				} else {

				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("update".equals(uri)) {

		} else if ("delete".equals(uri)) {

		} else {

		}
	}

	public static void main(String[] args) {

	}

}
