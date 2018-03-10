package com.cbrothercoder.demo.admin;

import com.cbrothercoder.demo.common.test.JettyTestServer;

import java.util.TimeZone;


public class JettyAdminTestJetty {

    public static void main(String[] args) throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        JettyTestServer jettyTestServer = new JettyTestServer();
        jettyTestServer.start(JettyAdminTestJetty.class, 8800);
    }

}
