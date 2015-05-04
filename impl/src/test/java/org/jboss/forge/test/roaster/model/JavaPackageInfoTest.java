/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.forge.test.roaster.model;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.Annotation;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.ValuePair;
import org.jboss.forge.roaster.model.impl.JavaPackageInfoImpl;
import org.jboss.forge.roaster.model.impl.TypeImpl;
import org.jboss.forge.roaster.model.source.AnnotationSource;
import org.jboss.forge.roaster.model.source.Import;
import org.jboss.forge.roaster.model.source.JavaPackageInfoSource;
import org.junit.Assert;
import org.junit.Test;

public class JavaPackageInfoTest
{

   @Test
   public void testCanCreatePackageInfo() throws Exception
   {
      JavaPackageInfoSource packageInfo = Roaster.create(JavaPackageInfoSource.class);
      packageInfo.setPackage("org.jboss.forge.roaster");
      Assert.assertEquals("org.jboss.forge.roaster", packageInfo.getPackage());
      Assert.assertEquals("package-info", packageInfo.getName());
   }

   @Test(expected = UnsupportedOperationException.class)
   public void testSetPackageInfoNameThrowsUnsupportedOperation() throws Exception
   {
      JavaPackageInfoSource packageInfo = Roaster.create(JavaPackageInfoSource.class);
      packageInfo.setName("anything");
   }

   @Test
   public void testCanParsePackageInfo() throws Exception
   {
      InputStream stream = JavaPackageInfoTest.class
               .getResourceAsStream("/org/jboss/forge/grammar/java/package-info.java");
      JavaPackageInfoSource javaPkg = Roaster.parse(JavaPackageInfoSource.class, stream);
      assertEquals("org.jboss.forge.test.roaster.model", javaPkg.getPackage());
      Assert.assertEquals("package-info", javaPkg.getName());
      Assert.assertNotNull(javaPkg.getImport("javax.xml.bind.annotation.XmlSchema"));
      Import XmlAccessTypeField = javaPkg.getImport("javax.xml.bind.annotation.XmlAccessType.FIELD");
      Assert.assertNotNull(XmlAccessTypeField);
      Assert.assertTrue(XmlAccessTypeField.isStatic());
      List<AnnotationSource<JavaPackageInfoSource>> annotations = javaPkg.getAnnotations();
      Assert.assertEquals(2, annotations.size());
      Annotation<JavaPackageInfoSource> annotation = javaPkg.getAnnotation("XmlSchema");
      List<ValuePair> values = annotation.getValues();
      Assert.assertEquals(3, values.size());
      String namespace = annotation.getLiteralValue("namespace");
      Assert.assertEquals(namespace, "\"http://forge.org/Test\"");

      AnnotationSource<JavaPackageInfoSource> annotationXmlOrder = javaPkg
               .addAnnotation("javax.xml.bind.annotation.XmlAccessorOrder");
      AnnotationSource<JavaPackageInfoSource> annotationXmlAccessorOrder = javaPkg.getAnnotation("XmlAccessorOrder");
      Assert.assertEquals(annotationXmlOrder.getName(), annotationXmlAccessorOrder.getName());
   }

   @Test
   public void testPackageInfoWithImportedAnnotations()
   {
      JavaPackageInfoSource packageInfo = Roaster.create(JavaPackageInfoSource.class);
      packageInfo.setPackage("org.jboss.forge.roaster");
      Type type = new TypeImpl(packageInfo, null, MyPLAnnotation.class.getName());
      packageInfo.addImport(type);
      packageInfo.addAnnotation("my.custom.Annotation");
      packageInfo.getEnclosingType();

      assertEquals(2,packageInfo.getImports().size());
   }

   public static @interface MyPLAnnotation {}
}
