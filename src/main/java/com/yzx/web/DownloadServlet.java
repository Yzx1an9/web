package com.yzx.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filname = "logo.gif";
        ServletContext servletContext = getServletContext();

        InputStream resourceAsStream = servletContext.getResourceAsStream("/static/img/" + filname);
        String mimeType = servletContext.getMimeType("/static/img/" + filname);
        System.out.println(mimeType);
        resp.setContentType(mimeType);
        resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("中国.gif", "UTF-8"));
        OutputStream outputStream = resp.getOutputStream();
        IOUtils.copy(resourceAsStream, outputStream);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String filname = "logo.gif";
//        ServletContext servletContext = getServletContext();
//
//        InputStream resourceAsStream = servletContext.getResourceAsStream("/static/img/" + filname);
//        String mimeType = servletContext.getMimeType("/static/img/" + filname);
//        resp.setContentType(mimeType);
//        resp.setHeader("Content-Dispostion","attachment;filename=" + filname);
//        resourceAsStream.read();
//        ServletOutputStream outputStream = resp.getOutputStream();
//        IOUtils.copy(resourceAsStream,outputStream);
        req.setCharacterEncoding("UTF-8");
        if (ServletFileUpload.isMultipartContent(req)) {
            System.out.println(req.getContentType());
            ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                String name = "";
                for (FileItem fl : list) {
                    if (!fl.isFormField()) {
                        try {

                            System.out.println(fl.getContentType());
                            fl.write(new File("D:\\937810953\\" + fl.getName()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        name = fl.getFieldName().equals("username") ? fl.getString() : "";
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }


        }

    }
}
