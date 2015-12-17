/*
 * Copyright 2013 the original author or authors.
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

import com.google.common.collect.ListMultimap;
import org.gradle.model.internal.core.rule.describe.ModelRuleDescriptor;

import java.util.Set;

public interface ModelCreator extends ModelRule {
    ModelRuleDescriptor getDescriptor();

    ModelPath getPath();

    ModelPromise getPromise();

    ModelAdapter getAdapter();

    ModelProjection getProjection();

    /**
     * Actions that need to be registered when the node is registered.
     */
    ListMultimap<ModelActionRole, ? extends ModelAction> getActions();

    Set<? extends ModelReference<?>> getInputs();

    boolean isEphemeral();

    void addProjection(ModelProjection projection);
}
