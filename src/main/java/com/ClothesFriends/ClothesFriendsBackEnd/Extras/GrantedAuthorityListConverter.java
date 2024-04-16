package com.ClothesFriends.ClothesFriendsBackEnd.Extras;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Converter;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.IOException;
import java.util.List;

@Converter
public class GrantedAuthorityListConverter implements AttributeConverter<List<GrantedAuthority>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<GrantedAuthority> authorities) {
        try {
            return objectMapper.writeValueAsString(authorities);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting authorities to JSON", e);
        }
    }

    @Override
    public List<GrantedAuthority> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<GrantedAuthority>>() {});
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to authorities", e);
        }
    }
}
