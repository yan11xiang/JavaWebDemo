package com.cbrothercoder.demo.common.web.views.file;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author trydofor
 * @since 2016-12-21.
 */
public class DownloadView extends AbstractView {

    private final String fileName;
    private final InputStream inputStream;

    public DownloadView(File file) {
        try {
            this.inputStream = new FileInputStream(file);
            this.fileName = file.getName();
        }catch (Exception e){
            throw new IllegalArgumentException(e);
        }
    }


    public DownloadView(InputStream inputStream, String fileName) {
        this.inputStream = inputStream;
        this.fileName = fileName;
    }

    /**
     * RFC 5987, "Character Set and Language Encoding for Hypertext Transfer Protocol (HTTP) Header Field Parameters."
     * RFC 2183 indicates that such headers should be encoded according to RFC 2184,
     * which was obsoleted by RFC 2231, covered by the draft RFC above.
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (inputStream == null) return;

        OutputStream os = response.getOutputStream();
        try {
            response.setContentLength(inputStream.available());
            response.setContentType("application/octet-stream;");
            StringBuilder dis = new StringBuilder("attachment");
            dis.append(";fileName=").append(new String(fileName.getBytes("UTF8"),"ISO-8859-1")); // RFC 2184
            dis.append(";fileName*=UTF-8''").append(URLEncoder.encode(fileName,"UTF8")); //  RFC 5987
            response.setHeader("Content-Disposition", dis.toString());

            byte[] buffer = new byte[512];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
        } finally {
            inputStream.close();
        }
    }
}
