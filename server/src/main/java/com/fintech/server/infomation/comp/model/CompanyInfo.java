package com.fintech.server.infomation.comp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Data
@Entity
@Comment("상장 기업 정보")
public class CompanyInfo {
  @Id
  private String id;

  @Column(length = 200, nullable = false)
  @Comment("회사명")
  private String name;

  @Column(length = 200, nullable = false)
  @Comment("업종")
  private String industry;

  @Column(length = 200)
  @Comment("주요 제품")
  private String product;

  @Comment("상장일")
  private LocalDate publicDt;

  @Column(length = 200, nullable = false)
  @Comment("대표자명")
  private String adminNm;

  @Column(length = 200)
  @Comment("홈페이지")
  private String homepage;

  @Column(length = 20)
  @Comment("지역")
  private String area;
}
