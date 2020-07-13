package com.cardreader.data.parse.funds;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ChecklistTextsTextLongCorr2_QNAME = new QName("", "corr2");
    private final static QName _ChecklistTextsTextLongBadnews_QNAME = new QName("", "badnews");
    private final static QName _ChecklistTextsTextLongAlphadate_QNAME = new QName("", "alphadate");
    private final static QName _ChecklistTextsTextLongIrstdate_QNAME = new QName("", "irstdate");
    private final static QName _ChecklistTextsTextLongSharpedate_QNAME = new QName("", "sharpedate");
    private final static QName _ChecklistTextsTextLongBold_QNAME = new QName("", "bold");
    private final static QName _ChecklistTextsTextLongInforatiodate_QNAME = new QName("", "inforatiodate");
    private final static QName _ChecklistTextsTextLongTtdate_QNAME = new QName("", "ttdate");
    private final static QName _ChecklistTextsTextLongRefProfile_QNAME = new QName("", "ref_profile");
    private final static QName _ChecklistTextsTextLongVarval_QNAME = new QName("", "varval");
    private final static QName _ChecklistTextsTextLongVarpct_QNAME = new QName("", "varpct");
    private final static QName _ChecklistTextsTextLongRefAssetclass_QNAME = new QName("", "ref_assetclass");
    private final static QName _ChecklistTextsTextLongRefCountry_QNAME = new QName("", "ref_country");
    private final static QName _ChecklistTextsTextLongGldate_QNAME = new QName("", "gldate");
    private final static QName _ChecklistTextsTextLongRiskdate_QNAME = new QName("", "riskdate");
    private final static QName _ChecklistTextsTextLongBear_QNAME = new QName("", "bear");
    private final static QName _ChecklistTextsTextLongBeta_QNAME = new QName("", "beta");
    private final static QName _ChecklistTextsTextShortFirstdate_QNAME = new QName("", "firstdate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Checklist }
     * 
     */
    public Checklist createChecklist() {
        return new Checklist();
    }

    /**
     * Create an instance of {@link Checklist.Funds }
     * 
     */
    public Checklist.Funds createChecklistFunds() {
        return new Checklist.Funds();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund }
     * 
     */
    public Checklist.Funds.Fund createChecklistFundsFund() {
        return new Checklist.Funds.Fund();
    }

    /**
     * Create an instance of {@link Checklist.Promoters }
     * 
     */
    public Checklist.Promoters createChecklistPromoters() {
        return new Checklist.Promoters();
    }

    /**
     * Create an instance of {@link Checklist.Areas }
     * 
     */
    public Checklist.Areas createChecklistAreas() {
        return new Checklist.Areas();
    }

    /**
     * Create an instance of {@link Checklist.Profiles }
     * 
     */
    public Checklist.Profiles createChecklistProfiles() {
        return new Checklist.Profiles();
    }

    /**
     * Create an instance of {@link Checklist.Assetclasses }
     * 
     */
    public Checklist.Assetclasses createChecklistAssetclasses() {
        return new Checklist.Assetclasses();
    }

    /**
     * Create an instance of {@link Checklist.Items }
     * 
     */
    public Checklist.Items createChecklistItems() {
        return new Checklist.Items();
    }

    /**
     * Create an instance of {@link Checklist.Texts }
     * 
     */
    public Checklist.Texts createChecklistTexts() {
        return new Checklist.Texts();
    }

    /**
     * Create an instance of {@link Checklist.Texts.Text }
     * 
     */
    public Checklist.Texts.Text createChecklistTextsText() {
        return new Checklist.Texts.Text();
    }

    /**
     * Create an instance of {@link Checklist.Texts.Text.Long }
     * 
     */
    public Checklist.Texts.Text.Long createChecklistTextsTextLong() {
        return new Checklist.Texts.Text.Long();
    }

    /**
     * Create an instance of {@link Checklist.Texts.Text.Long.Bold }
     * 
     */
    public Checklist.Texts.Text.Long.Bold createChecklistTextsTextLongBold() {
        return new Checklist.Texts.Text.Long.Bold();
    }

    /**
     * Create an instance of {@link Checklist.Texts.Text.Short }
     * 
     */
    public Checklist.Texts.Text.Short createChecklistTextsTextShort() {
        return new Checklist.Texts.Text.Short();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Alpha }
     * 
     */
    public Checklist.Funds.Fund.Alpha createChecklistFundsFundAlpha() {
        return new Checklist.Funds.Fund.Alpha();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Asset }
     * 
     */
    public Checklist.Funds.Fund.Asset createChecklistFundsFundAsset() {
        return new Checklist.Funds.Fund.Asset();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Tracking }
     * 
     */
    public Checklist.Funds.Fund.Tracking createChecklistFundsFundTracking() {
        return new Checklist.Funds.Fund.Tracking();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Sharpe }
     * 
     */
    public Checklist.Funds.Fund.Sharpe createChecklistFundsFundSharpe() {
        return new Checklist.Funds.Fund.Sharpe();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Rp6M }
     * 
     */
    public Checklist.Funds.Fund.Rp6M createChecklistFundsFundRp6M() {
        return new Checklist.Funds.Fund.Rp6M();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Tt }
     * 
     */
    public Checklist.Funds.Fund.Tt createChecklistFundsFundTt() {
        return new Checklist.Funds.Fund.Tt();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Inforatio }
     * 
     */
    public Checklist.Funds.Fund.Inforatio createChecklistFundsFundInforatio() {
        return new Checklist.Funds.Fund.Inforatio();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Irst }
     * 
     */
    public Checklist.Funds.Fund.Irst createChecklistFundsFundIrst() {
        return new Checklist.Funds.Fund.Irst();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Bear }
     * 
     */
    public Checklist.Funds.Fund.Bear createChecklistFundsFundBear() {
        return new Checklist.Funds.Fund.Bear();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Badnews }
     * 
     */
    public Checklist.Funds.Fund.Badnews createChecklistFundsFundBadnews() {
        return new Checklist.Funds.Fund.Badnews();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Beta }
     * 
     */
    public Checklist.Funds.Fund.Beta createChecklistFundsFundBeta() {
        return new Checklist.Funds.Fund.Beta();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Corr2 }
     * 
     */
    public Checklist.Funds.Fund.Corr2 createChecklistFundsFundCorr2() {
        return new Checklist.Funds.Fund.Corr2();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Riskzone }
     * 
     */
    public Checklist.Funds.Fund.Riskzone createChecklistFundsFundRiskzone() {
        return new Checklist.Funds.Fund.Riskzone();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Varval }
     * 
     */
    public Checklist.Funds.Fund.Varval createChecklistFundsFundVarval() {
        return new Checklist.Funds.Fund.Varval();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Gleval }
     * 
     */
    public Checklist.Funds.Fund.Gleval createChecklistFundsFundGleval() {
        return new Checklist.Funds.Fund.Gleval();
    }

    /**
     * Create an instance of {@link Checklist.Funds.Fund.Firstdate }
     * 
     */
    public Checklist.Funds.Fund.Firstdate createChecklistFundsFundFirstdate() {
        return new Checklist.Funds.Fund.Firstdate();
    }

    /**
     * Create an instance of {@link Checklist.Promoters.Promoter }
     * 
     */
    public Checklist.Promoters.Promoter createChecklistPromotersPromoter() {
        return new Checklist.Promoters.Promoter();
    }

    /**
     * Create an instance of {@link Checklist.Areas.Area }
     * 
     */
    public Checklist.Areas.Area createChecklistAreasArea() {
        return new Checklist.Areas.Area();
    }

    /**
     * Create an instance of {@link Checklist.Profiles.Profile }
     * 
     */
    public Checklist.Profiles.Profile createChecklistProfilesProfile() {
        return new Checklist.Profiles.Profile();
    }

    /**
     * Create an instance of {@link Checklist.Assetclasses.Assetclass }
     * 
     */
    public Checklist.Assetclasses.Assetclass createChecklistAssetclassesAssetclass() {
        return new Checklist.Assetclasses.Assetclass();
    }

    /**
     * Create an instance of {@link Checklist.Items.Item }
     * 
     */
    public Checklist.Items.Item createChecklistItemsItem() {
        return new Checklist.Items.Item();
    }

    /**
     * Create an instance of {@link Checklist.Texts.Text.Long.Bear }
     * 
     */
    public Checklist.Texts.Text.Long.Bear createChecklistTextsTextLongBear() {
        return new Checklist.Texts.Text.Long.Bear();
    }

    /**
     * Create an instance of {@link Checklist.Texts.Text.Long.Badnews }
     * 
     */
    public Checklist.Texts.Text.Long.Badnews createChecklistTextsTextLongBadnews() {
        return new Checklist.Texts.Text.Long.Badnews();
    }

    /**
     * Create an instance of {@link Checklist.Texts.Text.Long.Beta }
     * 
     */
    public Checklist.Texts.Text.Long.Beta createChecklistTextsTextLongBeta() {
        return new Checklist.Texts.Text.Long.Beta();
    }

    /**
     * Create an instance of {@link Checklist.Texts.Text.Long.Corr2 }
     * 
     */
    public Checklist.Texts.Text.Long.Corr2 createChecklistTextsTextLongCorr2() {
        return new Checklist.Texts.Text.Long.Corr2();
    }

    /**
     * Create an instance of {@link Checklist.Texts.Text.Long.Varpct }
     * 
     */
    public Checklist.Texts.Text.Long.Varpct createChecklistTextsTextLongVarpct() {
        return new Checklist.Texts.Text.Long.Varpct();
    }

    /**
     * Create an instance of {@link Checklist.Texts.Text.Long.Bold.Tracking }
     * 
     */
    public Checklist.Texts.Text.Long.Bold.Tracking createChecklistTextsTextLongBoldTracking() {
        return new Checklist.Texts.Text.Long.Bold.Tracking();
    }

    /**
     * Create an instance of {@link Checklist.Texts.Text.Long.Bold.Sharpe }
     * 
     */
    public Checklist.Texts.Text.Long.Bold.Sharpe createChecklistTextsTextLongBoldSharpe() {
        return new Checklist.Texts.Text.Long.Bold.Sharpe();
    }

    /**
     * Create an instance of {@link Checklist.Texts.Text.Short.Varpct }
     * 
     */
    public Checklist.Texts.Text.Short.Varpct createChecklistTextsTextShortVarpct() {
        return new Checklist.Texts.Text.Short.Varpct();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Checklist.Texts.Text.Long.Corr2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "corr2", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Checklist.Texts.Text.Long.Corr2> createChecklistTextsTextLongCorr2(Checklist.Texts.Text.Long.Corr2 value) {
        return new JAXBElement<Checklist.Texts.Text.Long.Corr2>(_ChecklistTextsTextLongCorr2_QNAME, Checklist.Texts.Text.Long.Corr2 .class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Checklist.Texts.Text.Long.Badnews }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "badnews", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Checklist.Texts.Text.Long.Badnews> createChecklistTextsTextLongBadnews(Checklist.Texts.Text.Long.Badnews value) {
        return new JAXBElement<Checklist.Texts.Text.Long.Badnews>(_ChecklistTextsTextLongBadnews_QNAME, Checklist.Texts.Text.Long.Badnews.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "alphadate", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Object> createChecklistTextsTextLongAlphadate(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongAlphadate_QNAME, Object.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "irstdate", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Object> createChecklistTextsTextLongIrstdate(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongIrstdate_QNAME, Object.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sharpedate", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Object> createChecklistTextsTextLongSharpedate(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongSharpedate_QNAME, Object.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Checklist.Texts.Text.Long.Bold }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "bold", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Checklist.Texts.Text.Long.Bold> createChecklistTextsTextLongBold(Checklist.Texts.Text.Long.Bold value) {
        return new JAXBElement<Checklist.Texts.Text.Long.Bold>(_ChecklistTextsTextLongBold_QNAME, Checklist.Texts.Text.Long.Bold.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "inforatiodate", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Object> createChecklistTextsTextLongInforatiodate(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongInforatiodate_QNAME, Object.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ttdate", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Object> createChecklistTextsTextLongTtdate(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongTtdate_QNAME, Object.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ref_profile", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Object> createChecklistTextsTextLongRefProfile(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongRefProfile_QNAME, Object.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "varval", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Object> createChecklistTextsTextLongVarval(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongVarval_QNAME, Object.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Checklist.Texts.Text.Long.Varpct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "varpct", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Checklist.Texts.Text.Long.Varpct> createChecklistTextsTextLongVarpct(Checklist.Texts.Text.Long.Varpct value) {
        return new JAXBElement<Checklist.Texts.Text.Long.Varpct>(_ChecklistTextsTextLongVarpct_QNAME, Checklist.Texts.Text.Long.Varpct.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ref_assetclass", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Object> createChecklistTextsTextLongRefAssetclass(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongRefAssetclass_QNAME, Object.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ref_country", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Object> createChecklistTextsTextLongRefCountry(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongRefCountry_QNAME, Object.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "gldate", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Object> createChecklistTextsTextLongGldate(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongGldate_QNAME, Object.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "riskdate", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Object> createChecklistTextsTextLongRiskdate(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongRiskdate_QNAME, Object.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Checklist.Texts.Text.Long.Bear }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "bear", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Checklist.Texts.Text.Long.Bear> createChecklistTextsTextLongBear(Checklist.Texts.Text.Long.Bear value) {
        return new JAXBElement<Checklist.Texts.Text.Long.Bear>(_ChecklistTextsTextLongBear_QNAME, Checklist.Texts.Text.Long.Bear.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Checklist.Texts.Text.Long.Beta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "beta", scope = Checklist.Texts.Text.Long.class)
    public JAXBElement<Checklist.Texts.Text.Long.Beta> createChecklistTextsTextLongBeta(Checklist.Texts.Text.Long.Beta value) {
        return new JAXBElement<Checklist.Texts.Text.Long.Beta>(_ChecklistTextsTextLongBeta_QNAME, Checklist.Texts.Text.Long.Beta.class, Checklist.Texts.Text.Long.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "varval", scope = Checklist.Texts.Text.Short.class)
    public JAXBElement<Object> createChecklistTextsTextShortVarval(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongVarval_QNAME, Object.class, Checklist.Texts.Text.Short.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Checklist.Texts.Text.Short.Varpct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "varpct", scope = Checklist.Texts.Text.Short.class)
    public JAXBElement<Checklist.Texts.Text.Short.Varpct> createChecklistTextsTextShortVarpct(Checklist.Texts.Text.Short.Varpct value) {
        return new JAXBElement<Checklist.Texts.Text.Short.Varpct>(_ChecklistTextsTextLongVarpct_QNAME, Checklist.Texts.Text.Short.Varpct.class, Checklist.Texts.Text.Short.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "alphadate", scope = Checklist.Texts.Text.Short.class)
    public JAXBElement<Object> createChecklistTextsTextShortAlphadate(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongAlphadate_QNAME, Object.class, Checklist.Texts.Text.Short.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sharpedate", scope = Checklist.Texts.Text.Short.class)
    public JAXBElement<Object> createChecklistTextsTextShortSharpedate(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongSharpedate_QNAME, Object.class, Checklist.Texts.Text.Short.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "firstdate", scope = Checklist.Texts.Text.Short.class)
    public JAXBElement<Object> createChecklistTextsTextShortFirstdate(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextShortFirstdate_QNAME, Object.class, Checklist.Texts.Text.Short.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "inforatiodate", scope = Checklist.Texts.Text.Short.class)
    public JAXBElement<Object> createChecklistTextsTextShortInforatiodate(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongInforatiodate_QNAME, Object.class, Checklist.Texts.Text.Short.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ttdate", scope = Checklist.Texts.Text.Short.class)
    public JAXBElement<Object> createChecklistTextsTextShortTtdate(Object value) {
        return new JAXBElement<Object>(_ChecklistTextsTextLongTtdate_QNAME, Object.class, Checklist.Texts.Text.Short.class, value);
    }

}
