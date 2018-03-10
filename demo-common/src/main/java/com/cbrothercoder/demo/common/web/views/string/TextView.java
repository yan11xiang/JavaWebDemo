/**
 * Created with IntelliJ IDEA.
 * User: 沙加
 * Date: 16/12/17
 * Time: 下午11:05
 */

package com.cbrothercoder.demo.common.web.views.string;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class TextView extends AbstractView {

    private String content;
    private String contentType = "text/text";

    public TextView(String content) {
        this.content = content;
    }

    public TextView(String content, String contentType) {
        this.content = content;
        this.contentType = contentType;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtils.isEmpty(content)) return;

        response.setContentType(contentType);
        response.setContentLength(content.getBytes(CharEncoding.UTF_8).length);
        response.setCharacterEncoding(CharEncoding.UTF_8);
        response.getWriter().write(content);
    }
}
