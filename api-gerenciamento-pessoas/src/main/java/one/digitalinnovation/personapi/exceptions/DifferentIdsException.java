package one.digitalinnovation.personapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DifferentIdsException extends Exception{

    public DifferentIdsException(Long pageId, Long entityId){
        super(String.format("The IDs from request and entity are different: %d != %d", pageId, entityId));
    }
}
