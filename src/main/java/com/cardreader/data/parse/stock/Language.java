package com.cardreader.data.parse.stock;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Language.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Language">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="en"/>
 *     &lt;enumeration value="de"/>
 *     &lt;enumeration value="fr"/>
 *     &lt;enumeration value="es"/>
 *     &lt;enumeration value="it"/>
 *     &lt;enumeration value="se"/>
 *     &lt;enumeration value="nl"/>
 *     &lt;enumeration value="zt"/>
 *     &lt;enumeration value="zs"/>
 *     &lt;enumeration value="jp"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Language")
@XmlEnum
public enum Language {

    @XmlEnumValue("en")
    EN("en"),
    @XmlEnumValue("de")
    DE("de"),
    @XmlEnumValue("fr")
    FR("fr"),
    @XmlEnumValue("es")
    ES("es"),
    @XmlEnumValue("it")
    IT("it"),
    @XmlEnumValue("se")
    SE("se"),
    @XmlEnumValue("nl")
    NL("nl"),
    @XmlEnumValue("zt")
    ZT("zt"),
    @XmlEnumValue("zs")
    ZS("zs"),
    @XmlEnumValue("jp")
    JP("jp");
    private final String value;

    Language(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Language fromValue(String v) {
        for (Language c: Language.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
