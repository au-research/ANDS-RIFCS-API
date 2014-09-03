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
 * Class representing registry object existence date.
 *
 * @author Mahmoud Sadeghi
 *
 */
public class ExistenceDate extends RIFCSElement {

    /** The start date. */
    private CommonDateElement startDate = null;
    /** The end date. */
    private CommonDateElement endDate = null;

    /**
     * Construct an ExistenceDate object.
     *
     * @param n
     *            A w3c Node, typically an Element
     *
     * @throws RIFCSException A RIFCSException
     */
    protected ExistenceDate(final Node n) throws RIFCSException {
        super(n, Constants.ELEMENT_EXISTENSE_DATES);
    }

    /**
     * Set the startDate.
     *
     * @param value The date to be set.
     * @param dateFormat The date format to be used.
     * @throws RIFCSException A RIFCSException
     */
    public final void setStartDate(final String value,
            final String dateFormat) throws RIFCSException {
        CommonDateElement dateElement =  new CommonDateElement(
                this.newElement(Constants.ELEMENT_START_DATE));
        dateElement.setDateFormat(dateFormat);
        dateElement.setValue(value);
        this.startDate = dateElement;
        this.getElement().appendChild(this.startDate.getElement());
    }
    /**
     * return the StartDate.
     *
     * @return The StartDate
     */
    public final CommonDateElement getStartDate() {
        return this.startDate;
    }

    /**
     * Set the endDate.
     *
     * @param value The date to be set.
     * @param dateFormat The date format to be used.
     * @throws RIFCSException A RIFCSException
     */
    public final void setEndDate(final String value,
            final String dateFormat) throws RIFCSException {
        CommonDateElement dateElement =  new CommonDateElement(
                this.newElement(Constants.ELEMENT_END_DATE));
        dateElement.setDateFormat(dateFormat);
        dateElement.setValue(value);
        this.endDate = dateElement;
        this.getElement().appendChild(this.endDate.getElement());
    }


    /**
     * return the EndDate.
     *
     * @return The EndDate
     */
    public final CommonDateElement getEndDate() {
        return this.endDate;
    }
}
