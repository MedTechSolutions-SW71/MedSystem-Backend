Feature: Laboratory Management

  Scenario: Crear un laboratorio exitosamente
    Given tengo los detalles del laboratorio
    When envío una solicitud para crear un laboratorio
    Then el laboratorio debe ser creado exitosamente