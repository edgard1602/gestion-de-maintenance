package com.inventaire.Inventaire_Actifs.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDTO {
    private String accessToken;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static JwtResponseDTOBuilder builder() {
        return new JwtResponseDTOBuilder();
    }

    public static class JwtResponseDTOBuilder {
        private String accessToken;
        private String type;

        public JwtResponseDTOBuilder token(String token) {
            this.accessToken = token;
            return this;
        }

        public JwtResponseDTOBuilder type(String type) {
            this.type = type;
            return this;
        }

        public JwtResponseDTOBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public JwtResponseDTO build() {
            JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
            jwtResponseDTO.setAccessToken(accessToken);
            jwtResponseDTO.setType(type);
            return jwtResponseDTO;
        }
    }
}
