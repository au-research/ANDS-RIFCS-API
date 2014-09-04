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
package org.ands.rifcs.example;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.ands.rifcs.base.Collection;
import org.ands.rifcs.base.Name;
import org.ands.rifcs.base.NamePart;
import org.ands.rifcs.base.RIFCS;
import org.ands.rifcs.base.RIFCSException;
import org.ands.rifcs.base.RIFCSWrapper;
import org.ands.rifcs.base.RegistryObject;

import org.ands.rifcs.ch.RIFCSReader;

import org.xml.sax.SAXException;

/** Example of the API that reads RIF-CS data from a file. */
public final class SampleRIFCSRead {
    /** The RIF-CS object. */
    private static RIFCS rifcs = null;

    /** This class can not be instantiated. */
    private SampleRIFCSRead() {
    }

    /** The main method.
     *  @param args The command-line arguments. args[0] is the name
     *          of a file to be read.
     *  @throws RIFCSException A RIFCSException
     *  @throws FileNotFoundException A FileNotFoundException
     *  @throws SAXException A SAXException
     *  @throws ParserConfigurationException A ParserConfigurationException
     *  @throws IOException An IOException
     *  @throws MalformedURLException A MalformedURLException
     */
    public static void main(final String[] args) throws RIFCSException,
    FileNotFoundException, SAXException, ParserConfigurationException,
    IOException, MalformedURLException {
        RIFCSReader rr = new RIFCSReader();
        rr.mapToDOM(new FileInputStream(args[0]));
        RIFCSWrapper rw = new RIFCSWrapper(rr.getDocument());
        rw.validate();
        rifcs = rw.getRIFCSObject();

        List<RegistryObject> list = rifcs.getCollections();
        for (Iterator<RegistryObject> i = list.iterator(); i.hasNext();) {
            RegistryObject ro = (RegistryObject) i.next();
            Collection c = (Collection) ro.getClassObject();
            Iterator<Name> j = c.getNames().iterator();
            while (j.hasNext()) {
                Name n = (Name) j.next();
                if (n.getType().equals("primary")) {
                    Iterator<NamePart> k = n.getNameParts().iterator();
                    while (k.hasNext()) {
                        System.out.println(new StringBuilder().
                                append(((NamePart) k.next()).
                                        getValue()).append(" (").
                                        append(ro.getKey()).append(")").
                                        toString());
                    }
                }
            }
        }
    }
}
