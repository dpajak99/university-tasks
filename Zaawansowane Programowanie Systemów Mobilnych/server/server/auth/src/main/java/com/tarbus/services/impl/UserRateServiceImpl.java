package com.tarbus.services.impl;

import com.tarbus.payload.entity.UserRate;
import com.tarbus.repositories.jpa.UserRateRepository;
import com.tarbus.services.UserRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRateServiceImpl implements UserRateService {

  private final UserRateRepository userRateRepository;

  @Autowired
  public UserRateServiceImpl(UserRateRepository userRateRepository) {
    this.userRateRepository = userRateRepository;
  }

  @Override
  public void createRate(UserRate userRate) {
    userRateRepository.save(userRate);
  }

  @Override
  public double getUserRatesAvg(String userId) {
    List<UserRate> userRates = userRateRepository.findByGivenToId(userId);
    double sum = 0;
    for (UserRate userRate : userRates) {
      sum += userRate.getRate();
    }
    if( userRates.size() == 0 ) {
      return 5;
    }
    return sum / userRates.size();
  }
}
