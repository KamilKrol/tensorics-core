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

package org.tensorics.core.tensorbacked.operations;

import org.tensorics.core.math.operations.UnaryOperation;
import org.tensorics.core.tensor.Tensor;
import org.tensorics.core.tensor.operations.ElementUnaryOperation;
import org.tensorics.core.tensorbacked.Tensorbacked;
import org.tensorics.core.tensorbacked.TensorbackedInternals;

/**
 * An operation which takes one tensor-backed object and returns another one of the same type.
 * 
 * @author agorzaws
 * @param <V> the type the values of the tensor
 * @param <TB> the type of the tensor backed object
 */
public class ElementTensorBackedUnaryOperation<V, TB extends Tensorbacked<V>> implements
        TensorBackedUnaryOperation<V, TB> {

    private final UnaryOperation<V> elementOperation;

    public ElementTensorBackedUnaryOperation(UnaryOperation<V> elementOperation) {
        super();
        this.elementOperation = elementOperation;
    }

    @Override
    public TB perform(TB value) {
        Tensor<V> internalTensor = new ElementUnaryOperation<V>(elementOperation).perform(value.tensor());
        /* safe cast since we ensure C as a type in the argument! */
        @SuppressWarnings("unchecked")
        Class<TB> tensorBackedClass = (Class<TB>) value.getClass();
        return TensorbackedInternals.createBackedByTensor(tensorBackedClass, internalTensor);
    }

}
