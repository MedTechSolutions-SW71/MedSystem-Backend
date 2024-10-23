Feature: Crear una cita

  Scenario: Crear una nueva cita con detalles válidos
    Given el usuario está autenticado
    When el usuario envía una solicitud para crear una cita con los siguientes detalles:
      | fecha       | hora  | paciente_id | doctor_id | razon          |
      | 2023-10-15  | 10:00 | 123         | 456       | Consulta general |
    Then la cita debe ser creada exitosamente
    And la respuesta debe contener los detalles de la cita creada