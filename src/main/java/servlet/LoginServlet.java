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

@WebServlet({ "/login", "/logout" })
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDao dao = new UserDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		if (path.equals("/logout")) {
			HttpSession session = req.getSession();
			session.removeAttribute("user"); // xoa user khoi session
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
			return;
		}
		// Xu li event tai trang login
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		try {
			User user = dao.findByEmail(email);

			// Validate: Kiểm tra tài khoản
			if (user == null) {
				req.setAttribute("message", "Tài khoản không tồn tại!");
				// Giữ lại email người dùng đã nhập để họ không phải gõ lại
				req.setAttribute("email", email);
				req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
				return;
			}

			// Validate: Kiểm tra mật khẩu
			if (!user.getPassword().equals(password)) {
				req.setAttribute("message", "Sai mật khẩu!");
				req.setAttribute("email", email); // Giữ lại email
				req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
				return;
			}

			// Đăng nhập thành công
			HttpSession session = req.getSession();
			session.setAttribute("user", user);

			// Dùng sendRedirect để tránh lỗi "Confirm Form Resubmission" khi F5
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
			return;

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", "Lỗi hệ thống: " + e.getMessage());
			req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
		}
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}
}
