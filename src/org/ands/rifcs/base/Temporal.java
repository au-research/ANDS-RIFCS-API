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
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing a RIF-CS address.
 *
 * @author Scott Yeadon
 *
 */
public class Temporal extends RIFCSElement {
    /**
     * Pattern to use to make the SimpleDateFormat for formatting dates.
     */
    private static final String TEMPORAL_DATE_FORMAT =
            "yyyy-MM-dd'T'HH:mm:ssZ";
    /**
     * The length of TEMPORAL_DATE_FORMAT.
     */
    private static final int TEMPORAL_DATE_FORMAT_LENGTH =
            TEMPORAL_DATE_FORMAT.length();
    /** The date information for this temporal coverage. */
    private List<DateWithTypeDateFormat> dates =
            new ArrayList<DateWithTypeDateFormat>();
    /** The text information for this temporal coverage. */
    private List<Element> texts = new ArrayList<Element>();

    /**
     * Construct a Temporal object.
     *
     * @param n
     *        A w3c Node, typically an Element
     *
     * @throws RIFCSException A RIFCSException
     */
    protected Temporal(final Node n) throws RIFCSException {
        super(n, Constants.ELEMENT_TEMPORAL);
        initStructures();
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
     * Obtain the date information for this temporal coverage.
     *
     * @return
     *      A list of DateWithTypeDateFormat objects
     */
    public final List<DateWithTypeDateFormat> getDates() {
        return this.dates;
    }


    /**
     * Obtain the text information for this temporal coverage.
     *
     * @return
     *      A list of string values
     */
    public final List<String> getText() {
        ArrayList<String> al = new ArrayList<String>();
        for (Iterator<Element> i = texts.iterator(); i.hasNext();) {
            al.add(i.next().getTextContent());
        }
        return al;
    }


    /**
     * Add text information to the temporal object.
     *
     * @param text
     *    a text description of the temporal coverage
     */
    public final void addText(final String text) {
        Element e = this.newElement(Constants.ELEMENT_TEXT);
        e.setTextContent(text);
        this.getElement().appendChild(e);
        this.texts.add((Element) e);
    }


    /**
     * Add temporal date to the coverage object. A convenience method
     * creating a single temporal element with a date element.
     *
     * @param date
     *      The date to add to the date element.
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
     * Add temporal date to the coverage object. A convenience method
     * creating a single temporal element with a date element.
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
        this.dates.add((DateWithTypeDateFormat) de);
    }

    /**
     * Add temporal date to the coverage object. A convenience method
     * creating a single temporal element with a date element.
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
        DateFormat df = new SimpleDateFormat(TEMPORAL_DATE_FORMAT);
        String text = df.format(date);
        String result = text.substring(0, TEMPORAL_DATE_FORMAT_LENGTH)
                + ":" + text.substring(TEMPORAL_DATE_FORMAT_LENGTH);
        this.addDate(result, type);
    }


    /** Initialisation code for existing documents. A wrapper that
     *  invokes initTexts() and initDates() in turn.
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    private void initStructures() throws RIFCSException {
        initTexts();
        initDates();
    }

    /** Initialisation code for element elements.
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    private void initTexts() throws RIFCSException {
        NodeList nl = super.getElements(Constants.ELEMENT_TEXT);

        for (int i = 0; i < nl.getLength(); i++) {
            texts.add((Element) nl.item(i));
        }
    }

    /** Initialisation code for date elements.
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    private void initDates() throws RIFCSException {
        NodeList nl = super.getElements(Constants.ELEMENT_DATE);

        for (int i = 0; i < nl.getLength(); i++) {
            dates.add(new DateWithTypeDateFormat(nl.item(i)));
        }
    }
}
