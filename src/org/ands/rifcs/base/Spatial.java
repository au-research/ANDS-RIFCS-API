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
 * Class representing a RIF-CS Spatial object.
 *
 * @author Scott Yeadon
 *
 */
public class Spatial extends RIFCSElement {
    /**
     * Construct a Spatial object.
     *
     * @param n
     *        A w3c Node, typically an Element
     *
     * @throws RIFCSException A RIFCSException
     */
    protected Spatial(final Node n) throws RIFCSException {
        super(n, Constants.ELEMENT_SPATIAL);
    }


    /**
     * Set the type.
     *
     * @param type
     *          The type of spatial information
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
    * Set the language.
    *
    * @param lang
    *      The xml:lang attribute value
    */
    public final void setLanguage(final String lang) {
        super.setAttributeValueNS(Constants.NS_XML,
                Constants.ATTRIBUTE_LANG, lang);
    }


    /**
     * Obtain the language.
     *
     * @return
     *      The language or empty string if attribute
     *      is empty or not present
     */
    public final String getLanguage() {
        return super.getAttributeValueNS(Constants.NS_XML,
                Constants.ATTRIBUTE_LANG);
    }

    /**
     * Set the content.
     *
     * @param value
     *      The spatial value
     */
    public final void setValue(final String value) {
        super.setTextContent(value);
    }


    /**
     * Obtain the content.
     *
     * @return
     *      The spatial
     */
    public final String getValue() {
        return super.getTextContent();
    }

}
