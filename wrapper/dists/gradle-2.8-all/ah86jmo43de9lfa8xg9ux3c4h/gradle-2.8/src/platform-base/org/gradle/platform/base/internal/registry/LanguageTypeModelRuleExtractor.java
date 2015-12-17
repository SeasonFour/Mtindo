/*
 * Copyright 2014 the original author or authors.
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

package org.gradle.platform.base.internal.registry;

import com.google.common.collect.ImmutableList;
import org.gradle.api.internal.file.FileResolver;
import org.gradle.internal.reflect.Instantiator;
import org.gradle.internal.service.ServiceRegistry;
import org.gradle.language.base.LanguageSourceSet;
import org.gradle.language.base.internal.registry.LanguageRegistry;
import org.gradle.language.base.internal.registry.RuleBasedLanguageRegistration;
import org.gradle.language.base.plugins.ComponentModelBasePlugin;
import org.gradle.language.base.sources.BaseLanguageSourceSet;
import org.gradle.model.internal.core.*;
import org.gradle.model.internal.core.rule.describe.ModelRuleDescriptor;
import org.gradle.model.internal.inspect.MethodRuleDefinition;
import org.gradle.model.internal.manage.schema.ModelSchema;
import org.gradle.model.internal.manage.schema.ModelSchemaStore;
import org.gradle.model.internal.type.ModelType;
import org.gradle.platform.base.LanguageType;
import org.gradle.platform.base.LanguageTypeBuilder;
import org.gradle.platform.base.internal.builder.LanguageTypeBuilderInternal;
import org.gradle.platform.base.internal.builder.TypeBuilderFactory;
import org.gradle.platform.base.internal.builder.TypeBuilderInternal;
import org.gradle.platform.base.internal.util.ImplementationTypeDetermer;

import java.util.List;

public class LanguageTypeModelRuleExtractor extends TypeModelRuleExtractor<LanguageType, LanguageSourceSet, BaseLanguageSourceSet> {
    public ImplementationTypeDetermer<LanguageSourceSet, BaseLanguageSourceSet> implementationTypeDetermer = new ImplementationTypeDetermer<LanguageSourceSet, BaseLanguageSourceSet>("language", BaseLanguageSourceSet.class);

    public LanguageTypeModelRuleExtractor(ModelSchemaStore schemaStore) {
        super("language", LanguageSourceSet.class, BaseLanguageSourceSet.class, LanguageTypeBuilder.class, schemaStore, new TypeBuilderFactory<LanguageSourceSet>() {
            @Override
            public TypeBuilderInternal<LanguageSourceSet> create(ModelSchema<? extends LanguageSourceSet> schema) {
                return new DefaultLanguageTypeBuilder(schema);
            }
        });
    }

    @Override
    protected <R, S> ExtractedModelRule createRegistration(MethodRuleDefinition<R, S> ruleDefinition, ModelType<? extends LanguageSourceSet> type, TypeBuilderInternal<LanguageSourceSet> builder) {
        ImmutableList<Class<?>> dependencies = ImmutableList.<Class<?>>of(ComponentModelBasePlugin.class);
        ModelType<? extends BaseLanguageSourceSet> implementation = implementationTypeDetermer.determineImplementationType(type, builder);
        if (implementation != null) {
            ModelAction mutator = new RegisterTypeRule(type, implementation, ((LanguageTypeBuilderInternal) builder).getLanguageName(), ruleDefinition.getDescriptor());
            return new ExtractedModelAction(ModelActionRole.Defaults, dependencies, mutator);
        }
        return new DependencyOnlyExtractedModelRule(dependencies);
    }

    public static class DefaultLanguageTypeBuilder extends AbstractTypeBuilder<LanguageSourceSet> implements LanguageTypeBuilderInternal<LanguageSourceSet> {
        private String languageName;

        public DefaultLanguageTypeBuilder(ModelSchema<? extends LanguageSourceSet> schema) {
            super(LanguageType.class, schema);
        }

        @Override
        public void setLanguageName(String languageName) {
            this.languageName = languageName;
        }

        @Override
        public String getLanguageName() {
            return languageName;
        }
    }

    protected static class RegisterTypeRule extends AbstractModelActionWithView<LanguageRegistry> {
        private final ModelType<? extends LanguageSourceSet> type;
        private final ModelType<? extends BaseLanguageSourceSet> implementation;
        private final String languageName;

        protected RegisterTypeRule(ModelType<? extends LanguageSourceSet> type, ModelType<? extends BaseLanguageSourceSet> implementation, String languageName, ModelRuleDescriptor descriptor) {
            super(ModelReference.of(LanguageRegistry.class), descriptor, ModelReference.of(ServiceRegistry.class));
            this.type = type;
            this.implementation = implementation;
            this.languageName = languageName;
        }

        @Override
        protected void execute(MutableModelNode modelNode, LanguageRegistry languageRegistry, List<ModelView<?>> inputs) {
            ServiceRegistry serviceRegistry = ModelViews.assertType(inputs.get(0), ModelType.of(ServiceRegistry.class)).getInstance();
            Instantiator instantiator = serviceRegistry.get(Instantiator.class);
            FileResolver fileResolver = serviceRegistry.get(FileResolver.class);
            @SuppressWarnings("unchecked")
            Class<BaseLanguageSourceSet> publicClass = (Class<BaseLanguageSourceSet>) type.getConcreteClass();
            Class<? extends BaseLanguageSourceSet> implementationClass = implementation.getConcreteClass();
            languageRegistry.add(new RuleBasedLanguageRegistration<BaseLanguageSourceSet>(languageName, publicClass, implementationClass, instantiator, fileResolver));
        }
    }
}
