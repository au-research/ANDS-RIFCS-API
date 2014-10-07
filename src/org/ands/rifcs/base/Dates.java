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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing a RIF-CS dates object.
 *
 * @author Scott Yeadon
 *
 */
public class Dates extends RIFCSElement {
    /**
     * Pattern to use to make the SimpleDateFormat for formatting dates.
     */
    private static final String DATE_FORMAT =
            "yyyy-MM-dd'T'HH:mm:ssZ";
    /**
     * The length of TEMPORAL_DATE_FORMAT.
     */
    private static final int DATE_FORMAT_LENGTH =
            DATE_FORMAT.length();
    /** List of date nodes. */
    private List<DateWithTypeDateFormat> dateList =
            new ArrayList<DateWithTypeDateFormat>();

    /**
     * Construct a Dates object.
     *
     * @param n
     *        A w3c Node, typically an Element
     *
     * @throws RIFCSException A RIFCSException
     */
    protected Dates(final Node n) throws RIFCSException {
        super(n, Constants.ELEMENT_DATES);
        initStructures();
    }


    /**
     * Set the type.
     *
     * @param type
     *          The type of name
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
     * Obtain the dates for this dates.
     *
     * @return
     *      A list of DateWithTypeDateFormat objects
     */
    public final List<DateWithTypeDateFormat> getDates() {
        return dateList;
    }

    /**
     * Create and return an empty DateWithTypeDateFormat object.
     *
     * The returned object has no properties or content and is not part
     * of the RIF-CS document, it is essentially a constructor of an object
     * owned by the RIF-CS document. The returned object needs to be
     * "filled out" (e.g. with properties, additional sub-elements, etc)
     * before being added to the RIF-CS document.
     *
     * @return the new Date object
     *
     * @throws RIFCSException A RIFCSException
     *
     */

    public final DateWithTypeDateFormat newDate() throws RIFCSException {
        return new DateWithTypeDateFormat(this.newElement(
                Constants.ELEMENT_DATE));
    }

    /**
     * Add a date to the dates object. A convenience method
     * creating a single date element. The dateFormat
     * is assumed to be "W3C".
     *
     * @param date
     *      The date to add to the dates element.
     * @param type
     *      The type of date
     * @throws RIFCSException A RIFCSException
     *
     */
    public final void addDate(final String date, final String type)
            throws RIFCSException {
        this.addDate(date, type, "W3C");
    }

    /**
     * Add a date to the dates object. A convenience method
     * creating a single date element.
     *
     * @param date
     *      The date to add to the date element.
     * @param type
     *      The type of date.
     * @param dateFormat
     *      The date format.
     * @throws RIFCSException A RIFCSException
     *
     */
    public final void addDate(final String date,
            final String type, final String dateFormat) throws RIFCSException {
        DateWithTypeDateFormat de = this.newDate();
        de.setType(type);
        de.setDateFormat(dateFormat);
        de.setValue(date);
        this.getElement().appendChild(de.getElement());
        this.dateList.add((DateWithTypeDateFormat) de);
    }

    /**
     * Add temporal date to the coverage object. A convenience method
     * creating a single temporal element with a date element.
     * The dateFormat is assumed to be "W3C".
     *
     * @param date
     *      The date to add to the date element.
     * @param type
     *      The type of date
     * @throws RIFCSException A RIFCSException
     *
     */
    public final void addDate(final Date date,
            final String type) throws RIFCSException {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        String text = df.format(date);
        String result = text.substring(0, DATE_FORMAT_LENGTH)
                + ":" + text.substring(DATE_FORMAT_LENGTH);
        this.addDate(result, type);
    }


    /** Initialisation code for existing documents.
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    private void initStructures() throws RIFCSException {
        NodeList nl = super.getElements(Constants.ELEMENT_DATE);

        for (int i = 0; i < nl.getLength(); i++) {
            dateList.add(new DateWithTypeDateFormat(nl.item(i)));
        }
    }

}
