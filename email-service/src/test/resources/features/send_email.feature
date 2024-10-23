Feature: Enviar un email

  Scenario: Enviar un email con detalles válidos
    Given el usuario está autenticado
    When el usuario envía una solicitud para enviar un email con los siguientes detalles:
      | to          | subject       | body            |
      | ampudiaflores@gmail.com | Equipo de MedSystem | Ampudia. Te damos la bienvenida a MedSystem! |
    Then el email debe ser enviado exitosamente
    And la respuesta debe contener el mensaje "Email sent"