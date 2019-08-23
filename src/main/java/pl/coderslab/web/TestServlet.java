package pl.coderslab.web;

import pl.coderslab.dao.*;
import pl.coderslab.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.LinkOption;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "TestServlet", urlPatterns = "/test")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StatusDao statusDao = new StatusDao();
        List<Status> list = statusDao.readAll();
        PrintWriter writer = response.getWriter();
        writer.println(list);
    }
}
