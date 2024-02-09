package com.xadmin.feestype.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xadmin.feestype.bean.Type;
import com.xadmin.feestype.dao.TypeDao;


@WebServlet("/")
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TypeDao typeDao;
       
   
   
	public void init(ServletConfig config) throws ServletException {
		typeDao = new TypeDao();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		switch (action) {
	    case "/new":
	        showNewForm(request, response);
	        break;
	        
	    case "/insert":
	        try {
	            insertType(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        break;

	    case "/delete":
	        try {
	            deleteType(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        break;

	    case "/edit":
	        try {
	            showEditForm(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ServletException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        break;

	    case "/update":
	        try {
	            updateType(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        break;

	    default:
	        try {
	            listType(request, response);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ServletException e) {
	            e.printStackTrace();
	        }
	        break;
	}
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("fee-type-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertType(HttpServletRequest request, HttpServletResponse response) 
	        throws SQLException, IOException, ServletException {
	    String feeTypeId = request.getParameter("fee_type_id");
	    String feeTypeDesc = request.getParameter("fee_type_desc");
	    
	    HttpSession session = request.getSession();
	    RequestDispatcher dispatcher = null;
	    
	    if (feeTypeId == null || feeTypeId.equals("")) {
	        dispatcher = request.getRequestDispatcher("fee-type-form.jsp");
	        dispatcher.forward(request, response);
	    } else {
	        Type newType = new Type(feeTypeId, feeTypeDesc);
	        typeDao.insertType(newType);
	        response.sendRedirect("list");
	    }
	}


	
	private void deleteType(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String feeTypeId = request.getParameter("fee_type_id");
		typeDao.deleteType(feeTypeId);
		response.sendRedirect("list");

	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String feeTypeId = request.getParameter("fee_type_id");
		Type existingType;
		try {
			existingType= typeDao.selectType(feeTypeId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("fee-type-form.jsp");
		request.setAttribute("type", existingType);
		dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void updateType(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	    String feeTypeId = request.getParameter("fee_type_id");
	    String feeTypeDesc = request.getParameter("fee_type_desc");
	    
	    HttpSession session = request.getSession();
	    RequestDispatcher dispatcher = null;
	    
	    if (feeTypeId == null || feeTypeId.isEmpty()) {
	        dispatcher = request.getRequestDispatcher("fee-type-form.jsp");
	        dispatcher.forward(request, response);
	    } else {
	        Type book = new Type(feeTypeId, feeTypeDesc);
	        typeDao.updateType(book);

	        response.sendRedirect("list");
	    }
	}

	private void listType(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		try {
		List<Type> listType = typeDao.selectAllTypes();
		request.setAttribute("listType", listType);
		RequestDispatcher dispatcher = request.getRequestDispatcher("fee-type-list.jsp");
		dispatcher.forward(request, response);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

}

