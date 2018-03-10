package com.cbrothercoder.demo.common.web.views.file;


import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Map;

/**
 * <p>
 * <p>
 * </p>
 * User: Kevin(ΰ��Ĵ����)
 * Date: 2017/9/25
 */
public class ExcelView {
    private static final Logger logger = LoggerFactory.getLogger(ExcelView.class);
    private InputStream inputStream;

    private String fileName;

    public ExcelView(InputStream inputStream, String fileName) {
        this.inputStream = inputStream;
        this.fileName = fileName;
    }

    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            response.setContentType("application/force-download;");
            StringBuilder dis = new StringBuilder("attachment");
            dis.append(";fileName=").append(new String(this.fileName.getBytes("UTF8"), "ISO-8859-1")); // RFC 2184
            dis.append(";fileName*=UTF-8''"); //  RFC 5987
            response.setHeader("Content-Disposition", dis.toString());
            IOUtils.copy(this.inputStream, response.getOutputStream());
            this.inputStream.close();
        } catch (Exception e) {
            logger.error("ExcelView error", e);
        }
    }

}