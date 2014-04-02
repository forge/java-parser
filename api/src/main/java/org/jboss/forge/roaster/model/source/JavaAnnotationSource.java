/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model.source;

import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.AnnotationElement;
import org.jboss.forge.roaster.model.JavaAnnotation;

/**
 * Represents a Java {@code @interface} annotation source file as an in-memory modifiable element. See
 * {@link Roaster} for various options in generating {@link JavaAnnotationSource} instances.
 *
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public interface JavaAnnotationSource extends JavaAnnotation<JavaAnnotationSource>, JavaSource<JavaAnnotationSource>, TypeHolderSource<JavaAnnotationSource>
{
   /**
    * Get the {@link AnnotationElementSource} with the given name and return it, otherwise, return null.
    */
   @Override
   public AnnotationElementSource getAnnotationElement(String name);

   /**
    * Get a list of all {@link AnnotationElementSource}s declared by this {@link JavaAnnotation}, or return an empty
    * list if no {@link AnnotationElementSource}s are declared.
    */
   @Override
   public List<AnnotationElementSource> getAnnotationElements();

   /**
    * Add a new Java {@link AnnotationElementSource} to this {@link JavaAnnotationSource} instance. This will be a stub until
    * further modified.
    */
   public AnnotationElementSource addAnnotationElement();

   /**
    * Add a new {@link AnnotationElementSource} declaration to this {@link JavaAnnotationSource} instance, using the given
    * {@link String} as the declaration.
    * <p/>
    * <strong>For example:</strong><br>
    * <code>AnnotationElement e = javaClass.addAnnotationElement("String newAnnotationElement();");</code>
    */
   public AnnotationElementSource addAnnotationElement(final String declaration);

   /**
    * Remove the given {@link AnnotationElement} from this {@link JavaAnnotationSource} instance, if it exists;
    * otherwise, do nothing.
    */
   public JavaAnnotationSource removeAnnotationElement(final AnnotationElement<?> annotationElement);
}