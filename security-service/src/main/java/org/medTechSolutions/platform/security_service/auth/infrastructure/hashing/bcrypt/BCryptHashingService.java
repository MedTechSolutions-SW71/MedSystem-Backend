package org.medTechSolutions.platform.security_service.auth.infrastructure.hashing.bcrypt;


import org.medTechSolutions.platform.security_service.auth.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {

}
