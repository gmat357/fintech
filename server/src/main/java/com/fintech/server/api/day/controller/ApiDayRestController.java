package com.fintech.server.api.day.controller;

import com.fintech.server.api.day.service.ApiDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/day")
@RequiredArgsConstructor
public class ApiDayRestController {

  private final ApiDayService apiDayService;

  @PostMapping("/batch")
  public Mono<Map> batch(){
    return apiDayService.batch();
  }
}
