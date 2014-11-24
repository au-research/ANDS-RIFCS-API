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
import org.ands.rifcs.base.CitationDate;
import org.ands.rifcs.base.CitationInfo;
import org.ands.rifcs.base.CitationMetadata;
import org.ands.rifcs.base.Collection;
import org.ands.rifcs.base.Contributor;
import org.ands.rifcs.base.Coverage;
import org.ands.rifcs.base.Dates;
import org.ands.rifcs.base.Electronic;
import org.ands.rifcs.base.Format;
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
import org.ands.rifcs.base.Spatial;
import org.ands.rifcs.base.Temporal;
import org.xml.sax.SAXException;

/** Example of the API that builds a collection registry object.
 *  This tests the additions and changes for RIF-CS v1.6.
 */
public final class SampleRIFCSBuildCollectionV1Dot6Number1 {

    /** The RIF-CS object. */
    private static RIFCS rifcs = null;

    /** This class can not be instantiated. */
    private SampleRIFCSBuildCollectionV1Dot6Number1() {
    }

    /** The main method.
     *  @param args The command-line arguments.
     *  @throws RIFCSException A RIFCSException
     *  @throws FileNotFoundException A FileNotFoundException
     *  @throws SAXException A SAXException
     *  @throws ParserConfigurationException A ParserConfigurationException
     *  @throws IOException An IOException
     */
    public static void main(final String[] args) throws
    RIFCSException, FileNotFoundException, SAXException,
    ParserConfigurationException, IOException {
        RIFCSWrapper mw = new RIFCSWrapper();
        rifcs = mw.getRIFCSObject();
        RegistryObject r = rifcs.newRegistryObject();
        r.setKey("collection1");
        r.setGroup("ANDS");
        r.setOriginatingSource("http://myrepository.au.edu");
        Collection c = r.newCollection();
        c.setType("collection");
        c.addIdentifier("hdl:7651/myhandlesuffix", "handle");
        Dates d = c.newDates();
        d.setType("datetype");
        d.addDate("2014-09-08T09:46:00Z", "datetype2");
        d.addDate("2014-09-08T09:47:00Z", "datetype22");
        c.addDates(d);
        d = c.newDates();
        d.setType("datetype3");
        d.addDate("2014-09-08T13:23:00Z", "datetype4");
        d.addDate("2014-09-08T13:24:00Z", "datetype42");
        c.addDates(d);
        Rights rights = c.newRights();
        rights.setAccessRights("Access Rights Value",
                "Access Rights Uri", "Access Rights Type");
        rights.setLicence("Licence Value", "Licence Uri", "Licence Type");
        rights.setRightsStatement("Rights Statement Value",
                "Rights Statement Uri");
        c.addRights(rights);
        rights = c.newRights();
        rights.setAccessRights("Access Rights Value2",
                "Access Rights Uri2", "Access Rights Type2");
        rights.setLicence("Licence Value2", "Licence Uri2", "Licence Type2");
        rights.setRightsStatement("Rights Statement Value2",
                "Rights Statement Uri2");
        c.addRights(rights);
        Name n = c.newName();
        n.setType("primary");
        NamePart np = n.newNamePart();
        np.setValue("Sample Collection");
        n.addNamePart(np);
        c.addName(n);
        Location l = c.newLocation();
        Address a = l.newAddress();
        Electronic e = a.newElectronic();
        e.setValue("http://myrepository.au.edu/collections/collection1");
        e.setType("url");
        e.setTarget("target");
        e.setTitle("title");
        e.addNotes("notes 1");
        e.addNotes("notes 2");
        e.addMediaType("mediaType 1");
        e.addMediaType("mediaType 2");
        e.setByteSize("1234");
        e.addArg("name 1", "true", "type 1", "use 1");
        e.addArg("name 2", "false", "type 2", "use 2");
        a.addElectronic(e);
        l.addAddress(a);
        c.addLocation(l);
        RelatedObject ro = c.newRelatedObject();
        ro.setKey("activity1");
        ro.addRelation("isOutputOf", null, null, null);
        c.addRelatedObject(ro);
        RelatedObject ro2 = c.newRelatedObject();
        ro2.setKey("party1");
        ro2.addRelation("isOwnerOf", null, null, null);
        c.addRelatedObject(ro2);
        RelatedObject ro3 = c.newRelatedObject();
        ro3.setKey("service1");
        ro3.addRelation("supports", null, null, null);
        c.addRelatedObject(ro3);
        c.addSubject("subject1", "local", null);
        c.addSubject("subject2", "local", null);
        Coverage cov = c.newCoverage();
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
        c.addCoverage(cov);
        c.addDescription("This is a sample description", "brief", null);
        RelatedInfo ri = c.newRelatedInfo();
        ri.addIdentifier("http://external-server.edu/related-page.htm", "uri");
        ri.addIdentifier("http://external-server.edu/related-page2.htm",
                "uri");
        Relation rel = ri.newRelation();
        rel.setType("relationType");
        rel.setDescription("relation description");
        rel.setURL("http://external-server.edu/relation.htm");
        ri.addRelation(rel);
         rel = ri.newRelation();
        rel.setType("relationType2");
        rel.setDescription("relation description2");
        rel.setURL("http://external-server.edu/relation2.htm");
        ri.addRelation(rel);
        Format format = ri.newFormat();
        format.addIdentifier("format id1", "format type1");
        format.addIdentifier("format id2", "format type2");
        ri.setFormat(format);
        ri.setTitle("A related information resource");
        ri.setNotes("Notes about the related information resource");
        c.addRelatedInfo(ri);
        CitationInfo ci = c.newCitationInfo();
        ci.setCitation("sasdgasdgsdgasdgasdgasdgasdgasdgsadgasdgasdgsg",
                "howardsss");
        c.addCitationInfo(ci);
        CitationInfo ci2 = c.newCitationInfo();
        CitationMetadata cim = ci2.newCitationMetadata();
        cim.setIdentifier("sdjhksdghkashdgkjashgd", "pod");
        Contributor cCont = cim.newContributor();
        cCont.setSeq(0);
        cCont.addNamePart("Monus", "surname");
        cCont.addNamePart("Leo", "sgiven");
        cim.addContributor(cCont);
        cim.setTitle("ashgfjhsagfjashgf");
        cim.setVersion("versionksjadhkjsahf");
        cim.setPublisher("publishersjdhgkjahsdgkahgkahsdkghaksdghkajhg");
        cim.setPlacePublished("sjdhgkjahsdgkahgkahsdkghaksdghkajhg");
        CitationDate cd = cim.newCitationDate();
        cd.setType("cdtype");
        cd.setValue("2014-09-08T11:25Z");
        cim.addDate(cd);
        cim.setURL("sdjhgksjhgdk");
        cim.setContext("shdgjsgjasgjahdsgjsd");
        c.addCitationInfo(ci2);
        ci2.addCitationMetadata(cim);
//        c.addDescription("漢字仮名交じり文漢字仮名交じり文漢字仮名交じり"
//                + "文漢字仮名交じり文漢字仮名交じり文漢字仮名交じり"
//                + "文漢字仮名交じり文漢字仮名交じり文漢字仮名交じり"
//                + "文漢字仮名交じり文漢字仮名交じり文漢字仮名交じり"
//                + "文漢字仮名交じり文", "full", "eng");
        c.addDescription("漢字仮名交じり文漢字仮名交じり文漢字仮名交じり"
                + "文漢字仮名交じり文漢字仮名交じり文漢字仮名交じり"
                + "文漢字仮名交じり文漢字仮名交じり文漢字仮名交じり"
                + "文漢字仮名交じり文漢字仮名交じり文漢字仮名交じり"
                + "文漢字仮名交じり文", "full", null);
        r.addCollection(c);
        rifcs.addRegistryObject(r);
        mw.write(System.out);
        mw.validate();
    }

}
