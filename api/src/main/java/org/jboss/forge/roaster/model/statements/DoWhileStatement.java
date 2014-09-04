package org.jboss.forge.roaster.model.statements;

import org.jboss.forge.roaster.model.Block;
import org.jboss.forge.roaster.model.expressions.Expression;
import org.jboss.forge.roaster.model.source.BlockHolder;
import org.jboss.forge.roaster.model.source.JavaSource;

public interface DoWhileStatement<O extends JavaSource<O>, T extends Block<O, ? extends BlockHolder<O, ?>>>
        extends Statement<O,T,DoWhileStatement<O,T>>,
        BlockHolder<O,DoWhileStatement<O, T>> {

    DoWhileStatement<O,T> setCondition( Expression expr );

    DoWhileStatement<O,T> setBody( Statement block );

}