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
package demoapp.dom.types.primitive.booleans.holder;

import javax.jdo.annotations.DatastoreIdentity;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Bounding;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.LabelPosition;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.PropertyLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import demoapp.dom._infra.asciidocdesc.HasAsciiDocDescription;

//tag::class[]
public interface PrimitiveBooleanHolder {

//end::class[]
    @MemberOrder(name = "read-only-properties", sequence = "1") // TODO: doesn't seem to get picked up
    @Property
    @PropertyLayout
//tag::class[]
    boolean isReadOnlyProperty();
    void setReadOnlyProperty(boolean c);

//end::class[]
    @MemberOrder(name = "editable-properties", sequence = "1") // TODO: doesn't seem to get picked up
    @Property
    @PropertyLayout
//tag::class[]
    boolean isReadWriteProperty();
    void setReadWriteProperty(boolean c);

    @MemberOrder(name = "label-positions", sequence = "1")
    @Property
    @PropertyLayout(labelPosition = LabelPosition.LEFT, describedAs = "labelPosition=LEFT", hidden = Where.ALL_TABLES)
    default boolean isReadOnlyPropertyDerivedLabelPositionLeft() { return isReadOnlyProperty(); }

    @MemberOrder(name = "label-positions", sequence = "2")
    @Property
    @PropertyLayout(labelPosition = LabelPosition.TOP, describedAs = "labelPosition=TOP", hidden = Where.ALL_TABLES)
    default boolean isReadOnlyPropertyDerivedLabelPositionTop() { return isReadOnlyProperty(); }

    @MemberOrder(name = "label-positions", sequence = "3")
    @Property
    @PropertyLayout(labelPosition = LabelPosition.RIGHT, describedAs = "labelPosition=RIGHT", hidden = Where.ALL_TABLES)
    default boolean isReadOnlyPropertyDerivedLabelPositionRight() { return isReadOnlyProperty(); }

    @MemberOrder(name = "label-positions", sequence = "4")
    @Property
    @PropertyLayout(labelPosition = LabelPosition.NONE, describedAs = "labelPosition=NONE", hidden = Where.ALL_TABLES)
    default boolean isReadOnlyPropertyDerivedLabelPositionNone() { return isReadOnlyProperty(); }

}
//end::class[]
