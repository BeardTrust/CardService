package com.beardtrust.webapp.cardservice.controllers;

import com.beardtrust.webapp.cardservice.dtos.CardDTO;
import com.beardtrust.webapp.cardservice.dtos.CardTypeDTO;
import com.beardtrust.webapp.cardservice.entities.CardEntity;
import com.beardtrust.webapp.cardservice.entities.CurrencyValue;
import com.beardtrust.webapp.cardservice.models.CardRegistrationModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpRequestModel;
import com.beardtrust.webapp.cardservice.models.CardSignUpResponseModel;
import com.beardtrust.webapp.cardservice.models.CardUpdateModel;
import com.beardtrust.webapp.cardservice.services.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class provides the rest api endpoints and associated logic.
 *
 * @author Davis Hill <Davis.Hill@Smoothstack.com>
 * @author Matthew Crowell <Matthew.Crowell@Smoothstack.com>
 */
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cards")
@Slf4j
public class CardController {

    private final CardService cardService;

    /**
     * This method exposes an endpoint for health checks that returns the String "Healthy"
     * and a status code of 200.
     *
     * @return ResponseEntity<String> representing the status of the service
     */
    @PreAuthorize("permitAll()")
    @GetMapping(path = "/health")
    public ResponseEntity<String> healthCheck() {
        log.trace("Start of CardController.healthCheck()");
        log.debug("Health Check Incoming");
        log.trace("End of CardController.healthCheck()");
        return new ResponseEntity<>("Healthy", HttpStatus.OK);
    }

    /**
     * This method is used by administrators to register a card for a client of
     * BeardTrust.
     *
     * @param userId	String	the string representation of the user's unique user
     * id
     * @param cardRegistration	CardRegistrationModel	the data required to
     * register the card
     * @return	CardSignUpResponseModel	the card service's response to the
     * registration request
     */
    @PostMapping(path = "/register/{id}")
    @PreAuthorize("hasAuthority('admin')")
    @Consumes({MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CardSignUpResponseModel> registerCard(@PathVariable("id") String userId,
            @RequestBody CardRegistrationModel cardRegistration) {
        log.trace("Start of CardController.registerCard(" + userId + ", <redacted CardRegistrationModel>)");

        ResponseEntity<CardSignUpResponseModel> response = new ResponseEntity<>(
                cardService.registerCard(userId, cardRegistration), HttpStatus.CREATED
        );

        log.trace("End of CardController.registerCard(" + userId + ", <redacted CardRegistrationModel>)");

        return response;
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Page<CardDTO>> displayAllCards(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize,
            @RequestParam(name = "sortBy",
                    defaultValue = "id,asc") String[] sortBy,
            @RequestParam(name = "search", required = false) String searchCriteria) {
        log.trace("Start of CardController.displayAllCards(" + pageNumber + ", " + pageSize + ", " + Arrays.toString(sortBy) + ", " + searchCriteria +
                ")");        ResponseEntity<Page<CardDTO>> response;

        response = new ResponseEntity<>(cardService.getAll(pageNumber, pageSize, sortBy, searchCriteria),
                HttpStatus.OK);
        log.trace("End of CardController.displayAllCards(" + pageNumber + ", " + pageSize + ", " + Arrays.toString(sortBy) + ", " + searchCriteria +
                ")");
        return response;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public CardEntity displayCardById(@PathVariable String id) {

        log.trace("Start of CardController.displayCardById(" + id + ")");
        log.trace("End of CardController.displayCardById(" + id + ")");
        return cardService.getById(id);
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('admin')")
    public void updateCard(@RequestBody CardUpdateModel cardUpdateModel) {
        log.trace("Start of CardController.updateCard(<redacted CardUpdateModel for " +
                cardUpdateModel.getId() + ">)");
        cardService.update(cardUpdateModel);
        log.trace("End of CardController.updateCard(<redacted CardUpdateModel for " +
                cardUpdateModel.getId() + ">)");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public void deactivateCard(@PathVariable String id) {
        log.trace("Start of CardController.deactivateCard(" + id + ")");

        cardService.deactivateById(id);

        log.trace("End of CardController.deactivateCard(" + id + ")");
    }

    /**
     * This method receives a CardSignUpRequestModel object from a client of
     * BeardTrust seeking to apply for a new credit card and invokes the card
     * service to sign the user up for the specified card.
     *
     * @param userId String the user's unique id
     * @param signUpRequest CardSignUpRequestModel object containing application
     * data
     * @return ResponseEntity<CardDTO> the response from the server including
     * the new card's dto
     */
    @PostMapping(path = "/{id}")
    @PreAuthorize("principal == #userId")
    @Consumes({MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CardSignUpResponseModel> applyForCard(@PathVariable("id") String userId,
            @RequestBody CardSignUpRequestModel signUpRequest) {
        log.trace("Start of CardController.applyForCard(<redacted CardSignUpRequestModel for " + userId + ")");
        ResponseEntity<CardSignUpResponseModel> response;
        response = new ResponseEntity<>(cardService.applyForCard(userId, signUpRequest), HttpStatus.CREATED);
        log.trace("End of CardController.applyForCard(<redacted CardSignUpRequestModel for " + userId + ")");
        return response;
    }

    /**
     * This method retrieves the details of a single card associated with a
     * specific user id from the database.
     *
     * @param userId String the user id that must be associated with the card
     * @param id String the card id to search for
     * @return ResponseEntity<CardDTO> the http response entity with the card
     * details
     */
    @PreAuthorize("hasAuthority('admin') or principal == #userId")
    @GetMapping(path = "/{userId}/{cardId}")
    @Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CardDTO> getCardStatus(@PathVariable(value = "userId") String userId,
            @PathVariable(value = "cardId") String id) {
        log.trace("Start of CardController.getCardStatus(" + userId + ", " + id + ")");

        CardDTO status = cardService.getStatus(userId, id);
        ResponseEntity<CardDTO> response = new ResponseEntity<>(status, HttpStatus.OK);

        log.trace("End of CardController.getCardStatus(" + userId + ", " + id + ")");
        return response;
    }

    /**
     * This method retrieves a list of all cards associated with a user id from
     * the database.
     *
     * @param userId String the user id to search for
     * @return ResponseEntity<List < CardDTO>> the http response with the list
     * of all associated cards
     */
    @PreAuthorize("hasAuthority('admin') or principal == #userId")
    @GetMapping(path = "/{id}/all")
    @Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<CardDTO>> getCardsByUser(@PathVariable("id") String userId,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize,
            @RequestParam(name = "sortBy",
                    defaultValue = "id,asc") String[] sortBy,
            @RequestParam(name = "search", required = false) String searchCriteria) {
        log.trace("Start of CardController.getCardsByUser(" + userId + ", " + pageNumber + ", " + pageSize +
                ", " + Arrays.toString(sortBy) + ", " + searchCriteria + ")");
        ResponseEntity<Page<CardDTO>> response;
        log.info("Card list requested for " + userId);
        Page<CardDTO> cards;
        cards = cardService.getCardsByUser(userId, pageNumber, pageSize, sortBy, searchCriteria);
        response = new ResponseEntity<>(cards, HttpStatus.OK);
        log.trace("End of CardController.getCardsByUser(" + userId + ", " + pageNumber + ", " + pageSize +
                ", " + Arrays.toString(sortBy) + ", " + searchCriteria + ")");
        return response;
    }

    /**
     * This method exposes the card service's method for getting all available
     * card types to the /cards/available endpoint.
     *
     * @return ResponseEntity<List < CardTypeDTO>> list of all currently
     * available card types
     */
    @PreAuthorize("hasAuthority('user')")
    @GetMapping(path = "/available")
    @Produces({MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<CardTypeDTO>> getAvailableCardTypes(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize,
            @RequestParam(name = "sortBy",
                    defaultValue = "id,desc") String[] sortBy,
            @RequestParam(name = "search", required = false) String searchCriteria) {
        log.trace("Start of CardController.getAvailableCardTypes(" + pageNumber + ", " + pageSize +
                ", " + Arrays.toString(sortBy) + ", " + searchCriteria + ")");

        Page<CardTypeDTO> page = cardService.getAvailableCardTypes(pageNumber, pageSize, sortBy, searchCriteria);

        log.trace("End of CardController.getAvailableCardTypes(" + pageNumber + ", " + pageSize +
                ", " + Arrays.toString(sortBy) + ", " + searchCriteria + ")");

        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
