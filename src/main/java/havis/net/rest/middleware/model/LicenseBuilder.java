package havis.net.rest.middleware.model;

import havis.middleware.ale.base.exception.ImplementationException;
import havis.middleware.ale.service.mc.ImplementationExceptionResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LicenseBuilder {

	/**
	 * 
	 * @param license
	 *            License string without signature
	 * @throws ImplementationException
	 * @throws ImplementationExceptionResponse
	 */
	public static License getLicense(String license) throws ImplementationException {
		try {
			byte[] decoded = DatatypeConverter.parseBase64Binary(license);
			return evaluateLicenseWithXml(decoded);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			throw new ImplementationException(e);
		}
	}

	/**
	 * Evaluates the license String with XML Doc
	 * 
	 * @param licenseString
	 * @return the corresponding license object
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	private static License evaluateLicenseWithXml(byte[] licenseString) throws SAXException, IOException, ParserConfigurationException {
		License license = new License();
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = db.parse(new ByteArrayInputStream(licenseString));
		NodeList xmlLicense = doc.getElementsByTagName("license");
		if (xmlLicense.getLength() > 0) {
			Element xmlLicenseElement = (Element) xmlLicense.item(0);
			NodeList xmlLicensee = xmlLicenseElement.getElementsByTagName("licensee");
			if (xmlLicensee.getLength() > 0) {
				license.setLicensee(xmlLicensee.item(0).getTextContent());
			}

			NodeList xmlBoundary = xmlLicenseElement.getElementsByTagName("boundary");
			if (xmlBoundary.getLength() > 0) {
				Element xmlBoundaryElement = (Element) xmlBoundary.item(0);
				NodeList xmlStart = xmlBoundaryElement.getElementsByTagName("start");
				if (xmlStart.getLength() > 0) {
					license.setStartingTime(xmlStart.item(0).getTextContent());
				}

				NodeList xmlEnd = xmlBoundaryElement.getElementsByTagName("stop");
				if (xmlStart.getLength() > 0) {
					license.setEndTime(xmlEnd.item(0).getTextContent());
				}
			}
		}
		return license;
	}

	/**
	 * Evaluates the license String with Regular Expressions
	 * 
	 * @param decoded
	 * @return the corresponding license object
	 */
	@SuppressWarnings("unused")
	private static License evaluateLicenseWithRegEx(byte[] decoded) {
		License license = new License();
		String licenseString = new String(decoded);

		Pattern licenseePattern = Pattern.compile("<license>.*<licensee>(.*)<\\/licensee>.*<\\/license>");
		Matcher licenseeMatcher = licenseePattern.matcher(licenseString);
		if (licenseeMatcher.find()) {
			license.setLicensee(licenseeMatcher.group(1));
		}
		Pattern startingTimePattern = Pattern.compile("<license>.*<boundary>.*<date>.*<start>(.*)<\\/start>.*<\\/date>.*<\\/boundary>.*<\\/license>");
		Matcher startingTimeMatcher = startingTimePattern.matcher(licenseString);
		if (startingTimeMatcher.find()) {
			license.setStartingTime(startingTimeMatcher.group(1));
		}
		Pattern endTimePattern = Pattern.compile("<license>.*<boundary>.*<date>.*<stop>(.*)<\\/stop>.*<\\/date>.*<\\/boundary>.*<\\/license>");
		Matcher endTimeMatcher = endTimePattern.matcher(licenseString);
		if (endTimeMatcher.find()) {
			license.setEndTime(endTimeMatcher.group(1));
		}
		return license;
	}
}