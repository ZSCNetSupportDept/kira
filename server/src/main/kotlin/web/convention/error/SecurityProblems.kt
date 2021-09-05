package love.sola.kira.web.convention.error

class BadCredentialProblem : ForbiddenProblem(
    title = "Bad Credential",
    detail = "The provided credential does not match."
)

class IllegalAccessProblem(detail: String) : ForbiddenProblem(
    title = "Illegal Access",
    detail = detail
)

class InvalidCredentialProblem : BadRequestProblem(
    detail = "The provided credential is not valid"
)

class LoginAlreadyUsedProblem : BadRequestProblem(
    detail = "Login name already used!"
)
