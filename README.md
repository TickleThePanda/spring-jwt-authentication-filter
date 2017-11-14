Spring Framework filters that decode and verify a JWT token. Primarily used for my own
projects. Extracts the `roles` array from a JWT token and adds them as roles
to a token.

It expects the variable `jwt.secret.token` to be set in the environment.

