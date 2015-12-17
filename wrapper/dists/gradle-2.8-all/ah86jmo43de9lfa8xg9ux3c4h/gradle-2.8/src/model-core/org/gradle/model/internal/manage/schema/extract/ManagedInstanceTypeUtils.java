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

package org.gradle.model.internal.manage.schema.extract;

import org.gradle.internal.Cast;
import org.gradle.model.internal.manage.instance.ManagedInstance;
import org.gradle.model.internal.type.ModelType;

public class ManagedInstanceTypeUtils {
    public static <T> ModelType<? super T> extractModelTypeFromInstance(T instance) {
        if (instance instanceof ManagedInstance) {
            return Cast.uncheckedCast(((ManagedInstance) instance).getManagedType());
        }
        return ModelType.of(Cast.<Class<T>>uncheckedCast(instance.getClass()));
    }
}
