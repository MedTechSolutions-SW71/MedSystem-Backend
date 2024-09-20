package org.medTechSolutions.platform.security_service.auth.infrastructure.tokens.jwt;


import jakarta.servlet.http.HttpServletRequest;
import org.medTechSolutions.platform.security_service.auth.application.internal.outboundservices.tokens.TokenService;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;

public interface BearerTokenService extends TokenService {

    String getBearerTokenFrom(HttpServletRequest request);
    String generateToken(Authentication authentication);

}
