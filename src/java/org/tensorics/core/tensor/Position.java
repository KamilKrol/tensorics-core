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

package org.tensorics.core.tensor;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;

/**
 * Defines the position of a value within a tensor in the N-dimensional coordinate space.
 * 
 * @author agorzaws
 */
public final class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Interner<Position> INTERNER = Interners.newWeakInterner();

    /*
     * NOTE: This has to be after the cachedPositions initialization, because the 'of' method uses it!
     */
    private static final Position EMPTY_POSITION = Position.of(Collections.emptySet());

    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    private final ClassToInstanceMap<Object> coordinates;

    private Position(Set<?> coordinates) {
        this.coordinates = Coordinates.mapOf(coordinates);
        if (this.coordinates.containsKey(Position.class)) {
            throw new IllegalArgumentException("A position is contained in the collection of coordinates."
                    + "This is most-probably a programming mistake and therefore not allowed.");
        }
        if (this.coordinates.containsKey(Position.class)) {
            throw new IllegalArgumentException("A position is contained in the collection of coordinates."
                    + "This is most-probably a programming mistake and therefore not allowed.");
        }
    }

    @SuppressWarnings("PMD.ShortMethodName")
    public static Position of(Set<?> coordinates) {
        return INTERNER.intern(new Position(coordinates));
    }

    public static Position empty() {
        return EMPTY_POSITION;
    }

    @SafeVarargs
    @SuppressWarnings("PMD.ShortMethodName")
    public static Position of(Object... coordinates) {
        return of(ImmutableSet.copyOf(coordinates));
    }

    public ClassToInstanceMap<Object> getCoordinates() {
        return coordinates;
    }

    public <CS> CS coordinateFor(Class<CS> dimension) {
        return getCoordinates().getInstance(dimension);
    }

    public Set<?> coordinates() {
        return new HashSet<>(coordinates.values());
    }

    /**
     * Retrieves the dimensions of this position (i.e. the type of the containing coordinates)
     * 
     * @return the types of the coordinates
     */
    public Set<Class<?>> dimensionSet() {
        return getCoordinates().keySet();
    }

    /**
     * Checks if the position is consistent with the given dimensions. Conformity means that the position contains
     * exactly one coordinate for each dimension in the given set of dimensions (classes of coordinates).
     * 
     * @param dimensions the dimensions for which conformity has to be checked.
     * @return {@code true} if the position is conform, {@code false} if not.
     */
    public boolean isConsistentWith(Set<? extends Class<?>> dimensions) {
        Preconditions.checkArgument(dimensions != null, "Argument '" + "dimensions" + "' must not be null!");
        return dimensions.equals(dimensionSet());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Position other = (Position) obj;
        if (coordinates == null) {
            if (other.coordinates != null) {
                return false;
            }
        } else if (!coordinates.equals(other.coordinates)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return coordinates.values().toString();
    }

}