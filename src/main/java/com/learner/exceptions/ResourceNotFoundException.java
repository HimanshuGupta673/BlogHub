package com.learner.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long fieldValue;
    public ResourceNotFoundException(String user, String id, long userId) {
      super(String.format("%s not found with %s : %s",user,id,userId));
      this.resourceName = user;
      this.fieldName = id;
      this.fieldValue = userId;
    }
}
