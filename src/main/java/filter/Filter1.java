package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

//không dùng @webfilter vì cấu hình trong web.xml
public class Filter1 implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// gán dl vào request
		request.setAttribute("hello", "Xin chào tôi là Filter 1");
		chain.doFilter(request, response);
	}

}
