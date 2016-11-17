/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.roaster.model;

import java.util.List;

/**
 * Represents a {@link JavaType} that may declare fields or methods.
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 * 
 */
public interface MemberHolder<O extends JavaType<O>>
{
   /**
    * Return a list of all class members (fields, methods, etc.)
    */
   List<? extends Member<O>> getMembers();
}
