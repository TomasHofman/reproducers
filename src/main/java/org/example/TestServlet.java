package org.example;

import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

@WebServlet("/HelloWorld")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<URL> resources = FileUtils.class.getClassLoader().getResources("/META-INF/MANIFEST.MF");
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            if (url.toString().contains("commons-io")) {
                resp.getWriter().println("Manifest URL: " + url);
            }
        }

        resp.getWriter().println("FileUtils classloader: " + FileUtils.class.getClassLoader().getName());
    }
}
