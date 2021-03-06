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
 * Class representing subelements of a RIF-CS Rights object.
 * This is for subelements of a rights element that
 * only have a rightsUri attribute. (As of RIF-CS v1.5, that
 * means the rightsStatement element.
 *
 * Cf. the RightsTypedInfo class.
 *
 * @author Mahmoud Sadeghi
 *
 */
public class RightsInfo extends RIFCSElement {
    /**
     * Construct a RightsInfo object.
     *
     * @param n
     *        A w3c Node, typically an Element
     *
     * @throws RIFCSException A RIFCSException
     */
    protected RightsInfo(final Node n) throws RIFCSException {
        super(n, n.getNodeName());
    }


    /**
     * Set the rightsUri.
     *
     * @param rightsUri
     *          The rightsUri of RightsInfo
     */
    public final void setRightsUri(final String rightsUri) {
        super.setAttributeValue(Constants.ATTRIBUTE_RIGHTS_URI, rightsUri);
    }


    /**
     * return the rightsUri.
     *
     * @return
     *      The type attribute value or empty string if attribute
     *      is empty or not present
     */
    public final String getRightsUri() {
        return super.getAttributeValue(Constants.ATTRIBUTE_RIGHTS_URI);
    }


    /**
     * Set the content.
     *
     * @param value
     *      The content of the RightsInfo
     */
    public final void setValue(final String value) {
        super.setTextContent(value);
    }


    /**
     * Obtain the content.
     *
     * @return
     *      The RightsInfo string
     */
    public final String getValue() {
        return super.getTextContent();
    }
}
