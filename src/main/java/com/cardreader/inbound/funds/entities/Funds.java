package com.cardreader.inbound.funds.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author shrikant.kushwaha CREATE Table FUNDS ( ANALYSISDATE TIMESTAMPTZ,
 *         PUBLISHDATE TIMESTAMPTZ, AUTH_COUNTRIES VARCHAR(50), PEA VARCHAR
 *         (10), UCITS VARCHAR (10), ETF VARCHAR (10), PDF VARCHAR (10),
 *         PRICING_FREQUENCY VARCHAR(10), CCY VARCHAR (10), ISIN VARCHAR (12),
 *         ASSETCLASS BIGINT, PROFILE BIGINT, PROMOTER BIGINT, AREA BIGINT,
 *         REF_ASSETCLASS BIGINT, REF_PROFILE BIGINT, REF_COUNTRY VARCHAR(2),
 *         NAME VARCHAR(100), TTDATE TIMESTAMPTZ, SHARPEDATE TIMESTAMPTZ,
 *         ALPHADATE TIMESTAMPTZ, INFORATIODATE TIMESTAMPTZ, GLDATE TIMESTAMPTZ,
 *         RISKDATE TIMESTAMPTZ, IRSTDATE TIMESTAMPTZ, BMFRISK INT, BNFRISK INT,
 *         TR_MIN REAL, TR_MAX REAL, VARPCT REAL, NAV REAL, NAVDATE TIMESTAMPTZ,
 *         ASSETDATE TIMESTAMPTZ, MGTFEES REAL, REDEMPTIONFEES REAL,
 *         INCEPTIONDATE TIMESTAMPTZ, VOL1M REAL, VOL12M REAL, PERFYTD REAL,
 *         PERF1Y REAL, PERF3Y REAL, PERF5Y REAL, ALPHA REAL, ASSET REAL,
 *         TRACKING REAL, SHARPE REAL, RP6M REAL, TT REAL, INFORATIO INT, IRST
 *         INT, BEAR INT, BADNEW INT, BETA INT, CORR2 REAL, RISKZONE INT, VARVAL
 *         REAL, GLEVAL INT, FIRSTDATE TIMESTAMPTZ)
 */

@Entity
@Table(name = "FUNDS")
@Scope("session")
public class Funds {
	
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fundid_generator")
	@SequenceGenerator(name = "fundid_generator", sequenceName = "funds_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "ANALYSISDATE")
	@Getter
	@Setter
	private Timestamp analysisDate;

	@Column(name = "PUBLISHDATE")
	@Getter
	@Setter
	private Timestamp publishDate;

	@Column(name = "AUTH_COUNTRIES")
	@JsonProperty
	@Getter
	@Setter
	private String authCountries;

	@Getter
	@Setter
	@Column(name = "PEA")
	private String pea;

	@Getter
	@Setter
	@Column(name = "UCITS")
	private String ucits;

	@Getter
	@Setter
	@Column(name = "ETF")
	private String etf;

	@Getter
	@Setter
	@Column(name = "PDF")
	private String pdf;

	@Getter
	@Setter
	@Column(name = "PRICING_FREQUENCY")
	private String pricingFrequency;

	@Getter
	@Setter
	@Column(name = "CCY")
	private String ccy;

	@Getter
	@Setter
	@Column(name = "ISIN")
	private String isin;

	@Getter
	@Setter
	@Column(name = "ASSETCLASS")
	private Short assetClass;

	@Getter
	@Setter
	@Column(name = "PROFILE")
	private Short profile;
	@Getter
	@Setter
	@Column(name = "PROMOTER")
	private Integer promotor;
	@Getter
	@Setter
	@Column(name = "AREA")
	private Long area;

	@Getter
	@Setter
	@Column(name = "REF_ASSETCLASS")
	private Long refAssetClass;
	@Getter
	@Setter
	@Column(name = "REF_PROFILE")
	private Short refProfile;

	@Getter
	@Setter
	@Column(name = "REF_COUNTRY")
	private String refCountry;

	@Getter
	@Setter
	@Column(name = "NAME")
	private String name;

	@Getter
	@Setter
	@Column(name = "TTDATE")
	private Timestamp ttDate;
	@Getter
	@Setter
	@Column(name = "SHARPEDATE")
	private Timestamp sharpDate;

	@Getter
	@Setter
	@Column(name = "ALPHADATE")
	private Timestamp alphaDate;
	@Getter
	@Setter
	@Column(name = "INFORMATIODATE")
	private Timestamp informationDate;
	@Getter
	@Setter
	@Column(name = "GLDATE")
	private Timestamp glDate;
	@Getter
	@Setter
	@Column(name = "RISKDATE")
	private Timestamp riskDate;
	@Getter
	@Setter
	@Column(name = "IRSTDATE")
	private Timestamp irstDate;
	@Getter
	@Setter
	@Column(name = "BMFRISK")
	private int bmfRisk;
	@Getter
	@Setter
	@Column(name = "BNFRISK")
	private int bnfRisk;

	@Getter
	@Setter
	@Column(name = "TR_MIN")
	private BigDecimal trMin;
	@Getter
	@Setter
	@Column(name = "TR_MAX")
	private BigDecimal trMax;
	@Getter
	@Setter
	@Column(name = "VARPCT")
	private BigDecimal varpct;
	@Getter
	@Setter
	@Column(name = "NAV")
	private BigDecimal nav;

	@Getter
	@Setter
	@Column(name = "NAVDATE")
	private Timestamp navDate;
	@Getter
	@Setter
	@Column(name = "ASSETDATE")
	private Timestamp assetDate;
	@Getter
	@Setter
	@Column(name = "MGTFEES")
	private BigDecimal mgtfees;
	@Getter
	@Setter
	@Column(name = "REDEMPTIONFEES")
	private BigDecimal redemptionFees;
	@Getter
	@Setter
	@Column(name = "INCEPTIONDATE")
	private Timestamp inceptionDate;

	@Getter
	@Setter
	@Column(name = "VOL1M")
	private BigDecimal vol1m;
	@Getter
	@Setter
	@Column(name = "VOL12M")
	private BigDecimal vol12M;
	@Getter
	@Setter
	@Column(name = "PERFYTD")
	private BigDecimal perftd;
	@Getter
	@Setter
	@Column(name = "PERF1Y")
	private BigDecimal perf1y;
	@Getter
	@Setter
	@Column(name = "PERF3Y")
	private BigDecimal perf3y;
	@Getter
	@Setter
	@Column(name = "PERF5Y")
	private BigDecimal perf5y;
	@Getter
	@Setter
	@Column(name = "ALPHA")
	private BigDecimal alpha;
	
	@Getter
	@Setter
	@Column(name = "ALPHA_TEXT_ID")
	private Short alphaTextId;
	
	
	
	@Getter
	@Setter
	@Column(name = "ASSET")
	private BigDecimal asset;
	
	@Getter
	@Setter
	@Column(name = "ASSET_TEXT_ID")
	private Short assetTextId;
	
	@Getter
	@Setter
	@Column(name = "TRACKING")
	private BigDecimal tracking;
	
	@Getter
	@Setter
	@Column(name = "TRACKING_TEXT_ID")
	private Short trackingTextId;
	
	@Getter
	@Setter
	@Column(name = "SHARPE")
	private BigDecimal sharpe;
	
	@Getter
	@Setter
	@Column(name = "SHARPE_STAR")
	private Short sharpeStar;
	
	
	@Getter
	@Setter
	@Column(name = "SHARPE_TEXT_ID")
	private Short sharpeTextId;
	
	
	
	@Getter
	@Setter
	@Column(name = "RP6M")
	private BigDecimal rp6m;
	
	
	@Getter
	@Setter
	@Column(name = "RP6M_STAR")
	private Short rp6mStar;
	
	
	@Getter
	@Setter
	@Column(name = "RP6M_TEXT_ID")
	private Short rp6mTextId;
	
	@Getter
	@Setter
	@Column(name = "TT")
	private BigDecimal tt;
	
	@Getter
	@Setter
	@Column(name = "TT_STAR")
	private Short ttStar;
	
	
	@Getter
	@Setter
	@Column(name = "TT_TEXT_ID")
	private Short ttTextId;

	@Getter
	@Setter
	@Column(name = "INFORATIO")
	private BigDecimal inforatio;
	
	@Getter
	@Setter
	@Column(name = "INFORATIO_STAR")
	private Short inforatioStar;
	
	
	@Getter
	@Setter
	@Column(name = "INFORATIO_TEXT_ID")
	private Short inforatioTextId;
	
	
	@Getter
	@Setter
	@Column(name = "IRST")
	private int irst;
	
	@Getter
	@Setter
	@Column(name = "IRST_TEXT_ID")
	private Short irstTextId;
	
	@Getter
	@Setter
	@Column(name = "BEAR")
	private int bear;
	
	@Getter
	@Setter
	@Column(name = "BEAR_TEXT_ID")
	private Short bearTextId;
	
	@Getter
	@Setter
	@Column(name = "BADNEW")
	private short bednew;
	
	@Getter
	@Setter
	@Column(name = "BADNEW_TEXT_ID")
	private Short bednewTextId;
	
	@Getter
	@Setter
	@Column(name = "BETA")
	private int beta;
	@Getter
	@Setter
	@Column(name = "BETA_TEXT_ID")
	private Short betaTextId;
	
	@Getter
	@Setter
	@Column(name = "CORR2")
	private BigDecimal corr2;
	
	@Getter
	@Setter
	@Column(name = "CORR2_TEXT_ID")
	private Short corr2TextId;
	
	@Getter
	@Setter
	@Column(name = "RISKZONE")
	private int riskZone;
	@Getter
	@Setter
	@Column(name = "RISKZONE_TEXT_ID")
	private Short riskzoneTextId;
	
	@Getter
	@Setter
	@Column(name = "VARVAL")
	private BigDecimal varval;
	
	@Getter
	@Setter
	@Column(name = "VARVAL_TEXT_ID")
	private Short varvalTextId;
	
	
	@Getter
	@Setter
	@Column(name = "GLEVAL")
	private int gleval;
	
	@Getter
	@Setter
	@Column(name = "GLEVAL_TEXT_ID")
	private Short glevalTextId;
	
	@Getter
	@Setter
	@Column(name = "FIRSTDATE")
	private Timestamp firstDate;
	
	@Getter
	@Setter
	@Column(name = "FIRSTDATE_TEXT_ID")
	private Short firstDateTextId;
	
	
	
	@Getter
	@Setter
	@Column(name = "USER_CREATED")
	private String userCreated;
	@Getter
	@Setter
	@CreationTimestamp
	@Column(name = "DATE_CREATED")
	private Timestamp dateCreated;
	@Getter
	@Setter
	@Column(name = "USER_MODIFIED")
	private String userModified;
	@Getter
	@Setter
	@Column(name = "DATE_MODIFIED")
	@UpdateTimestamp
	private Timestamp dateModifiled;

}
