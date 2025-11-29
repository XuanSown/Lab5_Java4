package servlet;

import java.io.IOException;

import dao.UserDao;
import dao.impl.UserDAOImpl;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDao dao = new UserDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		try {
			User user = dao.findByEmail(email);
			if (user == null) {
				req.setAttribute("message", "Tài khoản không tồn tại");
				req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
				return;
			} else if (!user.getPassword().equals(password)) {
				req.setAttribute("message", "Sai mật khẩu");
				req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
				return;
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
//				req.setAttribute("message", "Đăng nhập thành công!");
//				resp.sendRedirect(req.getContextPath() + "/index");
				req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("message", "Lỗi hệ thống: " + e.getMessage());
		}
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}
}
