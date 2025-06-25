package com.fintech.server.market.day.model;

import com.fintech.server.infomation.comp.model.CompanyInfo;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Data
@Entity
public class MarketDay {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 10, nullable = false)
  @Comment("전일 대비")
  public String prdyVrss;

  @Column(length = 1, nullable = false)
  @Comment("전일 대비 부호")
  public String prdyVrssSign;

  @Column(length = 11, nullable = false)
  @Comment("전일 대비율")
  public String prdyCtrt;

  @Column(length = 10, nullable = false)
  @Comment("주식 전일 종가")
  public String stckPrdyClpr;

  @Column(length = 18, nullable = false)
  @Comment("누적 거래량")
  public String acmlVol;

  @Column(length = 18, nullable = false)
  @Comment("누적 거래 대금")
  public String acmlTrPbmn;

  @Column(length = 10, nullable = false)
  @Comment("주식 현재가")
  public String stckPrpr;

  @Column(length = 8, nullable = false)
  @Comment("주식 영업 일자")
  public String stckBsopDate;

  @Column(length = 6, nullable = false)
  @Comment("주식 체결 시간")
  public String stckCntgHour;

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
  @Comment("체결 거래량")
  public String cntgVol;

  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name="company_id", referencedColumnName = "id")
  CompanyInfo companyInfo;



















}
