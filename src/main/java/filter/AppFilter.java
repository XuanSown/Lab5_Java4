package filter;

import java.io.IOException;
import java.util.Date;

import dao.LogDAO;
import dao.impl.LogDAOImpl;
import entity.Log;
import entity.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//lọc tất cả các request
@WebFilter("/*")
public class AppFilter implements Filter {

	private LogDAO logDAO = new LogDAOImpl();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// mã hóa utf-8 cho toàn bộ trang web
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// ghi nhận thông tin truy cập nếu đăng nhập
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		if (user != null) {
			try {
				System.out.println("debug: đang chuẩn bị lưu log cho " + user.getEmail());
				// lấy tt uri hiệnt tại
				String uri = req.getRequestURI();
				Date now = new Date();
				// lấy email để lưu username vào log
				String username = user.getEmail();

				// tạo log để lưu vào db
				Log log = new Log(null, uri, now, username);
				logDAO.create(log);
				System.out.println("debug: đã gọi lệnh create log");

				System.out.println("Ghi thành công: " + username + " - " + uri + " - " + now);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			System.out.println("debug: User chưa đăng nhập, không lưu log");
		}

		chain.doFilter(request, response);
	}

}
