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
 * Class representing related object information.
 *
 * @author Scott Yeadon
 *
 */
public class RelatedObject extends RIFCSElement {
    /** List of Relation nodes. */
    private List<Relation> relations = new ArrayList<Relation>();

    /**
     * Construct a RelatedObject object.
     *
     * @param n
     *        A w3c Node, typically an Element
     *
     * @throws RIFCSException A RIFCSException
     */
    protected RelatedObject(final Node n) throws RIFCSException {
        super(n, Constants.ELEMENT_RELATED_OBJECT);
        initStructures();
    }


    /**
     * Set the related object key value.
     *
     * @param keyValue
     *      The key uniquely identifying the related registry object
     */
    public final void setKey(final String keyValue) {
        Element key = this.newElement(Constants.ELEMENT_KEY);
        key.setTextContent(keyValue);
        this.getElement().appendChild(key);
    }


    /**
     * Get the related object key value.
     *
     * @return
     *     The key uniquely identifying the related registry object
     */
    public final String getKey() {
        NodeList nl = super.getElements(Constants.ELEMENT_KEY);
        if (nl.getLength() == 1) {
            return nl.item(0).getTextContent();
        }

        return null;
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


    /** Initialisation code for existing documents.
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    private void initStructures() throws RIFCSException {
        NodeList nl = super.getElements(Constants.ELEMENT_RELATION);

        for (int i = 0; i < nl.getLength(); i++) {
            relations.add(new Relation(nl.item(i)));
        }
    }
}
