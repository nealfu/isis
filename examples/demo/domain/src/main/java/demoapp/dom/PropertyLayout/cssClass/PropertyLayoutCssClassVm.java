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
package demoapp.dom.PropertyLayout.cssClass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Nature;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;

import lombok.Getter;
import lombok.Setter;

import demoapp.dom.PropertyLayout.named.PropertyLayoutNamedMetaAnnotation;
import demoapp.dom._infra.asciidocdesc.HasAsciiDocDescription;

//tag::class[]
@XmlRootElement(name = "root")
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@DomainObject(
        nature=Nature.VIEW_MODEL,
        objectType = "demo.PropertyLayout.cssClass.ActionLayoutPromptStyleVm"
)
public class StringViewModel implements HasAsciiDocDescription {

//end::class[]
    public StringViewModel() {
        this.title = "PropertyLayout#cssClass";
    }

//tag::class[]
    @Title(prepend = "Demonstrates: ")
    @Property(editing = Editing.DISABLED)
    @XmlElement(required = true)
    @Getter @Setter
    private String title;

    @Property(optionality = Optionality.OPTIONAL)
    @PropertyLayout(cssClass = "red", describedAs = "@PropertyLayout(cssClass=\"red\")", hidden = Where.ALL_TABLES)
    @MemberOrder(name = "properties", sequence = "1")
    @XmlElement(required = false)
    @Getter @Setter
    private String propertyUsingAnnotation;

    @Property(optionality = Optionality.OPTIONAL)
    @PropertyLayout(describedAs = "<cpt:property id=\"...\" cssClass=\"red\")/>", hidden = Where.ALL_TABLES)
    @MemberOrder(name = "properties", sequence = "2")
    @XmlElement(required = false)
    @Getter @Setter
    private String propertyUsingLayout;

    @Property(optionality = Optionality.OPTIONAL)
    @PropertyLayoutCssClassMetaAnnotation
    @PropertyLayout(describedAs = "@PropertyLayoutCssClassMetaAnnotation", hidden = Where.ALL_TABLES)
    @MemberOrder(name = "meta-annotated", sequence = "1")
    @XmlElement(required = false)
    @Getter @Setter
    private String propertyUsingMetaAnnotation;

    @Property(optionality = Optionality.OPTIONAL)
    @PropertyLayoutCssClassMetaAnnotation
    @PropertyLayout(cssClass = "blue", describedAs = "meta-annotation overridden using @PropertyLayout(...)", hidden = Where.ALL_TABLES)
    @MemberOrder(name = "meta-annotated", sequence = "2")
    @XmlElement(required = false)
    @Getter @Setter
    private String propertyUsingMetaAnnotationOverridden;

}
//end::class[]
