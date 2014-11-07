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
 * Class representing a RIF-CS electronic address object.
 *
 * @author Scott Yeadon
 *
 */
public class Electronic extends RIFCSElement {
    /** List of Notes nodes.
     * @since 3.0.0
     */
    private List<Notes> notes = new ArrayList<Notes>();
    /** List of MediaType nodes.
     * @since 3.0.0
     */
    private List<MediaType> mediaTypes = new ArrayList<MediaType>();
    /** List of Arg nodes. */
    private List<Arg> args = new ArrayList<Arg>();

    /**
     * Construct an Electronic address object.
     *
     * @param n
     *        A w3c Node, typically an Element
     *
     * @throws RIFCSException A RIFCSException
     */
    protected Electronic(final Node n) throws RIFCSException {
        super(n, Constants.ELEMENT_ELECTRONIC);
        initStructures();
    }


    /**
     * Set the type.
     *
     * @param type
     *      The electronic address type
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
     * Set the target.
     *
     * @param target
     *      The electronic target
     * @since 3.0.0
     */
    public final void setTarget(final String target) {
        super.setAttributeValue(Constants.ATTRIBUTE_TARGET, target);
    }


    /**
     * Return the target.
     *
     * @return
     *      The target attribute value or empty string if attribute
     *      is empty or not present
     * @since 3.0.0
     */
    public final String getTarget() {
        return super.getAttributeValue(Constants.ATTRIBUTE_TARGET);
    }


    /**
     * Set the title.
     *
     * @param title
     *    The title of the electronic address
     * @since 3.0.0
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
     *    The title of the electronic address
     * @since 3.0.0
     */
    public final String getTitle() {
        NodeList nl = super.getElements(Constants.ELEMENT_TITLE);
        if (nl.getLength() == 1) {
            return nl.item(0).getTextContent();
        }

        return null;
    }


    /**
     * Add a notes element to the electronic address.
     *
     * @param notesObject
     *    a Notes object
     * @since 3.0.0
     */
    public final void addNotes(final Notes notesObject) {
        this.getElement().appendChild(notesObject.getElement());
        this.notes.add(notesObject);
    }


    /**
     * Obtain the notes elements of this electronic address.
     *
     * @return
     *      A list of Notes objects
     * @since 3.0.0
     */
    public final List<Notes> getNotes() {
        return notes;
    }

    /**
     * Create and return an empty notes element.
     *
     * The returned object has no properties or content and is not part
     * of the RIF-CS document, it is essentially a constructor of an object
     * owned by the RIF-CS document. The returned object needs to be
     * "filled out" (e.g. with properties, additional sub-elements, etc)
     * before being added to the RIF-CS document.
     *
     * @return the new Notes object
     *
     * @throws RIFCSException A RIFCSException
     * @since 3.0.0
     *
     */
    public final Notes newNotes() throws RIFCSException {
        return new Notes(this.newElement(Constants.ELEMENT_NOTES));
    }


    /**
     * Convenience method to add an notes element to the electronic object.
     *
     * @param notesText
     *            notes to be added
     *
     * @throws RIFCSException A RIFCSException
     * @since 3.0.0
     *
     */
    public final void addNotes(final String notesText)
            throws RIFCSException {
        Notes n = newNotes();
        n.setValue(notesText);
        addNotes(n);
    }


    /**
     * Add a media type to the electronic address.
     *
     * @param mediaType
     *    a MediaType object
     * @since 3.0.0
     */
    public final void addMediaType(final MediaType mediaType) {
        this.getElement().appendChild(mediaType.getElement());
        this.mediaTypes.add(mediaType);
    }


    /**
     * Obtain the media types of this electronic resource.
     *
     * @return
     *      A list of MediaType objects
     * @since 3.0.0
     */
    public final List<MediaType> getMediaTypes() {
        return mediaTypes;
    }

    /**
     * Create and return an empty media type element.
     *
     * The returned object has no properties or content and is not part
     * of the RIF-CS document, it is essentially a constructor of an object
     * owned by the RIF-CS document. The returned object needs to be
     * "filled out" (e.g. with properties, additional sub-elements, etc)
     * before being added to the RIF-CS document.
     *
     * @return the new MediaType object
     *
     * @throws RIFCSException A RIFCSException
     * @since 3.0.0
     *
     */
    public final MediaType newMediaType() throws RIFCSException {
        return new MediaType(this.newElement(Constants.ELEMENT_MEDIATYPE));
    }


    /**
     * Convenience method to add a media type element to the electronic object.
     *
     * @param mediaTypeText
     *            media type to be added
     *
     * @throws RIFCSException A RIFCSException
     * @since 3.0.0
     *
     */
    public final void addMediaType(final String mediaTypeText)
            throws RIFCSException {
        MediaType m = newMediaType();
        m.setValue(mediaTypeText);
        addMediaType(m);
    }


    /**
     * Set the byteSize.
     *
     * @param byteSize
     *    The byte size of the electronic address
     * @since 3.0.0
     */
    public final void setByteSize(final String byteSize) {
        Element e = this.newElement(Constants.ELEMENT_BYTESIZE);
        e.setTextContent(byteSize);
        this.getElement().appendChild(e);
    }


    /**
     * Get the byte size.
     *
     * @return String
     *    The byte size of the electronic address
     * @since 3.0.0
     */
    public final String getByteSize() {
        NodeList nl = super.getElements(Constants.ELEMENT_BYTESIZE);
        if (nl.getLength() == 1) {
            return nl.item(0).getTextContent();
        }

        return null;
    }

    /**
     * Create and return an empty Arg object.
     *
     * The returned object has no properties or content and is not part
     * of the RIF-CS document, it is essentially a constructor of an object
     * owned by the RIF-CS document. The returned object needs to be
     * "filled out" (e.g. with properties, additional sub-elements, etc)
     * before being added to the RIF-CS document.
     *
     * @return the new Arg object
     *
     * @throws RIFCSException A RIFCSException
     *
     */
    public final Arg newArg() throws RIFCSException {
        return new Arg(this.newElement(Constants.ELEMENT_ARG));
    }


    /**
     * Add an argument to the electroinc address object.
     *
     * @param name
     *    the name of the argument
     * @param required
     *    <code>true</code> if the argument is required else <code>false</code>
     * @param type
     *    the argument type
     * @param use
     *    the argument use
     * @throws RIFCSException A RIFCSException
     */
    public final void addArg(final String name,
                       final String required,
                       final String type,
                       final String use) throws RIFCSException {
        Arg arg = newArg();
        arg.setName(name);
        arg.setRequired(required);
        arg.setType(type);
        arg.setUse(use);
        addArg(arg);
    }


    /**
     * Obtain the arguments for this electronic address.
     *
     * @return
     *      A list of Arg objects
     */
    public final List<Arg> getArgs() {
        return args;
    }


    /**
     * Add an argument to the electronic address object.
     *
     * @param arg
     *    a completed Arg object
     */
    public final void addArg(final Arg arg) {
       this.getElement().appendChild(arg.getElement());
       this.args.add(arg);
    }


    /**
     * Set the electronic address URI.
     *
     * @param valueUri
     *    a resolvable URI representing the electronic address
     *    of the containing registry object
     */
    public final void setValue(final String valueUri) {
        Element value = this.newElement(Constants.ELEMENT_VALUE);
        value.setTextContent(valueUri);
        this.getElement().appendChild(value);
    }


    /**
     * Return the electronic address URI.
     *
     * @return
     *    a resolvable URI representing the electronic address
     *    of the containing registry object
     */
    public final String getValue() {
        NodeList nl = super.getElements(Constants.ELEMENT_VALUE);
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
        NodeList nl = super.getElements(Constants.ELEMENT_ARG);

        for (int i = 0; i < nl.getLength(); i++) {
            args.add(new Arg(nl.item(i)));
        }
    }
}
