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
package demoapp.dom.ActionLayout;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;

import lombok.extern.log4j.Log4j2;

@DomainService(nature=NatureOfService.VIEW, objectType = "demo.ActionLayoutMenu")
@DomainObjectLayout(named="ActionLayout")
@Log4j2
public class ActionLayoutMenu {

    @Action(semantics = SemanticsOf.SAFE)
    public demoapp.dom.ActionLayout.position.ActionLayoutPositionVm position(){
        return new demoapp.dom.ActionLayout.position.ActionLayoutPositionVm();
    }

    @Action(semantics = SemanticsOf.SAFE)
    public demoapp.dom.ActionLayout.promptStyle.ActionLayoutPromptStyleVm promptStyle(){
        return new demoapp.dom.ActionLayout.promptStyle.ActionLayoutPromptStyleVm();
    }


}
