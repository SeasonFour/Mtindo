/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.model.internal.core;

import org.gradle.api.Nullable;
import org.gradle.internal.Cast;
import org.gradle.model.internal.core.rule.describe.ModelRuleDescriptor;
import org.gradle.model.internal.type.ModelType;

import java.util.Collections;
import java.util.List;

public class FactoryBasedNodeInitializer<T, S extends T> implements NodeInitializer {
    private final ModelReference<? extends InstanceFactory<? super T, String>> factoryReference;
    private final ModelType<S> type;

    public FactoryBasedNodeInitializer(ModelReference<? extends InstanceFactory<? super T, String>> factoryReference, ModelType<S> type) {
        this.factoryReference = factoryReference;
        this.type = type;
    }

    @Override
    public List<? extends ModelReference<?>> getInputs() {
        return Collections.singletonList(factoryReference);
    }

    @Override
    public void execute(MutableModelNode modelNode, List<ModelView<?>> inputs) {
        InstanceFactory<? super T, String> instantiator = Cast.uncheckedCast(inputs.get(0).getInstance());
        S item = instantiator.create(type, modelNode, modelNode.getPath().getName());
        modelNode.setPrivateData(type, item);
    }

    @Override
    public List<? extends ModelProjection> getProjections() {
        return Collections.singletonList(UnmanagedModelProjection.of(type));
    }

    @Nullable
    @Override
    public ModelAction getProjector(final ModelPath path, final ModelRuleDescriptor descriptor, final ModelType<?> typeToCreate) {
        if (!type.isAssignableFrom(typeToCreate)) {
            throw new IllegalArgumentException(String.format("Type %s is not a subtype of %s", typeToCreate, type));
        }
        final ModelType<? extends S> projectedType = Cast.uncheckedCast(typeToCreate);
        return new AbstractModelAction<Object>(ModelReference.of(path), descriptor, factoryReference) {
            @Override
            public void execute(MutableModelNode modelNode, List<ModelView<?>> inputs) {
                InstanceFactory<S, String> factory = Cast.uncheckedCast(inputs.get(0).getInstance());
                for (ModelType<?> internalView : factory.getInternalViews(projectedType)) {
                    modelNode.addProjection(UnmanagedModelProjection.of(internalView));
                }
            }
        };
    }
}
