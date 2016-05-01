//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2016.05.01 às 10:40:36 AM BRT 
//


package br.inf.portalfiscal.nfe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.w3c.dom.Element;


/**
 * Tipo Retorno do pedido de Download de NF-e
 * 
 * <p>Classe Java de TRetDownloadNFe complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="TRetDownloadNFe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/nfe}TAmb"/>
 *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/>
 *         &lt;element name="cStat" type="{http://www.portalfiscal.inf.br/nfe}TStat"/>
 *         &lt;element name="xMotivo" type="{http://www.portalfiscal.inf.br/nfe}TMotivo"/>
 *         &lt;element name="dhResp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="retNFe" maxOccurs="10" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/nfe}TChNFe"/>
 *                   &lt;element name="cStat" type="{http://www.portalfiscal.inf.br/nfe}TStat"/>
 *                   &lt;element name="xMotivo" type="{http://www.portalfiscal.inf.br/nfe}TMotivo"/>
 *                   &lt;choice minOccurs="0">
 *                     &lt;element name="procNFeGrupoZip">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element name="NFeZip" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                               &lt;element name="protNFeZip" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                             &lt;/sequence>
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                     &lt;element name="procNFeZip" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *                     &lt;element name="procNFe">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;any processContents='skip'/>
 *                             &lt;/sequence>
 *                             &lt;attribute name="schema" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/choice>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/nfe}TVerDownloadNFe" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRetDownloadNFe", propOrder = {
    "tpAmb",
    "verAplic",
    "cStat",
    "xMotivo",
    "dhResp",
    "retNFe"
})
public class TRetDownloadNFe {

    @XmlElement(required = true)
    protected String tpAmb;
    @XmlElement(required = true)
    protected String verAplic;
    @XmlElement(required = true)
    protected String cStat;
    @XmlElement(required = true)
    protected String xMotivo;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dhResp;
    protected List<TRetDownloadNFe.RetNFe> retNFe;
    @XmlAttribute(name = "versao", required = true)
    protected String versao;

    /**
     * Obtém o valor da propriedade tpAmb.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpAmb() {
        return tpAmb;
    }

    /**
     * Define o valor da propriedade tpAmb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpAmb(String value) {
        this.tpAmb = value;
    }

    /**
     * Obtém o valor da propriedade verAplic.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerAplic() {
        return verAplic;
    }

    /**
     * Define o valor da propriedade verAplic.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerAplic(String value) {
        this.verAplic = value;
    }

    /**
     * Obtém o valor da propriedade cStat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCStat() {
        return cStat;
    }

    /**
     * Define o valor da propriedade cStat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCStat(String value) {
        this.cStat = value;
    }

    /**
     * Obtém o valor da propriedade xMotivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMotivo() {
        return xMotivo;
    }

    /**
     * Define o valor da propriedade xMotivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMotivo(String value) {
        this.xMotivo = value;
    }

    /**
     * Obtém o valor da propriedade dhResp.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDhResp() {
        return dhResp;
    }

    /**
     * Define o valor da propriedade dhResp.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDhResp(XMLGregorianCalendar value) {
        this.dhResp = value;
    }

    /**
     * Gets the value of the retNFe property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the retNFe property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRetNFe().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TRetDownloadNFe.RetNFe }
     * 
     * 
     */
    public List<TRetDownloadNFe.RetNFe> getRetNFe() {
        if (retNFe == null) {
            retNFe = new ArrayList<TRetDownloadNFe.RetNFe>();
        }
        return this.retNFe;
    }

    /**
     * Obtém o valor da propriedade versao.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersao() {
        return versao;
    }

    /**
     * Define o valor da propriedade versao.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersao(String value) {
        this.versao = value;
    }


    /**
     * <p>Classe Java de anonymous complex type.
     * 
     * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/nfe}TChNFe"/>
     *         &lt;element name="cStat" type="{http://www.portalfiscal.inf.br/nfe}TStat"/>
     *         &lt;element name="xMotivo" type="{http://www.portalfiscal.inf.br/nfe}TMotivo"/>
     *         &lt;choice minOccurs="0">
     *           &lt;element name="procNFeGrupoZip">
     *             &lt;complexType>
     *               &lt;complexContent>
     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                   &lt;sequence>
     *                     &lt;element name="NFeZip" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                     &lt;element name="protNFeZip" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *                   &lt;/sequence>
     *                 &lt;/restriction>
     *               &lt;/complexContent>
     *             &lt;/complexType>
     *           &lt;/element>
     *           &lt;element name="procNFeZip" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
     *           &lt;element name="procNFe">
     *             &lt;complexType>
     *               &lt;complexContent>
     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                   &lt;sequence>
     *                     &lt;any processContents='skip'/>
     *                   &lt;/sequence>
     *                   &lt;attribute name="schema" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;/restriction>
     *               &lt;/complexContent>
     *             &lt;/complexType>
     *           &lt;/element>
     *         &lt;/choice>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "chNFe",
        "cStat",
        "xMotivo",
        "procNFeGrupoZip",
        "procNFeZip",
        "procNFe"
    })
    public static class RetNFe {

        @XmlElement(required = true)
        protected String chNFe;
        @XmlElement(required = true)
        protected String cStat;
        @XmlElement(required = true)
        protected String xMotivo;
        protected TRetDownloadNFe.RetNFe.ProcNFeGrupoZip procNFeGrupoZip;
        protected byte[] procNFeZip;
        protected TRetDownloadNFe.RetNFe.ProcNFe procNFe;

        /**
         * Obtém o valor da propriedade chNFe.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getChNFe() {
            return chNFe;
        }

        /**
         * Define o valor da propriedade chNFe.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setChNFe(String value) {
            this.chNFe = value;
        }

        /**
         * Obtém o valor da propriedade cStat.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCStat() {
            return cStat;
        }

        /**
         * Define o valor da propriedade cStat.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCStat(String value) {
            this.cStat = value;
        }

        /**
         * Obtém o valor da propriedade xMotivo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXMotivo() {
            return xMotivo;
        }

        /**
         * Define o valor da propriedade xMotivo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXMotivo(String value) {
            this.xMotivo = value;
        }

        /**
         * Obtém o valor da propriedade procNFeGrupoZip.
         * 
         * @return
         *     possible object is
         *     {@link TRetDownloadNFe.RetNFe.ProcNFeGrupoZip }
         *     
         */
        public TRetDownloadNFe.RetNFe.ProcNFeGrupoZip getProcNFeGrupoZip() {
            return procNFeGrupoZip;
        }

        /**
         * Define o valor da propriedade procNFeGrupoZip.
         * 
         * @param value
         *     allowed object is
         *     {@link TRetDownloadNFe.RetNFe.ProcNFeGrupoZip }
         *     
         */
        public void setProcNFeGrupoZip(TRetDownloadNFe.RetNFe.ProcNFeGrupoZip value) {
            this.procNFeGrupoZip = value;
        }

        /**
         * Obtém o valor da propriedade procNFeZip.
         * 
         * @return
         *     possible object is
         *     byte[]
         */
        public byte[] getProcNFeZip() {
            return procNFeZip;
        }

        /**
         * Define o valor da propriedade procNFeZip.
         * 
         * @param value
         *     allowed object is
         *     byte[]
         */
        public void setProcNFeZip(byte[] value) {
            this.procNFeZip = value;
        }

        /**
         * Obtém o valor da propriedade procNFe.
         * 
         * @return
         *     possible object is
         *     {@link TRetDownloadNFe.RetNFe.ProcNFe }
         *     
         */
        public TRetDownloadNFe.RetNFe.ProcNFe getProcNFe() {
            return procNFe;
        }

        /**
         * Define o valor da propriedade procNFe.
         * 
         * @param value
         *     allowed object is
         *     {@link TRetDownloadNFe.RetNFe.ProcNFe }
         *     
         */
        public void setProcNFe(TRetDownloadNFe.RetNFe.ProcNFe value) {
            this.procNFe = value;
        }


        /**
         * <p>Classe Java de anonymous complex type.
         * 
         * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;any processContents='skip'/>
         *       &lt;/sequence>
         *       &lt;attribute name="schema" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "any"
        })
        public static class ProcNFe {

            @XmlAnyElement
            protected Element any;
            @XmlAttribute(name = "schema", required = true)
            protected String schema;

            /**
             * Obtém o valor da propriedade any.
             * 
             * @return
             *     possible object is
             *     {@link Element }
             *     
             */
            public Element getAny() {
                return any;
            }

            /**
             * Define o valor da propriedade any.
             * 
             * @param value
             *     allowed object is
             *     {@link Element }
             *     
             */
            public void setAny(Element value) {
                this.any = value;
            }

            /**
             * Obtém o valor da propriedade schema.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSchema() {
                return schema;
            }

            /**
             * Define o valor da propriedade schema.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSchema(String value) {
                this.schema = value;
            }

        }


        /**
         * <p>Classe Java de anonymous complex type.
         * 
         * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="NFeZip" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *         &lt;element name="protNFeZip" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "nFeZip",
            "protNFeZip"
        })
        public static class ProcNFeGrupoZip {

            @XmlElement(name = "NFeZip", required = true)
            protected byte[] nFeZip;
            @XmlElement(required = true)
            protected byte[] protNFeZip;

            /**
             * Obtém o valor da propriedade nFeZip.
             * 
             * @return
             *     possible object is
             *     byte[]
             */
            public byte[] getNFeZip() {
                return nFeZip;
            }

            /**
             * Define o valor da propriedade nFeZip.
             * 
             * @param value
             *     allowed object is
             *     byte[]
             */
            public void setNFeZip(byte[] value) {
                this.nFeZip = value;
            }

            /**
             * Obtém o valor da propriedade protNFeZip.
             * 
             * @return
             *     possible object is
             *     byte[]
             */
            public byte[] getProtNFeZip() {
                return protNFeZip;
            }

            /**
             * Define o valor da propriedade protNFeZip.
             * 
             * @param value
             *     allowed object is
             *     byte[]
             */
            public void setProtNFeZip(byte[] value) {
                this.protNFeZip = value;
            }

        }

    }

}
