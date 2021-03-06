<%--

    See the NOTICE file distributed with
    this work for additional information regarding copyright ownership.
    con terra GmbH licenses this file to You under the Apache License, Version 2.0
    (the "License"); you may not use this file except in compliance with
    the License. You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="editor" uri="http://www.52north.org/tags/editor" %>

<tiles:useAttribute name="cnt" id="smlIdentification_cnt"/>

<editor:fragments modelAttribute="updateMetadata">
    <table class="marginLine">
        <tr>
            <td>
                <label for="smlIdentification_${smlIdentification_cnt}_Definition" class="firstLabel">
                    <fmt:message key="element.smlTerm.definition"/>
                </label></td><td>
                <form:input path="storage['smlIdentification'].items[${smlIdentification_cnt}].definition" size="100"
                            id="smlIdentification_${smlIdentification_cnt}_Definition"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="smlIdentification_${smlIdentification_cnt}_Label" class="firstLabel">
                    <fmt:message key="element.smlTerm.label"/>
                </label></td><td>
                <form:input path="storage['smlIdentification'].items[${smlIdentification_cnt}].label" size="100"
                            id="smlIdentification_${smlIdentification_cnt}_Label"/>
            </td>
        </tr>
         <tr>
            <td>
                <label for="smlIdentification_${smlIdentification_cnt}_CodeSpace" class="firstLabel">
                    <fmt:message key="element.smlTerm.codeSpace"/>
                </label></td><td>
                <form:input path="storage['smlIdentification'].items[${smlIdentification_cnt}].codeSpace" size="100"
                            id="smlIdentification_${smlIdentification_cnt}_CodeSpace"/>
            </td>
        </tr>
        <tr>
            <td>
                <label for="smlIdentification_${smlIdentification_cnt}_Value" class="firstLabel">
                    <fmt:message key="element.smlTerm.value"/>
                </label></td><td>
                <form:input path="storage['smlIdentification'].items[${smlIdentification_cnt}].value" size="100"
                            id="smlIdentification_${smlIdentification_cnt}_Value"/>
            </td>
        </tr>
    </table>
</editor:fragments>



