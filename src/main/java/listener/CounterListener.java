package listener;

import entity.Visitor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import util.XJPA;

@WebListener
public class CounterListener implements ServletContextListener, HttpSessionListener {

	// Khi ứng dụng chạy: đếm số từ db lên application scope
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		EntityManager em = XJPA.getEntityManager();
		Integer count = 0;

		try {
			// Tìm Id= 1
			Visitor visitor = em.find(Visitor.class, 1);
			if (visitor != null) {
				count = visitor.getVisitCount();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}

		// Lưu vào application scope để hiển thị lên web
		ServletContext application = sce.getServletContext();
		application.setAttribute("visitors", count);
		System.out.println("Load from database - Visitor: " + count);
	}

	// Khi ứng dụng tắt: lưu số đếm từ application scope xuống db
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		Integer count = (Integer) application.getAttribute("visitor");
		if (count == null) {
			count = 0;
		}

		EntityManager em = XJPA.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			// tìm bản ghi cũ
			Visitor visitor = em.find(Visitor.class, 1);
			if (visitor == null) {
				// nếu chưa có thì tạo mới
				visitor = new Visitor(1, count);
				em.persist(visitor);
			} else {
				visitor.setVisitCount(count);
				em.merge(visitor);
			}
			trans.commit();
			System.out.println("Saved to database - Visitor: " + count);
		} catch (Exception e) {
			// TODO: handle exception
			trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	// Khi có khách truy cập (session mới): tăng biến đếm trong RAM
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext application = se.getSession().getServletContext();

		// lấy số hiện tại
		Integer count = (Integer) application.getAttribute("visitors");
		if (count == null) {
			count = 0;
		}
		count++;
		// Cập nhật lại vào scope
		application.setAttribute("visitors", count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// không có chức năg
	}
}
