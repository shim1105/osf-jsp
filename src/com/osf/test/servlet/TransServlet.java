package com.osf.test.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osf.test.service.CommonService;
import com.osf.test.service.TransService;
import com.osf.test.service.impl.CommonCodeService;
import com.osf.test.service.impl.TransServiceImpl;

public class TransServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CommonService ccs= new CommonCodeService();  
    private TransService ts= new TransServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/tranpser/trans.jsp");
		request.setAttribute("ccList", ccs.selectCommonCodeList("trans"));
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String source = request.getParameter("source"); // 원래는 여기도 해줘어야 한다.
		String target= request.getParameter("target");
		String text = request.getParameter("text");
		request.setAttribute("rMap", ts.transperText(source, target, text));
		doGet(request, response);
	}

}
