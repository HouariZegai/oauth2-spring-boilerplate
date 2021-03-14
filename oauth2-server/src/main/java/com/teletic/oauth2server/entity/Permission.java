package com.teletic.oauth2server.entity;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Permission extends BaseIdEntity {
    private String name;
}
