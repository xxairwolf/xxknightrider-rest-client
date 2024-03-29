package org.wiztools.restclient.util;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.wiztools.restclient.XMLException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author subwiz
 */
public final class XMLIndentUtil {

    private XMLIndentUtil() {
    }
    
    public static String getIndented(String inXml) throws IOException {
        try {
            final InputSource src = new InputSource(new StringReader(inXml));
            final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();
            final boolean keepDeclaration = inXml.startsWith("<?xml");

            final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
            final LSSerializer writer = impl.createLSSerializer();

            writer.getDomConfig().setParameter("format-pretty-print", true); // Set this to true if the output needs to be beautified.
            writer.getDomConfig().setParameter("xml-declaration", keepDeclaration); // Set this to true if the declaration is needed to be outputted.

            return writer.writeToString(document);
        }
        catch(ParserConfigurationException ex) {
            throw new XMLException(null, ex);
        }
        catch(SAXException ex) {
            throw new XMLException(null, ex);
        }
        catch(ClassNotFoundException ex) {
            throw new XMLException(null, ex);
        }
        catch(InstantiationException ex) {
            throw new XMLException(null, ex);
        }
        catch(IllegalAccessException ex) {
            throw new XMLException(null, ex);
        }
    }
}
