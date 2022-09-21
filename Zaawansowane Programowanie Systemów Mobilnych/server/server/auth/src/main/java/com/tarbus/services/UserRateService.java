package com.tarbus.services;

import com.tarbus.payload.entity.UserRate;

public interface UserRateService {
  void createRate(UserRate userRate);
  double getUserRatesAvg(String userId);
}
