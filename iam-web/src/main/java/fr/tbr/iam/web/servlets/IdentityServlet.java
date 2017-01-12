package fr.tbr.iam.web.servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.tbr.iam.log.IAMLogger;
import fr.tbr.iam.log.impl.IAMLogManager;
import fr.tbr.iamcore.datamodel.Identity;
import fr.tbr.iamcore.exception.DAOSaveException;

import fr.tbr.iamcore.exception.DAOUpdateException;
import fr.tbr.iamcore.service.dao.IdentityDAOInterface;

/**
 * Servlet implementation class Login
 */

@WebServlet(name = "IdentityServlet", urlPatterns = "/IdAction")
public class IdentityServlet extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;

	
	@Inject
	IdentityDAOInterface dao;
	
	IAMLogger logger = IAMLogManager.getIAMLogger(IdentityServlet.class);


	/**
	 * Default constructor.
	 */
	public IdentityServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id =  (String) request.getSession().getAttribute("id");
		request.getSession().removeAttribute("id");
		
		String displayName = request.getParameter("displayName");
		String email = request.getParameter("email");
		String birthDate = request.getParameter("birthDate");
		
		DateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
		Date dob = null;
		
		try {
			dob = new java.sql.Date(dFormat.parse(birthDate).getTime());
		} catch(ParseException e1) {
			e1.printStackTrace();}
		
		String uid = request.getParameter("uid");
		Identity identity = new Identity();
		
		if(id !=null){
			identity.setDisplayName(displayName);
			identity.setEmail(email);
			identity.setBirthDate(dob);
			identity.setId(Long.valueOf(id));
			identity.setUid(uid);}
		
		if(id != null){
				try{
					dao.update(identity);}
			catch(DAOUpdateException e){
				e.printStackTrace();
			}
		}
		else{
			try{
					dao.save(new Identity(displayName, email, uid, dob));}
			catch(DAOSaveException e){
				e.printStackTrace();
				}
			}
		
		
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());

	}

}
