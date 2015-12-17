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

package org.gradle.model.internal.manage.instance;

import org.gradle.internal.UncheckedException;
import org.gradle.model.internal.manage.schema.ModelManagedImplStructSchema;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ManagedProxyFactory {

    public static final ManagedProxyFactory INSTANCE = new ManagedProxyFactory();

    public <T> T createProxy(ModelElementState state, ModelManagedImplStructSchema<T> schema) {
        try {
            Class<? extends T> generatedClass = schema.getImplementationType();
            if (generatedClass == null) {
                throw new IllegalStateException("No managed implementation class available for: " + schema.getType());
            }
            Class<?> delegateType = schema.getDelegateType();
            if (delegateType == null) {
                Constructor<? extends T> constructor = generatedClass.getConstructor(ModelElementState.class);
                return constructor.newInstance(state);
            } else {
                Object delegate = state.getBackingNode().getPrivateData(delegateType);
                Constructor<? extends T> constructor = generatedClass.getConstructor(ModelElementState.class, delegateType);
                return constructor.newInstance(state, delegate);
            }
        } catch (InvocationTargetException e) {
            throw UncheckedException.throwAsUncheckedException(e.getTargetException());
        } catch (Exception e) {
            throw UncheckedException.throwAsUncheckedException(e);
        }
    }

}
