package fr.tbr.iam.web.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.tbr.iam.log.IAMLogger;
import fr.tbr.iam.log.impl.IAMLogManager;
import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.exception.DAOSearchException;
import fr.tbr.iamcore.exception.DAOUpdateException;
import fr.tbr.iamcore.service.dao.DAODeleteException;
import fr.tbr.iamcore.service.dao.IdentityDAOInterface;

@WebServlet(name="ModifyDelete", urlPatterns="/ModifyDelete")


public class ModifyDelete extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;
	
	IAMLogger logger = IAMLogManager.getIAMLogger(ModifyDelete.class);
	
	@Inject
	IdentityDAOInterface dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String value=request.getParameter("modification");
		long a=Long.valueOf(request.getParameter("selection"));
		if(value.equals("modify")){
			
			try {
				Collection<Identity> idList = dao.getById (a);
					request.getSession().setAttribute("identity", idList);
					request.getSession().setAttribute("id", request.getParameter("selection"));
					request.getRequestDispatcher("create.jsp").forward(request, response);
					
			} catch (DAOSearchException e) {
				e.printStackTrace();
			}
			
		}else if(value.equals("delete")){
			try {
				Collection<Identity> idList = dao.getById (a);
				for(Identity id : idList){
					dao.delete(id);
					response.sendRedirect("search.jsp");
				}
			} catch (DAOSearchException | DAODeleteException e) {
				e.printStackTrace();
			}
			
			
		}else{
			response.sendRedirect("search.jsp");
			
		}
		
	}

}