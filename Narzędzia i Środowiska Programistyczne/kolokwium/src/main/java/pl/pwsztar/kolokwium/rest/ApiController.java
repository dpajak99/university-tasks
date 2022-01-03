package pl.pwsztar.kolokwium.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.pwsztar.kolokwium.model.AppNumber;
import pl.pwsztar.kolokwium.service.MathService;

@Controller
@RequestMapping(value = "/api")
public class ApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private MathService mathService;

    @RequestMapping(value = "/math/is-prime/{number}", method = RequestMethod.GET)
    public ResponseEntity<AppNumber> isPrime(@PathVariable("number") int pathNumber) {
        LOGGER.info("### Otrzymuję zmienną " + pathNumber);
        LOGGER.info("### Sprawdzam czy zmienna "+pathNumber+" jest liczbą pierwszą");
        final boolean isNumberPrime = mathService.isPrime(pathNumber);
        final AppNumber appNumber = new AppNumber(isNumberPrime);
        LOGGER.info("### Liczba "+pathNumber+ " - jest liczbą pierwszą. Prawda: " + isNumberPrime);
        if( isNumberPrime ) {
            return new ResponseEntity<>(appNumber, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(appNumber, HttpStatus.NOT_FOUND);
        }

    }
}




