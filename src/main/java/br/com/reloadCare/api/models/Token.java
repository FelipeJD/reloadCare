package br.com.reloadCare.api.models;

public record Token(
    String token,
    String type,
    String prefix,
    Long id
) {
}
