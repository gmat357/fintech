package com.fintech.server.market.day.model;

import com.fintech.server.infomation.comp.model.CompanyInfo;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Data
@Entity
public class MarketDay {

  public MarketDay(){

  }

  public MarketDay(String companyId, String stckBsopDate, String stckClpr, String stckOprc, String stckHgpr, String stckLwpr, String acmlVol, String acmlTrPbmn, String flngClsCode, String prttRate, String modYn, String prdyVrssSign, String prdyVrss, String revlIssuReas) {
    this.companyInfo = new CompanyInfo();
    this.companyInfo.setId(companyId);
    this.stckBsopDate = stckBsopDate;
    this.stckClpr = stckClpr;
    this.stckOprc = stckOprc;
    this.stckHgpr = stckHgpr;
    this.stckLwpr = stckLwpr;
    this.acmlVol = acmlVol;
    this.acmlTrPbmn = acmlTrPbmn;
    this.flngClsCode = flngClsCode;
    this.prttRate = prttRate;
    this.modYn = modYn;
    this.prdyVrssSign = prdyVrssSign;
    this.prdyVrss = prdyVrss;
    this.revlIssuReas = revlIssuReas;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name="company_id", referencedColumnName = "id")
  CompanyInfo companyInfo;

  @Column(length = 8, nullable = false)
  @Comment("주식 영업 일자")
  public String stckBsopDate;

  @Column(length = 10, nullable = false)
  @Comment("주식 종가")
  public String stckClpr;

  @Column(length = 10, nullable = false)
  @Comment("주식 시가2")
  public String stckOprc;

  @Column(length = 10, nullable = false)
  @Comment("주식 최고가")
  public String stckHgpr;

  @Column(length = 10, nullable = false)
  @Comment("주식 최저가")
  public String stckLwpr;

  @Column(length = 18, nullable = false)
  @Comment("누적 거래량")
  public String acmlVol;

  @Column(length = 18, nullable = false)
  @Comment("누적 거래 대금")
  public String acmlTrPbmn;

  @Column(length = 2, nullable = false)
  @Comment("락 구분 코드")
  public String flngClsCode;

  @Column(length = 11, nullable = false)
  @Comment("분할 비율")
  public String prttRate;

  @Column(length = 1, nullable = false)
  @Comment("변경 여부")
  public String modYn;

  @Column(length = 1, nullable = false)
  @Comment("전일 대비 부호")
  public String prdyVrssSign;

  @Column(length = 10, nullable = false)
  @Comment("전일 대비")
  public String prdyVrss;

  @Column(length = 2, nullable = false)
  @Comment("재평가사유코드")
  public String revlIssuReas;

}
