package com.fintech.server.api.day.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public interface ApiDayService {
  Mono<Map> batch();
}
