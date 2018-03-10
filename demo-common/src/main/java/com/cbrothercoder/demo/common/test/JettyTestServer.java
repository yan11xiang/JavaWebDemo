package com.cbrothercoder.demo.common.test;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * @author trydofor
 * @since 2017-01-04.
 */
public class JettyTestServer {

    public void start(Class<?> root, final int port) throws Exception {

        final String serverName = root.getSimpleName();

        String classFile = "/" + root.getName().replace('.', '/') + ".class";
        String classPattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + classFile;

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResources(classPattern)[0];
        String webappDir = resource.getFile().getAbsolutePath().replaceAll("target.*$", "") + "webapp";

        final File descriptor = File.createTempFile("jetty-webdefault-", ".tmp");
        descriptor.deleteOnExit();
        String webXml = webappDir + "/WEB-INF/web.xml";
        InputStream dis = JettyTestServer.class.getResourceAsStream(webXml);
        if (dis == null) {
            dis = new FileInputStream(webXml);
        }

        Files.copy(dis, descriptor.toPath(), StandardCopyOption.REPLACE_EXISTING);

        WebAppContext context = new WebAppContext();
        context.setContextPath("/");
        context.setDefaultsDescriptor(descriptor.getAbsolutePath());
        context.setResourceBase("file:" + webappDir);
        context.setClassLoader(root.getClassLoader());
        context.setParentLoaderPriority(true);

        Server server = new Server(port);

//        server.setGracefulShutdown(1000);
        server.setHandler(context);

        server.addLifeCycleListener(new AbstractLifeCycle.AbstractLifeCycleListener() {
            @Override
            public void lifeCycleStarted(LifeCycle lifeCycle) {
                System.err.println("\n");
                System.err.println("===============================================================================================================");
                System.err.println("============================\t\t" + serverName + " is started ! PORT : " + port + "\t\t===========================");
                System.err.println("===============================================================================================================");
                System.err.println("\n");
            }
        });

        //
        server.start();
        server.join();
    }

    public static void main(String[] args) throws Exception {

        JettyTestServer jettyTestServer = new JettyTestServer();
        jettyTestServer.start(JettyTestServer.class, 9999);
    }
}
