Feature: my feature file

  Scenario Outline: browser launch test
    Given I launch browser
      | browser   |
      | <browser> |
    Examples:
      | browser |
      | chrome  |