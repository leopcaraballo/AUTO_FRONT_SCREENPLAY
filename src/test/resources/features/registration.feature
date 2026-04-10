Feature: Patient arrival registration

  Scenario: Successful registration
    Given que el usuario está en la página de registro
    When ingresa datos de llegada válidos
    Then debería ver un mensaje de éxito en el registro de recepción

  Scenario: Failed registration
    Given que el usuario está en la página de registro
    When ingresa datos de llegada incompletos o inválidos
    Then debería ver mensajes de error de validación

  Scenario: Public landing page is visible
    Given que un visitante abre la pantalla pública
    Then el mensaje de bienvenida debería ser visible
