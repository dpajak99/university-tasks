package pl.pwsztar.kolokwium.service.impl;

import org.springframework.stereotype.Service;
import pl.pwsztar.kolokwium.service.MathService;

@Service
public class MathServiceImpl implements MathService {
    @Override
    public boolean isPrime(int pathNumber ) {
        if( pathNumber == 1) {
            return false;
        }
        for( int i = 2; i < pathNumber; i++ ) {
            if( pathNumber%i == 0 ) {
                return false;
            }
        }
        return true;
    }
}
