package com.cbrothercoder.demo.common.web.views.file;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author trydofor
 * @since 2016-12-21.
 */
public class ByteArrayView extends AbstractView {
    private final byte[] byteArray;
    private final String contentType;

    public ByteArrayView(byte[] byteArray, String contentType) {
        this.byteArray = byteArray;
        this.contentType = contentType;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(contentType);
        response.setContentLength(byteArray.length);
        response.getOutputStream().write(byteArray);
    }
}
