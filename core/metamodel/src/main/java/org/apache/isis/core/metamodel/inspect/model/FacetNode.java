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
package org.apache.isis.core.metamodel.inspect.model;

import java.util.stream.Stream;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Nature;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.schema.metamodel.v2.Facet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@DomainObject(nature=Nature.VIEW_MODEL, objectType = "isis.metamodel.FacetNode")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class FacetNode extends MMNode {

    @Property(hidden = Where.EVERYWHERE)
    @Getter @Setter private Facet facet;
    
    @Override
    public String title() {
        return facet.getFqcn()
                .replace("org.apache.isis.core.metamodel.facets.", "»")
                .replace("org.apache.isis.core.metamodel.", "»");
    }
    
    @Override
    public String iconName() {
        return "";
    }
    
    // -- TREE NODE STUFF
    
    @Getter @Setter @XmlTransient 
    private MMNode parentNode;

    @Override
    public int getChildNodeCount() {
        return facet.getAttr().size();
    }

    @Override
    public Stream<MMNode> streamChildNodes() {
        return facet.getAttr().stream()
                .map(facetAttr->MMNodeFactory.facetAttr(facetAttr, this));
    }
   
    
}

