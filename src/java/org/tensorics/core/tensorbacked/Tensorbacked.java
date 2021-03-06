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

package org.tensorics.core.tensorbacked;

import org.tensorics.core.tensor.Tensor;

/**
 * Interface for any object that will support Tensors and it will be able to transform itself into a tensor ie. for
 * mathematics calculations.
 * 
 * @author agorzaws
 * @param <T> Type of the tensor which backs this object.
 */
public interface Tensorbacked<T> {

    /**
     * @return a {@link Tensor} for given types.
     */
    Tensor<T> tensor();

}
