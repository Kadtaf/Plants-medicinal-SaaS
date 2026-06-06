package com.plants.api.utils;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.regex.Pattern;

@Component
public class SlugUtils {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    /**
     * Génère un slug à partir d'une chaîne de caractères.
     *
     * @param input Chaîne de caractères à convertir en slug.
     * @return Slug généré.
     */
    public String generateSlug(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Normaliser les caractères accentués
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Remplacer les caractères non-Latin par un tiret
        String noNonLatin = NONLATIN.matcher(normalized).replaceAll("-");
        // Remplacer les espaces par des tirets
        String noWhitespace = WHITESPACE.matcher(noNonLatin).replaceAll("-");
        // Convertir en minuscules
        String slug = noWhitespace.toLowerCase();
        // Supprimer les tirets consécutifs
        slug = slug.replaceAll("-+", "-");
        // Supprimer les tirets au début et à la fin
        slug = slug.replaceAll("^-|-$", "");

        return slug;
    }

    /**
     * Génère un slug unique en ajoutant un suffixe numérique si nécessaire.
     *
     * @param input    Chaîne de caractères à convertir en slug.
     * @param existingSlugs Liste des slugs existants.
     * @return Slug unique.
     */
    public String generateUniqueSlug(String input, java.util.Set<String> existingSlugs) {
        String slug = generateSlug(input);
        String uniqueSlug = slug;
        int suffix = 1;

        while (existingSlugs.contains(uniqueSlug)) {
            uniqueSlug = slug + "-" + suffix;
            suffix++;
        }

        return uniqueSlug;
    }
}

