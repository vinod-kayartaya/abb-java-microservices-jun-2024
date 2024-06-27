package com.abb.customerservice.config;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final JwtGrantedAuthoritiesConverter defaultConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = defaultConverter.convert(jwt);

        // Extract roles from resource_access
        Collection<GrantedAuthority> roles = jwt.getClaimAsMap("resource_access")
                .values().stream()
                .filter(map -> map instanceof Map)
                .map(map -> (Map<String, Object>) map)
                .flatMap(map -> ((Collection<String>) map.get("roles")).stream())
                .map(role -> (GrantedAuthority) () -> role)
                .collect(Collectors.toList());

        return Stream.concat(authorities.stream(), roles.stream())
                .collect(Collectors.toList());
    }
}
