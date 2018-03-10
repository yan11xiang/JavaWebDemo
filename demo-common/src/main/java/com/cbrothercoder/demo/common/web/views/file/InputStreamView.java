package com.cbrothercoder.demo.common.web.views.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author trydofor
 * @since 2016-12-21.
 */
public class InputStreamView {
    private final InputStream inputStream;
    private final String contentType;

    public InputStreamView(InputStream inputStream, String contentType) {
        this.inputStream = inputStream;
        this.contentType = contentType;
    }

    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (inputStream == null) return;

        OutputStream os = response.getOutputStream();
        try {
            response.setContentLength(inputStream.available());
            response.setContentType(contentType);

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
