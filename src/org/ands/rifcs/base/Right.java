/**
 * Date Modified: $Date: 2010-07-08 14:54:07 +1000 (Thu, 08 Jul 2010) $
 * Version: $Revision: 463 $
 *
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
 * Class representing registry object rights.
 *
 * @author Mahmoud Sadeghi
 *
 */
public class Right extends RIFCSElement {

    protected RightsInfo rightsStatement = null;
    protected RightsTypedInfo licence = null;
    protected RightsTypedInfo accessRights = null;

    /**
     * Construct a Rights object.
     *
     * @param n
     *            A w3c Node, typically an Element
     *
     * @throws RIFCSException A RIFCSException
     */
    protected Right(final Node n) throws RIFCSException {
        super(n, Constants.ELEMENT_RIGHTS);
    }

    /**
     * Set the rightsStatement.
     *
     * @param aRightsStatement
     * @throws RIFCSException A RIFCSException
     */
    public final void setRightsStatement(
        final RightsInfo aRightsStatement) throws RIFCSException {
        this.rightsStatement = aRightsStatement;
        this.getElement().appendChild(
            this.rightsStatement.getElement());
    }

    /**
     * Set the rightsStatement Value.
     *
     * @param value
     * @throws RIFCSException A RIFCSException
     */
    public final void setRightsStatement(final String value)
        throws RIFCSException {
        this.setRightsStatement(value, null);
    }

    /**
     * Set the rightsStatement Value and URI.
     *
     * @param value
     * @param rightsUri
     * @throws RIFCSException A RIFCSException
     */
    public final void setRightsStatement(final String value,
                                         final String rightsUri)
        throws RIFCSException {
        RightsInfo aRightsStatement = new RightsInfo(
            this.newElement(Constants.ELEMENT_RIGHTS_STATEMENT));
        aRightsStatement.setValue(value);
        if (rightsUri != null) {
            aRightsStatement.setRightsUri(rightsUri);
        }
        setRightsStatement(aRightsStatement);
    }

    /**
     * return the rightsStatement.
     *
     * @return The rightsStatement
     */
    public final RightsInfo getRightsStatement() {
        return this.rightsStatement;
    }
    /**
     * Set the licence.
     *
     * @param aLicence
     * @throws RIFCSException A RIFCSException
     */
    public final void setLicence(final RightsTypedInfo aLicence)
        throws RIFCSException {
        this.licence = aLicence;
        this.getElement().appendChild(this.licence.getElement());
    }

    /**
     * Set the licence Value.
     *
     * @param value
     * @throws RIFCSException A RIFCSException
     */
    public final void setLicence(final String value)
        throws RIFCSException {
        this.setLicence(value, null);
    }

    /**
     * Set the licence Value and Type.
     *
     * @param value
     * @param type
     * @throws RIFCSException A RIFCSException
     */
    public final void setLicence(final String value,
                                 final String type) throws RIFCSException {
        this.setLicence(value, type, null);
    }

    /**
     * Set the licence Value, URI and Type.
     *
     * @param value
     * @param rightsUri
     * @param type
     * @throws RIFCSException A RIFCSException
     */
    public final void setLicence(final String value,
                                 final String rightsUri, final String type)
        throws RIFCSException {
        RightsTypedInfo aLicence = new RightsTypedInfo(
            this.newElement(Constants.ELEMENT_LICENCE));
        aLicence.setValue(value);
        if (rightsUri != null) {
            aLicence.setRightsUri(rightsUri);
        }
        if (type != null) {
            aLicence.setType(type);
        }
        setLicence(aLicence);
    }

    /**
     * return the licence.
     *
     * @return The licence
     */
    public final RightsTypedInfo getLicence() {
        return this.licence;
    }

    /**
     * Set the accessRights.
     *
     * @param anAccessRights
     * @throws RIFCSException A RIFCSException
     */
    public final void setAccessRights(
        final RightsTypedInfo anAccessRights) throws RIFCSException {
        this.accessRights = anAccessRights;
        this.getElement().appendChild(this.accessRights.getElement());
    }

    /**
     * Set the accessRightsValue.
     *
     * @param value
     * @throws RIFCSException A RIFCSException
     */
    public final void setAccessRights(final String value)
        throws RIFCSException {
        this.setAccessRights(value, null);
    }

    /**
     * Set the accessRights Value and Type.
     *
     * @param value
     * @param type
     * @throws RIFCSException A RIFCSException
     */
    public final void setAccessRights(final String value,
                                      final String type) throws RIFCSException {
        this.setAccessRights(value, type, null);
    }

    /**
     * Set the accessRights Value, URI and Type.
     *
     * @param value
     * @param rightsUri
     * @param type
     * @throws RIFCSException A RIFCSException
     */
    public final void setAccessRights(final String value,
                                      final String rightsUri, final String type)
        throws RIFCSException {
        RightsTypedInfo anAccessRights =
            new RightsTypedInfo(
                this.newElement(Constants.ELEMENT_ACCESS_RIGHTS));
        anAccessRights.setValue(value);
        if (rightsUri != null) {
            anAccessRights.setRightsUri(rightsUri);
        }
        if (type != null) {
            anAccessRights.setType(type);
        }
        setLicence(anAccessRights);
    }

    /**
     * return the accessRights.
     *
     * @return The accessRights
     */
    public final RightsTypedInfo getAccessRights() {
        return this.accessRights;
    }


}
