package com.teletic.oauth2resource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomPrincipal implements Serializable {
    private String username;
    private String email;
}
