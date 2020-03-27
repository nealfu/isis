/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.isis.core.metamodel.inspect;

import java.util.Objects;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.Contributed;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.RestrictTo;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.graph.tree.TreeNode;
import org.apache.isis.applib.graph.tree.TreePath;
import org.apache.isis.applib.mixins.MixinConstants;
import org.apache.isis.applib.services.metamodel.MetaModelService;
import org.apache.isis.core.commons.internal.exceptions._Exceptions;
import org.apache.isis.core.metamodel.inspect.model.MMNodeFactory;
import org.apache.isis.core.metamodel.inspect.model.MMTreeAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Action(
        domainEvent = Object_inspectMetamodel.ActionDomainEvent.class,
        semantics = SemanticsOf.SAFE,
        restrictTo = RestrictTo.PROTOTYPING
        )
@ActionLayout(
        cssClassFa = "fa-sitemap",
        position = ActionLayout.Position.PANEL_DROPDOWN,
        contributed = Contributed.AS_ACTION
        )
@RequiredArgsConstructor
public class Object_inspectMetamodel {

    private final Object holder;

    public static class ActionDomainEvent 
    extends org.apache.isis.applib.IsisModuleApplib.ActionDomainEvent<Object_inspectMetamodel> {}

    @MemberOrder(name = MixinConstants.METADATA_LAYOUT_GROUPNAME, sequence = "700.2.1")
    public Object act() {

        val pkg = holder.getClass().getPackage().getName();

        val config =
                new MetaModelService.Config()
                .withIgnoreNoop()
                .withIgnoreAbstractClasses()
                .withIgnoreInterfaces()
                .withIgnoreBuiltInValueTypes()
                .withPackagePrefix(pkg);

        val metamodelDto = metaModelService.exportMetaModel(config);

        val className = holder.getClass().getName();

        val domainClassDto = metamodelDto.getDomainClassDto()
            .stream()
            .filter(classDto->Objects.equals(classDto.getId(), className))
            .findFirst()
            .orElseThrow(_Exceptions::noSuchElement);
        
        val root = MMNodeFactory.type(domainClassDto, null);
        val tree = TreeNode.lazy(root, MMTreeAdapter.class);
        tree.expand(TreePath.of(0)); // expand the root node
        return tree;
    }

    // -- DEPENDENCIES

    @Inject private MetaModelService metaModelService;
    //@Inject private SpecificationLoader specificationLoader;

}
