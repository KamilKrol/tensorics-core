// @formatter:off
 /*******************************************************************************
 *
 * This file is part of tensorics.
 * 
 * Copyright (c) 2008-2011, CERN. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 ******************************************************************************/
// @formatter:on

package org.tensorics.core.expressions;

import java.util.List;

import org.tensorics.core.commons.operations.Conversion;
import org.tensorics.core.tree.domain.AbstractDeferredExpression;
import org.tensorics.core.tree.domain.Expression;
import org.tensorics.core.tree.domain.Node;

import com.google.common.collect.ImmutableList;

/**
 * Represents an expression, which takes takes (expressions of) objects of one type and converts them into others.
 * 
 * @author kfuchsbe
 * @param <T> the type of the values of the tensor
 * @param <R> the type of the tensorbacked object
 */
public class ConversionOperationExpression<T, R> extends AbstractDeferredExpression<R> {

    private final Expression<T> sourceObject;
    private final Conversion<T, R> operation;

    public ConversionOperationExpression(Conversion<T, R> operation, Expression<T> source) {
        super();
        this.operation = operation;
        this.sourceObject = source;
    }

    @Override
    public List<Node> getChildren() {
        return ImmutableList.<Node> of(sourceObject);
    }

    public Expression<T> getSource() {
        return sourceObject;
    }

    public Conversion<T, R> getOperation() {
        return operation;
    }

}
