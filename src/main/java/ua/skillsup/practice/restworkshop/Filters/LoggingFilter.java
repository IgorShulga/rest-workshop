package ua.skillsup.practice.restworkshop.Filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
public class LoggingFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        logger.info("Logging Request {} : {}", req.getMethod(), req.getRequestURI());
        filterChain.doFilter(req, res);
        logger.info("Logging Respons :{}", res.getContentType());
    }

}
