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
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.ands.rifcs.base.Address;
import org.ands.rifcs.base.Coverage;
import org.ands.rifcs.base.Electronic;
import org.ands.rifcs.base.Format;
import org.ands.rifcs.base.Identifier;
import org.ands.rifcs.base.Location;
import org.ands.rifcs.base.Name;
import org.ands.rifcs.base.NamePart;
import org.ands.rifcs.base.RIFCS;
import org.ands.rifcs.base.RIFCSException;
import org.ands.rifcs.base.RIFCSWrapper;
import org.ands.rifcs.base.RegistryObject;
import org.ands.rifcs.base.RelatedInfo;
import org.ands.rifcs.base.RelatedObject;
import org.ands.rifcs.base.Relation;
import org.ands.rifcs.base.Rights;
import org.ands.rifcs.base.Service;
import org.ands.rifcs.base.Spatial;
import org.ands.rifcs.base.Temporal;
import org.xml.sax.SAXException;

/** Example of the API that builds a service registry object.
 *  This tests the additions and changes for RIF-CS v1.5.
 */
public final class SampleRIFCSBuildServiceV1Dot5Number1 {

    /** The RIF-CS object. */
    private static RIFCS rifcs = null;

    /** This class can not be instantiated. */
    private SampleRIFCSBuildServiceV1Dot5Number1() {
    }

    /** The main method.
     *  @param args The command-line arguments.
     *  @throws RIFCSException A RIFCSException
     *  @throws FileNotFoundException A FileNotFoundException
     *  @throws SAXException A SAXException
     *  @throws ParserConfigurationException A ParserConfigurationException
     *  @throws IOException An IOException
     */
    public static void main(final String[] args) throws RIFCSException,
    FileNotFoundException, SAXException, ParserConfigurationException,
    IOException {
        RIFCSWrapper mw = new RIFCSWrapper();
        rifcs = mw.getRIFCSObject();
        RegistryObject r = rifcs.newRegistryObject();
        r.setKey("Service");
        r.setGroup("ANDS");
        r.setOriginatingSource("http://myrepository.au.edu");
        Service s = r.newService();
        s.setType("service");

        Identifier identifier = s.newIdentifier();
        identifier.setType("handle");
        identifier.setValue("hdl:7651/myhandlesuffix");
        s.addIdentifier(identifier);

        Name n = s.newName();
        n.setType("primary");
        NamePart np = n.newNamePart();
        np.setValue("Sample Collection");
        n.addNamePart(np);
        s.addName(n);

        Location l = s.newLocation();
        Address ad = l.newAddress();
        Electronic e = ad.newElectronic();
        e.setValue("http://myrepository.au.edu/collections/collection1");
        e.setType("url");
        ad.addElectronic(e);
        l.addAddress(ad);
        s.addLocation(l);

        Coverage cov = s.newCoverage();
        Spatial sp = cov.newSpatial();
        Temporal tmp = cov.newTemporal();
        tmp.addDate("1999-3-4", "dateFrom", "W3C");
        tmp.addDate("1999-3-4", "dateFrom", "W3C");
        sp.setValue("126.773437,-23.598894 127.652343,-27.405585 "
                + "131.519531,-27.093039 131.167968,-24.081241 "
                + "130.464843,-20.503868 127.828124,-19.843884 "
                + "123.960937,-20.339134 123.433593,-22.141282 "
                + "123.433593,-25.040485 123.785156,-28.183080 "
                + "126.773437,-23.598894");
        sp.setType("kmlPolyCoords");
        cov.addSpatial(sp);
        cov.addTemporal(tmp);
        s.addCoverage(cov);

        RelatedObject ro = s.newRelatedObject();
        ro.setKey("collection1");
        ro.addRelation("isOutputOf", null, null, null);
        s.addRelatedObject(ro);
        RelatedObject ro2 = s.newRelatedObject();
        ro2.setKey("party1");
        ro2.addRelation("isOwnerOf", null, null, null);
        s.addRelatedObject(ro2);
        RelatedObject ro3 = s.newRelatedObject();
        ro3.setKey("service1");
        ro3.addRelation("supports", null, null, null);
        s.addRelatedObject(ro3);

        s.addSubject("subject1", "local", "identifier1" , "en");
        s.addSubject("subject2", "local", "identifier2" , "en");

        s.addDescription("This is a sample description", "brief", null);

        s.addAccessPolicy("Access Policy");

        Rights rights = s.newRights();
        rights.setAccessRights("Access Rights Value", "Access Rights Uri",
                "Access Rights Type");
        rights.setLicence("Licence Value", "Licence Uri", "Licence Type");
        rights.setRightsStatement("Rights Statement Value",
                "Rights Statement Uri");
        s.addRights(rights);
        rights = s.newRights();
        rights.setAccessRights("Access Rights Value2", "Access Rights Uri2",
                "Access Rights Type2");
        rights.setLicence("Licence Value2", "Licence Uri2", "Licence Type2");
        rights.setRightsStatement("Rights Statement Value2",
                "Rights Statement Uri2");
        s.addRights(rights);

        s.addExistenceDates("01-01-01", "dd-mm-yy", "12-12-12", "dd-mm-yy");

        RelatedInfo relatedInfo = s.newRelatedInfo();
        relatedInfo.addIdentifier("related info", "text");
        relatedInfo.addIdentifier(
                "http://external-server.edu/related-page2.htm",
                "uri");
        Relation rel = relatedInfo.newRelation();
        rel.setType("relationType");
        rel.setDescription("relation description");
        rel.setURL("http://external-server.edu/relation.htm");
        relatedInfo.addRelation(rel);
         rel = relatedInfo.newRelation();
        rel.setType("relationType2");
        rel.setDescription("relation description2");
        rel.setURL("http://external-server.edu/relation2.htm");
        relatedInfo.addRelation(rel);
        Format format = relatedInfo.newFormat();
        format.addIdentifier("format id1", "format type1");
        format.addIdentifier("format id2", "format type2");
        relatedInfo.setFormat(format);
        relatedInfo.setNotes("Notes");
        relatedInfo.setTitle("Title");
        relatedInfo.setType("Type");
        s.addRelatedInfo(relatedInfo);
        relatedInfo = s.newRelatedInfo();
        relatedInfo.addIdentifier("related info1", "text");
        relatedInfo.setNotes("Notes1");
        relatedInfo.setTitle("Title1");
        relatedInfo.setType("Type");
        s.addRelatedInfo(relatedInfo);

        r.addService(s);

        rifcs.addRegistryObject(r);
        mw.write(System.out);
        mw.validate();
    }

}
