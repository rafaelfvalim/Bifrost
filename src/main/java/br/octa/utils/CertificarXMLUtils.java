package br.octa.utils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jcp.xml.dsig.internal.dom.XMLDSigRI;
//import org.apache.jcp.xml.dsig.internal.dom.XMLDSigRI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.octa.enums.TipoInterfaceEnum;

public class CertificarXMLUtils {
	private static final String C14N_TRANSFORM_METHOD = "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";

	// Procedimento de assinar XML

	public String assinaXML(String xMLInput, String tag, TipoInterfaceEnum tipoInterface) throws Exception {
		PropertyUtils prop  = new PropertyUtils();
		String arquivoCertificado = prop.getValue("arquivo_certificado");
		String senhaCertificado = prop.getValue("senha_certificado");
		String temp = System.getProperty("java.io.tmpdir");
		String xMLOutput = temp+tipoInterface.value()+"_"+DateUtils.getDateAsString("hhmmssddMMyy")+".xml";
		XMLSignatureFactory sig = null;
		SignedInfo si1 = null;
		KeyInfo ki1 = null;
		// String strTipoSign = tag;
		String strID = "";

		// Capturo o certificado
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		ByteArrayInputStream stream = new ByteArrayInputStream(xMLInput.getBytes());
		Document docs = builder.parse(stream);	

		NodeList elementos = docs.getElementsByTagName(tag);

		Element e = (Element) elementos.item(0);
		System.out.println("XML " + e.getTextContent());
		String id = e.getAttribute("Id");
		e.setIdAttribute("Id", true);
		// XMLDSigRI()
		XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", new XMLDSigRI());

		ArrayList<Transform> transformList = new ArrayList<Transform>();
		TransformParameterSpec tps = null;
		Transform envelopedTransform = fac.newTransform(Transform.ENVELOPED, tps);
		Transform c14NTransform = fac.newTransform(C14N_TRANSFORM_METHOD, tps);
		transformList.add(envelopedTransform);
		transformList.add(c14NTransform);

		Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null,
				null);
		SignedInfo si = fac.newSignedInfo(
				fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
				fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref));

		KeyStore ks = KeyStore.getInstance("PKCS12");
		ks.load(new FileInputStream(arquivoCertificado), senhaCertificado.toCharArray());
		Enumeration<String> aliasesEnum = ks.aliases();
		String alias = "";
		while (aliasesEnum.hasMoreElements()) {
			alias = (String) aliasesEnum.nextElement();
			if (ks.isKeyEntry(alias)) {
				break;
			}
		}

		KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias,
				new KeyStore.PasswordProtection(senhaCertificado.toCharArray()));

		X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

		KeyInfoFactory kif = fac.getKeyInfoFactory();
		List<X509Certificate> x509Content = new ArrayList<X509Certificate>();

		x509Content.add(cert);
		X509Data xd = kif.newX509Data(x509Content);
		KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));


		DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(),
				docs.getDocumentElement().getElementsByTagName(tag).item(0));

		XMLSignature signature = fac.newXMLSignature(si, ki);
		signature.sign(dsc);

		OutputStream os = new FileOutputStream(xMLOutput);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		trans.transform(new DOMSource(docs), new StreamResult(xMLOutput));
		
		NodeList nl = docs.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
		String linhas = IoUtils.readFile(xMLOutput);

		String b64Encode = Base64Utils.encode(linhas);
		String b64Decode = Base64Utils.decode(b64Encode);

		if (nl.getLength() == 0) {
			throw new Exception("Cannot find Signature element");
		}
		DOMValidateContext valContext = new DOMValidateContext(new X509KeySelector(ks), nl.item(0));
		XMLSignature signatures = fac.unmarshalXMLSignature(valContext);
		return new String(Files.readAllBytes(Paths.get(xMLOutput)));
	}
	
	
}
