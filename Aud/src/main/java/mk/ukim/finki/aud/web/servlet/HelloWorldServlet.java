package mk.ukim.finki.aud.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloworldservlet", urlPatterns = "/hello")
public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String name = req.getParameter("name");
        String username = req.getParameter("username");
        PrintWriter writer = resp.getWriter();
        writer.format("<html><head>" +
                "<body><h1>Hello World %s!</h1></body>" +
                "</head></html>",  username);
        writer.flush();
    }
}
