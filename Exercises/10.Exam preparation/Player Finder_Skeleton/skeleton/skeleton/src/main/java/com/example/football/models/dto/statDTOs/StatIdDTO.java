package com.example.football.models.dto.statDTOs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class StatIdDTO {

    @XmlElement
    private Long id;

    public Long getId() {
        return id;
    }
}
