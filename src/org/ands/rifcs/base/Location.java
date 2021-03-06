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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing a RIF-CS location object.
 *
 * @author Scott Yeadon
 *
 */
public class Location extends RIFCSElement {
    /** List of Address nodes. */
    private List<Address> addresses = new ArrayList<Address>();
    /** List of Spatial nodes. */
    private List<Spatial> spatials = new ArrayList<Spatial>();


    /**
     * Construct an Location object.
     *
     * @param n
     *        A w3c Node, typically an Element
     *
     * @throws RIFCSException A RIFCSException
     */
    protected Location(final Node n) throws RIFCSException {
        super(n, Constants.ELEMENT_LOCATION);
        initStructures();
    }


    /**
     * Set the date the location was relevant from.
     *
     * @param dateFrom
     *      A date object representing the date the contained location
     *      information was valid from
     */
    public final void setDateFrom(final Date dateFrom) {
        super.setAttributeValue(Constants.ATTRIBUTE_DATE_FROM,
                RegistryObject.formatDate(dateFrom));
    }


    /**
     * Set the date the location was relevant from.
     *
     * @param dateFrom
     *      A string in UTC and of one of the forms described in section 3.2.7
     *      of the <a href="http://www.w3.org/TR/xmlschema-2/">W3C's Schema
     *      Data Types document</a>
     */
    public final void setDateFrom(final String dateFrom) {
        super.setAttributeValue(Constants.ATTRIBUTE_DATE_FROM, dateFrom);
    }


    /**
     * Get the date the location was relevant from.
     *
     * @return The value of the dateFrom attribute.
     *
     */
    public final String getDateFrom() {
        return super.getAttributeValue(Constants.ATTRIBUTE_DATE_FROM);
    }


    /**
     * Set the date the location was relevant to.
     *
     * @param dateTo
     *      A date object representing the date the contained location
     *      information was valid to
     */
    public final void setDateTo(final Date dateTo) {
        super.setAttributeValue(Constants.ATTRIBUTE_DATE_TO,
                RegistryObject.formatDate(dateTo));
    }


    /**
     * Set the date the location was relevant to.
     *
     * @param dateTo
     *      A string in UTC and of one of the forms described in section 3.2.7
     *      of the <a href="http://www.w3.org/TR/xmlschema-2/">W3C's Schema
     *      Data Types document</a>
     */
    public final void setDateTo(final String dateTo) {
        super.setAttributeValue(Constants.ATTRIBUTE_DATE_TO, dateTo);
    }


    /**
     * Get the date the location was relevant from.
     *
     * @return The value of the dateTo attribute.
     *
     */
    public final String getDateTo() {
        return super.getAttributeValue(Constants.ATTRIBUTE_DATE_TO);
    }


    /**
     * Create and return an empty Address object.
     *
     * The returned object has no properties or content and is not part
     * of the RIF-CS document, it is essentially a constructor of an object
     * owned by the RIF-CS document. The returned object needs to be
     * "filled out" (e.g. with properties, additional sub-elements, etc)
     * before being added to the RIF-CS document.
     *
     * @return the new Address object
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    public final Address newAddress() throws RIFCSException {
        return new Address(this.newElement(Constants.ELEMENT_ADDRESS));
    }


    /**
     * Add an address to the location object.
     *
     * @param address
     *    a completed Address object
     */
    public final void addAddress(final Address address) {
        this.getElement().appendChild(address.getElement());
        this.addresses.add(address);
    }


    /**
     * Obtain the addresses for this location.
     *
     * @return
     *      A list of Address objects
     */
    public final List<Address> getAddresses() {
        return this.addresses;
    }


    /**
     * Create and return an empty Spatial object.
     *
     * The returned object has no properties or content and is not part
     * of the RIF-CS document, it is essentially a constructor of an object
     * owned by the RIF-CS document. The returned object needs to be
     * "filled out" (e.g. with properties, additional sub-elements, etc)
     * before being added to the RIF-CS document.
     *
     * @return the new Spatial object
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    public final Spatial newSpatial() throws RIFCSException {
        return new Spatial(this.newElement(Constants.ELEMENT_SPATIAL));
    }


    /**
     * Add spatial information to the location object.
     *
     * @param spatial
     *    a completed Spatial object.
     */
    public final void addSpatial(final Spatial spatial) {
        this.getElement().appendChild(spatial.getElement());
        this.spatials.add(spatial);
    }


    /**
     * Obtain the spatial information for this location.
     *
     * @return
     *      A list of Spatial objects
     */
    public final List<Spatial> getSpatials() {
        return this.spatials;
    }


    /** Initialisation code for existing documents. A wrapper that
     *  invokes initSpatials() and initAddresses() in turn.
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    private void initStructures() throws RIFCSException {
        initSpatials();
        initAddresses();
    }

    /** Initialisation code for spatial elements.
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    private void initSpatials() throws RIFCSException {
        NodeList nl = super.getElements(Constants.ELEMENT_SPATIAL);

        for (int i = 0; i < nl.getLength(); i++) {
            spatials.add(new Spatial(nl.item(i)));
        }
    }

    /** Initialisation code for address elements.
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    private void initAddresses() throws RIFCSException {
        NodeList nl = super.getElements(Constants.ELEMENT_ADDRESS);

        for (int i = 0; i < nl.getLength(); i++) {
            addresses.add(new Address(nl.item(i)));
        }
    }
}
