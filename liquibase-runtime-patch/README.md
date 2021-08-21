# Patched Liquibase Runtime

This module patches several classes to make sure the liquibase command-line utilities function correctly.

The patched runtime is only used in gradle tasks, so no worries for affecting the final artifacts for production use.

## Patched Features

- Corrected the generated `UniqueConstraint` name to align with Hibernate's naming strategy.
- Added support for specifying author and context of generated `changeSet` from `diffChangelog` task.
