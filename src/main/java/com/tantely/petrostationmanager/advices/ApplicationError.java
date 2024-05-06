package com.tantely.petrostationmanager.advices;

import java.time.LocalDate;

public record ApplicationError (
  String message,
  LocalDate date,
  int status
){}
