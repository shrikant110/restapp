/**
 * 
 */
package com.social.inbound.stocts.entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author shrikant.kushwaha
 *
 */
@Getter
@Setter
@Entity
@Table(name = "AUR_STOCK_DETAIL")
@Scope("session")
public class AuroraStockDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aur_stock_detail_generator")
	@SequenceGenerator(name = "aur_stock_detail_generator", sequenceName = "aur_stock_detail_id_seq", allocationSize = 1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "ANALYSIS_DATE")
	private Timestamp analysisDate;

	@Column(name = "ISIN")
	private String isin;

	@Column(name = "ccy_code")
	private String currencycode;

	@Getter
	@Setter
	@Column(name = "COUNTRY")
	private String countrycode;
	@Getter
	@Setter
	@Column(name = "GROUPS")
	private String groups;

	@Column(name = "sector")
	private Short sector;
	@Getter
	@Setter
	@Column(name = "company_name")
	private String companyName;
	@Getter
	@Setter
	@Column(name = "year_of_analysis")
	private int yearofanalysis;
	@Getter
	@Setter
	@Column(name = "Tech_hist_Date")
	private Timestamp techHistDate;
	@Getter
	@Setter
	@Column(name = "Ert_Tren_hist_Date")
	private Timestamp ertTrenHistDate;
	@Getter
	@Setter
	@Column(name = "ERT_PRICE_HIST")
	private BigDecimal ertPriceHist;
	@Getter
	@Setter
	@Column(name = "Int_hist_Date")
	private Timestamp intHistDate;
	@Getter
	@Setter
	@Column(name = "glb_hist_Date")
	private Timestamp glbHistDate;
	@Getter
	@Setter
	@Column(name = "risk_hist_Date")
	private Timestamp riskHistDate;
	@Getter
	@Setter
	@Column(name = "Ref_index_Name")
	private String refIndexName;
	@Getter
	@Setter
	@Column(name = "Bear_Mkt_Risk")
	private Byte bearMktRisk;
	@Getter
	@Setter
	@Column(name = "Bad_New_Risk")
	private int badNewRisk;
	@Getter
	@Setter
	@Column(name = "Payout")
	private BigDecimal Payout;
	@Getter
	@Setter
	@Column(name = "Tech_Rev_Min")
	private BigDecimal techRevMin;
	@Getter
	@Setter
	@Column(name = "Tech_Rev_Max")
	private BigDecimal techRevMax;
	@Getter
	@Setter
	@Column(name = "premium")
	private BigDecimal premium;
	@Getter
	@Setter
	@Column(name = "Risk_Percentage")
	private BigDecimal riskPercentage;

	@Column(name = "Is_PDF_Report")
	private int isPDFReport;
	@Getter
	@Setter
	@Column(name = "Stock_Price")
	private BigDecimal stockPrice;
	@Getter
	@Setter
	@Column(name = "Earnings")
	private BigDecimal earnings;
	@Getter
	@Setter
	@Column(name = "Ticker")
	private String ticker;
	@Getter
	@Setter
	@Column(name = "Vol_1month")
	private BigDecimal vol1month;
	@Getter
	@Setter
	@Column(name = "Vol2_1month")
	private BigDecimal vol21month;
	@Getter
	@Setter
	@Column(name = "Divend")
	private BigDecimal divend;
	@Getter
	@Setter
	@Column(name = "Divend_Txtid")
	private int divend_Txtid;
	@Getter
	@Setter
	@Column(name = "PE_Ratio")
	private BigDecimal PERatio;
	@Getter
	@Setter
	@Column(name = "PE_Ratio_Txtid")
	private int PERatioTxtid;
	@Getter
	@Setter
	@Column(name = "LTG")
	private BigDecimal LTG;
	@Getter
	@Setter
	@Column(name = "LTG_Txtid")
	private int LTGTxtid;
	@Getter
	@Setter
	@Column(name = "LTPE")
	private BigDecimal LTPE;
	@Getter
	@Setter
	@Column(name = "LTPE_Txtid")
	private int LTPETxtid;
	@Getter
	@Setter
	@Column(name = "Market_Cap_Bn")
	private BigDecimal marketCapBn;
	@Getter
	@Setter
	@Column(name = "Market_Cap_Bn_Txtid")
	private int marketCapBnTxtid;
	@Getter
	@Setter
	@Column(name = "Number_Of_Analyst")
	private int numberOfAnalyst;
	@Getter
	@Setter
	@Column(name = "Number_Of_Analyst_Txtid")
	private int numberOfAnalystTxtid;
	@Getter
	@Setter
	@Column(name = "Val_Rating")
	private int valRating;
	@Getter
	@Setter
	@Column(name = "Val_Rating_Txtid")
	private int valRatingTxtid;
	@Getter
	@Setter
	@Column(name = "Val_Rating_Star")
	private int valRatingStar;
	@Getter
	@Setter
	@Column(name = "Rel_Per_4_week")
	private BigDecimal relPer4week;
	@Getter
	@Setter
	@Column(name = "Rel_Per_4_week_Txtid")
	private int relPer4weekTxtid;
	@Getter
	@Setter
	@Column(name = "Rel_Per_4_week_Star")
	private int relPer4weekStar;
	@Getter
	@Setter
	@Column(name = "Med_Term_Tech_Trend")
	private BigDecimal medTermTechTrend;
	@Getter
	@Setter
	@Column(name = "Med_Term_Tech_Trend_Txtid")
	private int medTermTechTrendTxtid;
	@Getter
	@Setter
	@Column(name = "Med_Term_Tech_Trend_Star")
	private int MedTermTechTrendStar;
	@Getter
	@Setter
	@Column(name = "Ear_Rev_Trend")
	private BigDecimal earRevTrend;
	@Getter
	@Setter
	@Column(name = "Ear_Rev_Trend_Txtid")
	private int earRevTrendTxtid;
	@Getter
	@Setter
	@Column(name = "Ear_Rev_Trend_Star")
	private int earRevTrendStar;
	@Getter
	@Setter
	@Column(name = "Interest")
	private int interest;
	@Getter
	@Setter
	@Column(name = "Interest_Txtid")
	private int interestTxtid;
	@Getter
	@Setter
	@Column(name = "Bear_Mkt_Factor")
	private BigInteger bearMktFactor;
	@Getter
	@Setter
	@Column(name = "Bear_Mkt_Factor_Txtid")
	private Short bearMktFactorTxtid;
	
	@Getter
	@Setter
	@Column(name = "Bad_New_Factor")
	private int badNewFactor;
	@Getter
	@Setter
	@Column(name = "Bad_New_Factor_Txtid")
	private int badNewFactorTxtid;
	@Getter
	@Setter
	@Column(name = "Beta")
	private int beta;
	@Getter
	@Setter
	@Column(name = "Beta_Txtid")
	private int betaTxtid;
	
	@Getter
	@Setter
	@Column(name = "Correlation")
	private BigDecimal correlation;
	@Getter
	@Setter
	@Column(name = "Correlation_Txtid")
	private int correlationTxtid;
	@Getter
	@Setter
	@Column(name = "Risk_Zone")
	private int riskZone;
	@Getter
	@Setter
	@Column(name = "Risk_Zone_Txtid")
	private int riskZoneTxtid;
	@Getter
	@Setter
	@Column(name = "Value_Risk")
	private BigDecimal valueRisk;
	@Getter
	@Setter
	@Column(name = "Value_Risk_Txtid")
	private int valueRiskTxtid;
	@Getter
	@Setter
	@Column(name = "Glb_Evaluation")
	private int glbEvaluation;
	@Getter
	@Setter
	@Column(name = "Glb_Evaluation_Txtid")
	private int glbEvaluationTxtid;
	@Getter
	@Setter
	@Column(name = "First_Anly_Date")
	private Timestamp firstAnlyDate;
	
	@Getter
	@Setter
	@Column(name = "First_Anly_Date_Txtid")
	private int firstAnlyDateTxtid;

}
