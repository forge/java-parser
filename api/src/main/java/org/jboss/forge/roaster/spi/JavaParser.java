/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.forge.roaster.spi;

import java.io.InputStream;
import java.util.List;

import org.jboss.forge.roaster.model.JavaType;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.JavaSourceUnit;

/**
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 * 
 */
public interface JavaParser
{
   /**
    * Create a new empty {@link JavaSource} instance.
    * 
    * @param type desired source type
    * @return instance of {@code T}, {@code null} if the {@link JavaSource} type is not supported by this
    *         {@link JavaParser}.
    */
   <T extends JavaSource<?>> T create(final Class<T> type);

   /**
    * Read the given {@link InputStream} and parse the data into a new {@link JavaType} instance.
    * 
    * @param data to parse
    * @return {@link JavaType}, {@code null} if the data format is not recognized by this {@link JavaParser}.
    */
   JavaType<?> parse(final InputStream data);

   /**
    * Read the given {@link InputStream} and parse the data into a source code file data type representation.
    * 
    * @param encoding encoding used when the data is a string
    * @param data to parse
    * @return {@link JavaSourceUnit}, {@code null} if the data format is not recognized by this {@link JavaParser}.
    */
   JavaSourceUnit parse(final String encoding, final InputStream data);

}