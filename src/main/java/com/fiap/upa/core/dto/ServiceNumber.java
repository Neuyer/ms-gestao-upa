package com.fiap.upa.core.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record ServiceNumber(@JsonAlias("queueNumber") String number) {
}
