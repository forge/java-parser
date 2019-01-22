/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.forge.roaster.model.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

public class ModifierAccessor
{
   public boolean hasModifier(ASTNode body, final ModifierKeyword keyword)
   {
      return getModifiers(body).stream().filter(modifier -> modifier.getKeyword() == keyword).count() != 0;
   }

   private List<Modifier> getModifiers(ASTNode body)
   {
      List<Modifier> result = new ArrayList<>();
      List<?> modifiers = getInternalModifiers(body);
      for (Object m : modifiers)
      {
         if (m instanceof Modifier)
         {
            Modifier mod = (Modifier) m;
            result.add(mod);
         }
      }
      return result;
   }

   public List<Modifier> clearVisibility(ASTNode body)
   {
      List<Modifier> modifiers = getModifiers(body);

      List<Modifier> toBeRemoved = new ArrayList<>();
      for (Modifier modifier : modifiers)
      {
         if (modifier.isPrivate() || modifier.isProtected() || modifier.isPublic())
         {
            toBeRemoved.add(modifier);
         }
      }

      getInternalModifiers(body).removeAll(toBeRemoved);
      return modifiers;
   }

   public void addModifier(ASTNode body, ModifierKeyword keyword)
   {
      if (!hasModifier(body, keyword))
      {
         getInternalModifiers(body).add(body.getAST().newModifier(keyword));
      }
   }

   public void removeModifier(ASTNode body, ModifierKeyword keyword)
   {
      List<Modifier> toBeRemoved = getModifiers(body).stream()
               .filter(modifier -> modifier.getKeyword().equals(keyword))
               .collect(Collectors.toList());
      getInternalModifiers(body).removeAll(toBeRemoved);
   }

   @SuppressWarnings("unchecked")
   private static List<Modifier> getInternalModifiers(final ASTNode body)
   {
      if (body instanceof BodyDeclaration)
      {
         return ((BodyDeclaration) body).modifiers();
      }
      else if (body instanceof SingleVariableDeclaration)
      {
         return ((SingleVariableDeclaration) body).modifiers();
      }
      else
      {
         return Collections.emptyList();
      }
   }
}