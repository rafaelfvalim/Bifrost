//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2016.04.30 às 08:28:06 PM BRT 
//

package br.inf.portalfiscal.nfe;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the br.inf.portalfiscal.nfe package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _EnvEvento_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "envEvento");

	private final static QName _RetEnvEvento_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "retEnvEvento");

	private final static QName _ConsNFeDest_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "consNFeDest");

	private final static QName _RetConsNFeDest_QNAME = new QName("http://www.portalfiscal.inf.br/nfe",
			"retConsNFeDest");

	private final static QName _DownloadNFe_QNAME = new QName("http://www.portalfiscal.inf.br/nfe", "downloadNFe");

	private final static QName _RetDownloadNFe_QNAME = new QName("http://www.portalfiscal.inf.br/nfe",
			"retDownloadNFe");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: br.inf.portalfiscal.nfe
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link TEvento }
	 * 
	 */
	public TEvento createTEvento() {
		return new TEvento();
	}

	/**
	 * Create an instance of {@link TEvento.InfEvento }
	 * 
	 */
	public TEvento.InfEvento createTEventoInfEvento() {
		return new TEvento.InfEvento();
	}

	/**
	 * Create an instance of {@link TretEvento }
	 * 
	 */
	public TretEvento createTretEvento() {
		return new TretEvento();
	}

	/**
	 * Create an instance of {@link TEnvEvento }
	 * 
	 */
	public TEnvEvento createTEnvEvento() {
		return new TEnvEvento();
	}

	/**
	 * Create an instance of {@link TRetEnvEvento }
	 * 
	 */
	public TRetEnvEvento createTRetEnvEvento() {
		return new TRetEnvEvento();
	}

	/**
	 * Create an instance of {@link TProcEvento }
	 * 
	 */
	public TProcEvento createTProcEvento() {
		return new TProcEvento();
	}

	/**
	 * Create an instance of {@link TEvento.InfEvento.DetEvento }
	 * 
	 */
	public TEvento.InfEvento.DetEvento createTEventoInfEventoDetEvento() {
		return new TEvento.InfEvento.DetEvento();
	}

	/**
	 * Create an instance of {@link TretEvento.InfEvento }
	 * 
	 */
	public TretEvento.InfEvento createTretEventoInfEvento() {
		return new TretEvento.InfEvento();
	}

	/**
	 * Create an instance of {@link TRetConsNFeDest }
	 * 
	 */
	public TRetConsNFeDest createTRetConsNFeDest() {
		return new TRetConsNFeDest();
	}

	/**
	 * Create an instance of {@link TRetConsNFeDest.Ret }
	 * 
	 */
	public TRetConsNFeDest.Ret createTRetConsNFeDestRet() {
		return new TRetConsNFeDest.Ret();
	}

	/**
	 * Create an instance of {@link TConsNFeDest }
	 * 
	 */
	public TConsNFeDest createTConsNFeDest() {
		return new TConsNFeDest();
	}

	/**
	 * Create an instance of {@link TRetConsNFeDest.Ret.ResNFe }
	 * 
	 */
	public TRetConsNFeDest.Ret.ResNFe createTRetConsNFeDestRetResNFe() {
		return new TRetConsNFeDest.Ret.ResNFe();
	}

	/**
	 * Create an instance of {@link TRetConsNFeDest.Ret.ResCanc }
	 * 
	 */
	public TRetConsNFeDest.Ret.ResCanc createTRetConsNFeDestRetResCanc() {
		return new TRetConsNFeDest.Ret.ResCanc();
	}

	/**
	 * Create an instance of {@link TRetConsNFeDest.Ret.ResCCe }
	 * 
	 */
	public TRetConsNFeDest.Ret.ResCCe createTRetConsNFeDestRetResCCe() {
		return new TRetConsNFeDest.Ret.ResCCe();
	}

	/**
	 * Create an instance of {@link TRetDownloadNFe }
	 * 
	 */
	public TRetDownloadNFe createTRetDownloadNFe() {
		return new TRetDownloadNFe();
	}

	/**
	 * Create an instance of {@link TRetDownloadNFe.RetNFe }
	 * 
	 */
	public TRetDownloadNFe.RetNFe createTRetDownloadNFeRetNFe() {
		return new TRetDownloadNFe.RetNFe();
	}

	/**
	 * Create an instance of {@link TDownloadNFe }
	 * 
	 */
	public TDownloadNFe createTDownloadNFe() {
		return new TDownloadNFe();
	}

	/**
	 * Create an instance of {@link TRetDownloadNFe.RetNFe.ProcNFeGrupoZip }
	 * 
	 */
	public TRetDownloadNFe.RetNFe.ProcNFeGrupoZip createTRetDownloadNFeRetNFeProcNFeGrupoZip() {
		return new TRetDownloadNFe.RetNFe.ProcNFeGrupoZip();
	}

	/**
	 * Create an instance of {@link TRetDownloadNFe.RetNFe.ProcNFe }
	 * 
	 */
	public TRetDownloadNFe.RetNFe.ProcNFe createTRetDownloadNFeRetNFeProcNFe() {
		return new TRetDownloadNFe.RetNFe.ProcNFe();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link TEnvEvento }
	 * {@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "envEvento")
	public JAXBElement<TEnvEvento> createEnvEvento(TEnvEvento value) {
		return new JAXBElement<TEnvEvento>(_EnvEvento_QNAME, TEnvEvento.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link TRetEnvEvento }
	 * {@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "retEnvEvento")
	public JAXBElement<TRetEnvEvento> createRetEnvEvento(TRetEnvEvento value) {
		return new JAXBElement<TRetEnvEvento>(_RetEnvEvento_QNAME, TRetEnvEvento.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link TConsNFeDest }
	 * {@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "consNFeDest")
	public JAXBElement<TConsNFeDest> createConsNFeDest(TConsNFeDest value) {
		return new JAXBElement<TConsNFeDest>(_ConsNFeDest_QNAME, TConsNFeDest.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link TRetConsNFeDest
	 * } {@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "retConsNFeDest")
	public JAXBElement<TRetConsNFeDest> createRetConsNFeDest(TRetConsNFeDest value) {
		return new JAXBElement<TRetConsNFeDest>(_RetConsNFeDest_QNAME, TRetConsNFeDest.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link TDownloadNFe }
	 * {@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "downloadNFe")
	public JAXBElement<TDownloadNFe> createDownloadNFe(TDownloadNFe value) {
		return new JAXBElement<TDownloadNFe>(_DownloadNFe_QNAME, TDownloadNFe.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link TRetDownloadNFe
	 * } {@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.portalfiscal.inf.br/nfe", name = "retDownloadNFe")
	public JAXBElement<TRetDownloadNFe> createRetDownloadNFe(TRetDownloadNFe value) {
		return new JAXBElement<TRetDownloadNFe>(_RetDownloadNFe_QNAME, TRetDownloadNFe.class, null, value);
	}

}
