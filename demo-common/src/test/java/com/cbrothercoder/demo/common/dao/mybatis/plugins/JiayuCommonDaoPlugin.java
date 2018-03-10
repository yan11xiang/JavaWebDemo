package com.cbrothercoder.demo.common.dao.mybatis.plugins;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.util.StringUtility;

import java.io.InputStream;
import java.util.*;

/**
 * @author trydofor
 * @since 2016-12-22.
 */
public class JiayuCommonDaoPlugin extends PluginAdapter {

    private String rootClass           = null;
    private String rootInterface       = null;
    private Set<String> rootClassIgnore     = new HashSet<>();
    private Set<String> rootInterfaceIgnore = new HashSet<>();

    @Override
    public boolean validate(List<String> list) {
        String rif = super.properties.getProperty("rootInterface");
        if (StringUtility.stringHasValue(rif)) {
            this.rootInterface = rif;
        }

        String rc = super.properties.getProperty("rootClass");
        if (StringUtility.stringHasValue(rc)) {
            this.rootClass = rc;
        }

        String rci = super.properties.getProperty("rootClassIgnore");
        if (StringUtility.stringHasValue(rif)) {
            String[] isa = rci.split("[,; \n\r\t]+");
            for (String s : isa) {
                rootClassIgnore.add(s.trim());
            }
        }

        String rii = super.properties.getProperty("rootInterfaceIgnore");
        if (StringUtility.stringHasValue(rii)) {
            String[] isa = rii.split("[,; \n\r\t]+");
            for (String s : isa) {
                rootInterfaceIgnore.add(s.trim());
            }
        }

        return true;
    }

    private boolean notIgnoreRootClassTable(IntrospectedTable introspectedTable) {
        return !rootClassIgnore.contains(introspectedTable.getFullyQualifiedTableNameAtRuntime());
    }

    private boolean notIgnoreRootInterfaceTable(IntrospectedTable introspectedTable) {
        return !rootInterfaceIgnore.contains(introspectedTable.getFullyQualifiedTableNameAtRuntime());
    }

    private String className(String clz) {
        int pos = clz.lastIndexOf('.');
        if (pos < 0) {
            return clz.substring(pos + 1);
        }
        return clz;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        if (notIgnoreRootInterfaceTable(introspectedTable) && rootInterface != null) {
            FullyQualifiedJavaType ipl = new FullyQualifiedJavaType(className(rootInterface) + "<" + introspectedTable.getBaseRecordType() + ">");
            FullyQualifiedJavaType imp = new FullyQualifiedJavaType(rootInterface);
            interfaze.addSuperInterface(ipl);
            interfaze.addImportedType(imp);

            Method deleteLogicById = new Method("deleteLogicById");
            deleteLogicById.setVisibility(JavaVisibility.PUBLIC);
            deleteLogicById.setReturnType(FullyQualifiedJavaType.getIntInstance());
            deleteLogicById.addParameter(new Parameter(new FullyQualifiedJavaType("Long"), "id"));
            interfaze.addMethod(deleteLogicById);
        }

        for (Method method : interfaze.getMethods()) {
            String nm = method.getName();
            if (nm.contains("ByPrimaryKey")) {
                method.setName(nm.replaceAll("ByPrimaryKey", "ById"));
            }
        }

        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

        if (notIgnoreRootClassTable(introspectedTable)) {
            String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();

            XmlElement parentElement = document.getRootElement();
            XmlElement deleteLogicById = new XmlElement("update");
            deleteLogicById.addAttribute(new Attribute("id", "deleteLogicById"));
            deleteLogicById.addElement(new TextElement("update " + tableName + " set is_deleted = 1 where id = #{id}"));

            parentElement.addElement(deleteLogicById);
        }

        for (Element element : document.getRootElement().getElements()) {
            XmlElement xe = (XmlElement) element;

            Attribute newId = null;
            for (Iterator<Attribute> iterator = xe.getAttributes().iterator(); iterator.hasNext();) {
                Attribute attr = iterator.next();
                if (attr.getName().equals("id")) {
                    String id = attr.getValue();
                    if (id.contains("ByPrimaryKey")) {
                        newId = new Attribute("id", id.replaceAll("ByPrimaryKey", "ById"));
                        iterator.remove();
                    }
                }
            }
            if (newId != null) {
                xe.addAttribute(newId);
            }

        }

        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(new FullyQualifiedJavaType("long"));
        field.setStatic(true);
        field.setFinal(true);
        field.setName("serialVersionUID");
        field.setInitializationString("1009L");
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.getFields().add(0, field);

        if (notIgnoreRootClassTable(introspectedTable)) {
            FullyQualifiedJavaType etd = new FullyQualifiedJavaType(className(rootClass));
            FullyQualifiedJavaType imp = new FullyQualifiedJavaType(rootClass);

            topLevelClass.addImportedType(imp);
            topLevelClass.setSuperClass(etd);
        } else {
            String seri = "java.io.Serializable";
            FullyQualifiedJavaType ipl = new FullyQualifiedJavaType(className(seri));
            FullyQualifiedJavaType imp = new FullyQualifiedJavaType(seri);
            topLevelClass.addSuperInterface(ipl);
            topLevelClass.addImportedType(imp);
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        InputStream configFile = JiayuCommonDaoPlugin.class.getResourceAsStream("/mybatis/mybatis-autogen.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warn : warnings) {
            System.out.println(warn);
        }
    }
}
