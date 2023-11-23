package br.com.sup.api.dto;

public record HealthRegisterDto(
        String health, String mentalHealth, String substances, String substanceFrequencies, String goals
) {
}
