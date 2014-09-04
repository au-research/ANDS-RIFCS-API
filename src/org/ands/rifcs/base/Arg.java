/**
 * Copyright 2009 The Australian National University (ANU)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ands.rifcs.base;

import org.w3c.dom.Node;

/**
 * Class representing a RIF-CS Arg.
 *
 * @author Scott Yeadon
 *
 */
public class Arg extends RIFCSElement {
    /**
     * Construct an Arg object.
     *
     * @param n
     *        A w3c Node, typically an Element
     *
     * @throws RIFCSException A RIFCSException
     */
    protected Arg(final Node n) throws RIFCSException {
        super(n, Constants.ELEMENT_ARG);
    }


    /**
     * Set the type.
     *
     * @param type
     *          The type of argument
     */
    public final void setType(final String type) {
        super.setAttributeValue(Constants.ATTRIBUTE_TYPE, type);
    }


    /**
     * return the type.
     *
     * @return
     *      The type attribute value or empty string if attribute
     *      is empty or not present
     */
    public final String getType() {
        return super.getAttributeValue(Constants.ATTRIBUTE_TYPE);
    }


    /**
     * Set whether the argument is mandatory.
     *
     * @param required
     *      <code>true</code> or <code>false</code>
     */
    public final void setRequired(final String required) {
        super.setAttributeValue(Constants.ATTRIBUTE_REQUIRED, required);
    }


    /**
     * return whether the argument is mandatory.
     *
     * @return
     *  The attribute value or empty string if attribute
     *  is empty or not present
     */
    public final String getRequired() {
        return super.getAttributeValue(Constants.ATTRIBUTE_REQUIRED);
    }


    /**
     * Set the use of the argument.
     *
     * @param use
     *          term indicating the use of the argument
     */
    public final void setUse(final String use) {
        super.setAttributeValue(Constants.ATTRIBUTE_USE, use);
    }


    /**
     * return the use of the argument.
     *
     * @return
     *  The attribute value or empty string if attribute
     *  is empty or not present
     */
    public final String getUse() {
        return super.getAttributeValue(Constants.ATTRIBUTE_USE);
    }


    /**
     * Set the name of the argument.
     *
     * @param name
     *      The argument name
     */
    public final void setName(final String name) {
        super.setTextContent(name);
    }


    /**
     * return the name of the argument.
     *
     * @return
     *      The name or empty string if not set
     */
    public final String getName() {
        return super.getTextContent();
    }
}
