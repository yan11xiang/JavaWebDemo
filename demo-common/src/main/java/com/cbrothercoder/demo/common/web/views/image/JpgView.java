package com.cbrothercoder.demo.common.web.views.image;

import com.cbrothercoder.demo.common.web.views.file.InputStreamView;

import java.io.InputStream;

public class JpgView extends InputStreamView {

    public JpgView(InputStream image) {
        super(image, "image/jpg");
    }
}
