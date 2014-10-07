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
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing a RIF-CS address.
 *
 * @author Scott Yeadon
 *
 */
public class Format extends RIFCSElement {
    /** List of Identifier nodes. */
    private List<Identifier> identifiers = new ArrayList<Identifier>();

    /**
     * Construct aa Format object.
     *
     * @param n
     *        A w3c Node, typically an Element
     *
     * @throws RIFCSException A RIFCSException
     */
    protected Format(final Node n) throws RIFCSException {
        super(n, Constants.ELEMENT_FORMAT);
        initStructures();
    }


    /**
     * Create and return an empty identifer.
     *
     * The returned object has no properties or content and is not part
     * of the RIF-CS document, it is essentially a constructor of an object
     * owned by the RIF-CS document. The returned object needs to be
     * "filled out" (e.g. with properties, additional sub-elements, etc)
     * before being added to the RIF-CS document.
     *
     * @return the new Electronic object
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    public final Identifier newIdentifier() throws RIFCSException {
        return new Identifier(this.newElement(Constants.ELEMENT_IDENTIFIER));
    }


    /**
     * Add an identifier to the format object.
     *
     * @param identifier
     *    an Identifier object
     */
    public final void addIdentifier(final Identifier identifier) {
        this.getElement().appendChild(identifier.getElement());
        this.identifiers.add(identifier);
    }


    /**
     * Convenience method to add an identifier to the format object.
     *
     * @param identifier
     *            an identifier string
     * @param type
     *            the identifier type
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    public final void addIdentifier(final String identifier,
            final String type) throws RIFCSException {
        Identifier i = newIdentifier();
        i.setType(type);
        i.setValue(identifier);
        this.getElement().appendChild(i.getElement());
        this.identifiers.add(i);
    }


    /**
     * Obtain the identifiers for this format.
     *
     * @return
     *      A list of all identifiers within this format.
     */
    public final List<Identifier> getIdentifiers() {
        return this.identifiers;
    }


    /** Initialisation code for existing documents. A wrapper that
     *  invokes initPhysicals() and initElectronics() in turn.
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    private void initStructures() throws RIFCSException {
        initIdentifiers();
    }


    /** Initialisation code for identifier elements.
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    private void initIdentifiers() throws RIFCSException {
        NodeList nl = super.getElements(Constants.ELEMENT_IDENTIFIER);

        for (int i = 0; i < nl.getLength(); i++) {
            identifiers.add(new Identifier(nl.item(i)));
        }
    }
}
