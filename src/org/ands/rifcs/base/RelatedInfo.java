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

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class representing registry object related information.
 *
 * @author Scott Yeadon
 *
 */
public class RelatedInfo extends RIFCSElement {
    /** The identifiers of this related info. */
    private List<Identifier> identifiers = new ArrayList<Identifier>();
    /** List of Relation nodes. */
    private List<Relation> relations = new ArrayList<Relation>();
    /** The format of this related info. */
    private Format format = null;

    /**
     * Construct a RelatedInfo object.
     *
     * @param n
     *        A w3c Node, typically an Element
     *
     * @throws RIFCSException A RIFCSException
     */
    protected RelatedInfo(final Node n) throws RIFCSException {
        super(n, Constants.ELEMENT_RELATED_INFO);
        initStructures();
    }


    /**
     * Set the type.
     *
     * @param type
     *          The type of related information
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
     * Create and return an empty Identifier object.
     *
     * The returned object has no properties or content and is not part
     * of the RIF-CS document, it is essentially a constructor of an object
     * owned by the RIF-CS document. The returned object needs to be
     * "filled out" (e.g. with properties, additional sub-elements, etc)
     * before being added to the RIF-CS document.
     *
     * @return the new Identifier object
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    public final Identifier newIdentifier() throws RIFCSException {
        return new Identifier(this.newElement(Constants.ELEMENT_IDENTIFIER));
    }


    /**
     * Add an identifier to the relatedInfo object.
     *
     * @param anIdentifier
     *    a completed Identifier object
     */
    public final void addIdentifier(final Identifier anIdentifier) {
        this.getElement().appendChild(anIdentifier.getElement());
        this.identifiers.add(anIdentifier);
    }


    /**
     * Convenience method to add an identifier to the relatedInfo object.
     *
     * @param anIdentifier
     *      The identifier of the related information resource
     * @param type
     *      The type of the identifier
     * @throws RIFCSException A RIFCSException
     *
     */
    public final void addIdentifier(final String anIdentifier,
            final String type) throws RIFCSException {
        Identifier newIdentifier = this.newIdentifier();
        newIdentifier.setValue(anIdentifier);
        newIdentifier.setType(type);
        this.addIdentifier(newIdentifier);
    }

    /**
     * Obtain the identifiers for this location.
     *
     * @return
     *      A list of Identifier objects
     */
    public final List<Identifier> getIdentifiers() {
        return this.identifiers;
    }


    /**
     * Create and return an empty Relation object.
     *
     * The returned object has no properties or content and is not part
     * of the RIF-CS document, it is essentially a constructor of an object
     * owned by the RIF-CS document. The returned object needs to be
     * "filled out" (e.g. with properties, additional sub-elements, etc)
     * before being added to the RIF-CS document.
     *
     * @return the new Relation object
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    public final Relation newRelation() throws RIFCSException {
        return new Relation(this.newElement(Constants.ELEMENT_RELATION));
    }


    /**
     * Add a related object relation.
     *
     * @param type
     *          The type of relation being described
     * @param url
     *    A URL expressing or implementing the relationship
     *    between registry objects
     * @param description
     *    String describing the relation or null
     * @param descriptionLanguage
     *    The xml:lang attribute value
     * @throws RIFCSException A RIFCSException
     */
    public final void addRelation(final String type,
                            final String url,
                            final String description,
                            final String descriptionLanguage)
                                    throws RIFCSException {
        Relation relation = newRelation();

        relation.setType(type);

        if (url != null) {
            relation.setURL(url);
        }

        if (description != null) {
            relation.setDescription(description);
        }

        if (descriptionLanguage != null) {
            relation.setDescriptionLanguage(descriptionLanguage);
        }

        addRelation(relation);
    }


    /**
     * Obtain the relations for this collection.
     *
     * @return
     *      A list of Relation objects
     */
    public final List<Relation> getRelations() {
        return relations;
    }


    /**
     * Add a related object relation.
     *
     * @param relation
     *    A Relation object
     */
    public final void addRelation(final Relation relation) {
        this.getElement().appendChild(relation.getElement());
        this.relations.add(relation);
    }

    /**
     * Create and return an empty Format object.
     *
     * The returned object has no properties or content and is not part
     * of the RIF-CS document, it is essentially a constructor of an object
     * owned by the RIF-CS document. The returned object needs to be
     * "filled out" (e.g. with properties, additional sub-elements, etc)
     * before being added to the RIF-CS document.
     *
     * @return the new Format object
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    public final Format newFormat() throws RIFCSException {
        return new Format(this.newElement(Constants.ELEMENT_FORMAT));
    }

    /**
     * Set the format.
     *
     * @param aFormat
     *    a Format element representing the format
     *    of the containing registry object
     */
    public final void setFormat(final Format aFormat) {
        this.format = aFormat;
        this.getElement().appendChild(aFormat.getElement());
    }


    /**
     * Return the format.
     *
     * @return
     *    a Format element representing the format
     *    of the containing registry object
     */
    public final Format getFormat() {
        return this.format;
    }

    /**
     * Set the title.
     *
     * @param title
     *    The title of the related information resource
     */
    public final void setTitle(final String title) {
        Element e = this.newElement(Constants.ELEMENT_TITLE);
        e.setTextContent(title);
        this.getElement().appendChild(e);
    }


    /**
     * Get the title.
     *
     * @return String
     *    The title of the related information resource
     */
    public final String getTitle() {
        NodeList nl = super.getElements(Constants.ELEMENT_TITLE);
        if (nl.getLength() == 1) {
            return nl.item(0).getTextContent();
        }

        return null;
    }


    /**
     * Set the notes.
     *
     * @param notes
     *    Notes relevant to the related information resource
     */
    public final void setNotes(final String notes) {
        Element e = this.newElement(Constants.ELEMENT_NOTES);
        e.setTextContent(notes);
        this.getElement().appendChild(e);
    }


    /**
     * Get the notes.
     *
     * @return String
     *    The title of the related information resource
     */
    public final String getNotes() {
        NodeList nl = super.getElements(Constants.ELEMENT_NOTES);
        if (nl.getLength() == 1) {
            return nl.item(0).getTextContent();
        }

        return null;
    }


    /** Initialisation code for existing documents.
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    private void initStructures() throws RIFCSException {
        initIdentifiers();
        initRelations();
        initFormat();
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

    /** Initialisation code for relation elements.
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    private void initRelations() throws RIFCSException {
        NodeList nl = super.getElements(Constants.ELEMENT_RELATION);

        for (int i = 0; i < nl.getLength(); i++) {
            relations.add(new Relation(nl.item(i)));
        }
    }

    /** Initialisation code for relation elements.
    *
    * @throws RIFCSException A RIFCSException
    *
    */
   private void initFormat() throws RIFCSException {
       NodeList nl = super.getElements(Constants.ELEMENT_FORMAT);

       if (nl.getLength() > 0) {
           this.format = new Format(nl.item(0));
       }
   }

}
