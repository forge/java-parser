package org.jboss.forge.roaster.model.expressions;


import org.jboss.forge.roaster.model.source.JavaSource;

public interface Argument<O extends JavaSource<O>, T extends ExpressionSource<O>>
        extends Expression<O, T> {

}