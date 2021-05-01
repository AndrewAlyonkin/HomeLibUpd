package edu.alenkin.HomeLibUpd.servlets;

import edu.alenkin.HomeLibUpd.controllers.SearchController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class ShowImage extends HttpServlet {
    private String message;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        try(OutputStream out = response.getOutputStream()){
            int index = Integer.valueOf(request.getParameter("index"));
            SearchController searchController = (SearchController) request.getSession(false).getAttribute("searchController");
            byte[] image = searchController.getImage(index);
            response.setContentLength(image.length);
            out.write(image);
        }
    }

    public void destroy() {
    }

}
