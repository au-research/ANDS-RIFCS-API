/**
 * Copyright 2008 The Australian National University (ANU)
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

// THIS CLASS NOT CURRENTLY USED
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * superclass of all RIF-CS object classes (class not currently used).
 *
 * @author Scott Yeadon
 *
 */
public class ROElement {
    /** The element. */
    private Element e = null;

    /**
     * Construct a RIF-CS element.
     *
     * @param n
     *        A w3c Node, typically an Element
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    protected ROElement(final Node n) throws RIFCSException {
        if (n == null) {
            throw new RIFCSException("Null Node passed to constructor");
        }

        if (!(n instanceof Element)) {
            throw new RIFCSException(
                    "Node of type Element required in constructor");
        }

        String name = n.getNodeName();

        if (!name.equals(Constants.ELEMENT_ACTIVITY)
                && !name.equals(Constants.ELEMENT_COLLECTION)
                && !name.equals(Constants.ELEMENT_PARTY)
                && !name.equals(Constants.ELEMENT_SERVICE)) {
            throw new RIFCSException("Invalid regsitry object type: " + name);
        }

//        this.getElement().getOwnerDocument().
//          createElementNS(Constants.NS_RIFCS, name);
        e = (Element) n;
    }


    /**
     * Obtain an attribute value.
     *
     * @param name
     *      The name of the attribute
     *
     * @return
     *      The attribute value or empty string if attribute
     *      is empty or not present
     */
    protected final String getAttributeValue(final String name) {
        return e.getAttribute(name);
    }


    /**
     * Set an attribute value.
     *
     * @param name
     *      The name of the attribute
     *
     * @param value
     *      The attribute value
     */
    protected final void setAttributeValue(final String name,
                                     final String value) {
        e.setAttribute(name, value);
    }


    /**
     * Set an attribute value with namespace.
     *
     * @param ns
     *      The namespace URL of the attribute
     * @param name
     *      The attribute value
     * @param value
     *      The attribute value
     */
    protected final void setAttributeValueNS(final String ns,
                                        final String name,
                                     final String value) {
        e.setAttributeNS(ns, name, value);
    }


    /**
     * Obtain an attribute value where the attribute has a
     * namespace.
     *
     * @param ns
     *      The attribute namespace URL
     * @param localName
     *      The unqualified attribute name
     *
     * @return
     *      The attribute value or empty string if attribute
     *      is empty or not present
     */
    protected final String getAttributeValue(final String ns,
                                       final String localName) {
        return e.getAttributeNS(ns, localName);
    }


    /**
     * Obtain the text content of the RIFCS Element.
     *
     * @return
     *      The text content of the element
     */
    protected final String getText() {
        return e.getTextContent();
    }


    /**
     * Set the text content of the RIFCS Element.
     *
     * @param value
     *      The text content of the element
     */
    protected final void setText(final String value) {
        e.setTextContent(value);
    }


    /**
     * Obtain a list of descendant RIFCS elements with the given name.
     *
     * @param localName
     *      Obtain a list of descendant RIF-CS elements
     *
     * @return org.w3c.dom.NodeList
     *      The text content of the element
     */
    protected final NodeList getElements(final String localName) {
        return e.getElementsByTagNameNS(Constants.NS_RIFCS, localName);
    }


    /**
     * Obtain a list of child RIFCS elements.
     *
     * @param localName
     *      An element name
     *
     * @return
     *      A list of RIFCS elements whose tag name matches
     *      the localName
     */
    protected final List<Node> getChildElements(final String localName) {
        NodeList nl = e.getChildNodes();
        List<Node> l = new ArrayList<Node>();
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE
                    && nl.item(i).getLocalName().equals(localName)) {
                l.add(nl.item(i));
            }
        }

        return l;
    }


    /**
     * Obtain the w3c dom element this object represents.
     *
     * @return
     *      A w3c dom element
     */
    protected final Element getElement() {
        return e;
    }


    /**
     * Create and return an empty generic Element object.
     *
     * The returned object has no properties or content and is not part
     * of the RIF-CS document, it is essentially a constructor of an object
     * owned by the RIF-CS document. The returned object needs to be
     * "filled out" (e.g. with properties, additional sub-elements, etc)
     * before being added to the RIF-CS document.
     *
     * @param elementName
     *      the name of the element type
     *
     * @return
     *      an element with the given name
     */
    protected final Element newElement(final String elementName) {
        return this.getElement().getOwnerDocument().
                createElementNS(Constants.NS_RIFCS, elementName);
    }
}
