package com.fintech.server.api.day.service;

import com.fintech.server.market.day.model.MarketDay;
import com.fintech.server.market.day.model.MarketDayRepository;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class ApiDayServiceImpl implements ApiDayService {

  private final MarketDayRepository marketDayRepository;

  final String HOST = "https://openapi.koreainvestment.com:9443";
  final String URL = "/uapi/domestic-stock/v1/quotations/inquire-daily-itemchartprice";

  public Mono<Map> batch(){
    HttpClient httpClient = HttpClient.create()
      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
      .responseTimeout(Duration.ofMillis(5000))
      .doOnConnected(conn ->
        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
          .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS))
      );

    var webClient = WebClient.builder()
      .baseUrl(HOST + URL + "?fid_cond_mrkt_div_code=UN&fid_input_iscd=000660&fid_input_date_1=250601&fid_input_date_2=20250626&fid_period_div_code=D&fid_org_adj_prc=1")
      .defaultHeaders(i ->{
        i.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        i.add("appkey", "PS2Kor7J9Y3EOnZw04mfFn9PByRKcxlPGRZv");
        i.add("appsecret", "o1mJ0tZfSW4Jsu0FQ1HvSBvrYVZnidecYJcJ6n1tItwxTGk0sDSHLudxmFMibRgolVLMvPbG6tZVZZxOrhTjefQSS0sJkB4KCgmVIDdbXiX2PqkpsRLkp3FS0wCKrJrJ3mgj6YelLoTZlJ+lN2eBKkRtVPZUS0CsmI73qVDM76O6UPhiKpQ=");
        i.add("tr_id", "FHKST03010100");
        i.add("custtype", "P");
        i.add(HttpHeaders.AUTHORIZATION, "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b2tlbiIsImF1ZCI6ImI4MjAyMzNiLTIxNzQtNDc3Zi1hNWM5LTkzYWFkZDNiYWUyZCIsInByZHRfY2QiOiIiLCJpc3MiOiJ1bm9ndyIsImV4cCI6MTc1MTAyNzI3MSwiaWF0IjoxNzUwOTQwODcxLCJqdGkiOiJQUzJLb3I3SjlZM0VPblp3MDRtZkZuOVBCeVJLY3hsUEdSWnYifQ.TqcBH8Y6G6h0FEgOdScBrxgaDd_5kmOVnntIdIrVbBWhS_b4HMfbqBjBAwHxzhZ4823xLeSKxwp-HyyJ2Zs6xA");
      })
      .clientConnector(new ReactorClientHttpConnector(httpClient))
      .build();

    Mono<Map> result = webClient.get().retrieve().bodyToMono(Map.class);

    result.subscribe(
      map -> {
        Map<String, String> output1 = (Map<String, String>) map.get("output1");
        List<Map<String, String>> outputList = (List<Map<String, String>>) map.get("output2");
        outputList.forEach(i ->{
          MarketDay marketDay = new MarketDay(
            output1.get("stck_shrn_iscd"),
            i.get("stck_bsop_date"),
            i.get("stck_clpr"),
            i.get("stck_oprc"),
            i.get("stck_hgpr"),
            i.get("stck_lwpr"),
            i.get("acml_vol"),
            i.get("acml_tr_pbmn"),
            i.get("flng_cls_code"),
            i.get("prtt_rate"),
            i.get("mod_yn"),
            i.get("prdy_vrss_sign"),
            i.get("prdy_vrss"),
            i.get("revl_issu_reas")
          );

          marketDayRepository.save(marketDay);
        });
      }
    );


    return result;

  }
}
