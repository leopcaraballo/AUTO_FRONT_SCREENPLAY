Feature: Consulta de trayectoria clínica del paciente
  Como usuario Supervisor del sistema RLApp
  Quiero consultar la trayectoria clínica de los pacientes
  Para garantizar trazabilidad, continuidad y visibilidad desde la interfaz

  Scenario: Acceder a la consola de trayectorias como Supervisor
    Given que el usuario está en la página de trayectorias
    When la consola de trayectorias carga
    Then debería ver la sección de consulta de trayectorias
    And debería ver los badges de estado en la página

  Scenario: Buscar trayectoria de paciente inexistente muestra vacío
    Given que el usuario está en la página de trayectorias
    When busca la trayectoria de un paciente inexistente
    Then debería ver que no se encontraron resultados de trayectoria

  Scenario Outline: Búsqueda con IDs límite muestra vacío — DDT boundary
    Given que el usuario está en la página de trayectorias
    When busca la trayectoria con patientId "<patientId>"
    Then debería ver que no se encontraron resultados de trayectoria

    Examples: IDs inválidos — boundary value analysis
      | patientId                                          |
      | X                                                  |
      | NONEXISTENT-AAAA-BBBB-CCCC-DDDDDDDDDDDD           |
      | 12345678901234567890123456789012345678901234567890   |
