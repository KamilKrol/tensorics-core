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
package org.tensorics.core.tree.domain;

import java.util.Collections;
import java.util.List;

/**
 * An expression that needs no further processing. It contains already its own result, which can be simply retrieved by
 * the {@link #get()} method.
 * 
 * @author kfuchsbe
 * @param <R> the type of the resulting value of the expression
 */
public final class ResolvedExpression<R> implements Expression<R> {

    private final R value;

    private ResolvedExpression(R value) {
        this.value = value;
    }

    @SuppressWarnings("PMD.ShortMethodName")
    public static <T> ResolvedExpression<T> of(T value) {
        return new ResolvedExpression<T>(value);
    }

    @Override
    public boolean isResolved() {
        return true;
    }

    @Override
    public R get() {
        return value;
    }

    @Override
    public List<Node> getChildren() {
        return Collections.emptyList();
    }

}