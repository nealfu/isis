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

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.PromptStyle;
import org.apache.isis.applib.annotation.SemanticsOf;

import lombok.RequiredArgsConstructor;

//tag::class[]
@Action(
        semantics = SemanticsOf.IDEMPOTENT,
        associateWith = "readOnlyProperty",
        associateWithSequence = "1"
)
@ActionLayout(promptStyle = PromptStyle.INLINE, named = "Inline", describedAs = "promptStyle = INLINE")
@RequiredArgsConstructor
public class PrimitiveBooleanHolder_updateReadOnlyPropertyPromptStyleInline {

    private final PrimitiveBooleanHolder primitiveBooleanHolder;

    public PrimitiveBooleanHolder act(boolean newValue) {
        primitiveBooleanHolder.setReadOnlyProperty(newValue);
        return primitiveBooleanHolder;
    }
    public boolean default0Act() {
        return primitiveBooleanHolder.isReadOnlyProperty();
    }
}
//end::class[]
