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

import java.util.Date;

import org.w3c.dom.Node;

/**
 * Class representing a RIF-CS date with type and dateFormat attributes.
 *
 * @author Scott Yeadon
 *
 */
public class DateWithTypeDateFormat extends RIFCSElement {
    /**
     * Construct a DateWithTypeDateFormat object.
     *
     * @param n
     *        A w3c Node, typically an Element
     *
     * @throws RIFCSException A RIFCSException
     */
    protected DateWithTypeDateFormat(final Node n) throws RIFCSException {
        super(n, Constants.ELEMENT_DATE);
    }

    /**
     * Set the dateFormat.
     *
     * @param dateFormat
     *          The date format
     */
    public final void setDateFormat(final String dateFormat) {
        super.setAttributeValue(Constants.ATTRIBUTE_DATE_FORMAT, dateFormat);
    }


    /**
     * return the dateFormat.
     *
     * @return
     *      The date format value or empty string if attribute
     *      is empty or not present
     */
    public final String getDateFormat() {
        return super.getAttributeValue(Constants.ATTRIBUTE_DATE_FORMAT);
    }



    /**
     * Set the type.
     *
     * @param type
     *          The type of date
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
     * Set the content.
     *
     * @param value
     *      The content of the date
     */
    public final void setValue(final String value) {
        super.setTextContent(value);
    }


    /**
     * Set the content.
     *
     * @param value
     *      The content of the date
     */
    public final void setValue(final Date value) {
        super.setTextContent(RegistryObject.formatDate(value));
    }


    /**
     * Obtain the content.
     *
     * @return
     *      The description string
     */
    public final String getValue() {
        return super.getTextContent();
    }
}
