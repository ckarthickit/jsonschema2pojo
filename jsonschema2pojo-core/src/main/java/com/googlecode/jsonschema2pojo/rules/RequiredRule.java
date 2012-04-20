/**
 * Copyright © 2010-2011 Nokia
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

package com.googlecode.jsonschema2pojo.rules;

import com.fasterxml.jackson.databind.JsonNode;
import com.googlecode.jsonschema2pojo.Schema;
import com.sun.codemodel.JDocComment;
import com.sun.codemodel.JDocCommentable;

/**
 * Applies the "required" schema rule.
 * 
 * @see <a
 *      href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.7">http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.7</a>
 */
public class RequiredRule implements SchemaRule<JDocCommentable, JDocComment> {

    /**
     * Text added to JavaDoc to indicate that a field is required
     */
    public static final String REQUIRED_COMMENT_TEXT = "\n(Required)";

    protected RequiredRule() {
    }

    /**
     * Applies this schema rule to take the required code generation steps.
     * <p>
     * The required rule simply adds a note to the JavaDoc comment to mark a
     * property as required.
     * 
     * @param nodeName
     *            the name of the schema node for which this "required" rule has
     *            been added
     * @param node
     *            the "required" node, having a value <code>true</code> or
     *            <code>false</code>
     * @param generatableType
     *            the class or method which may be marked as "required"
     * @return the JavaDoc comment attached to the generatableType, which
     *         <em>may</em> have an added not to mark this construct as
     *         required.
     */
    @Override
    public JDocComment apply(String nodeName, JsonNode node, JDocCommentable generatableType, Schema schema) {
        JDocComment javadoc = generatableType.javadoc();

        if (node.asBoolean()) {
            javadoc.append(REQUIRED_COMMENT_TEXT);
        }

        return javadoc;
    }
}
