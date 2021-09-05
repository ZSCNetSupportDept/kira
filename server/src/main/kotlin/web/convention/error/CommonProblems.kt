package love.sola.kira.web.convention.error

class ResourceNotFoundProblem : NotFoundProblem(
    detail = "Specified resource could not be found"
)

class AmbiguousCreateProblem : BadRequestProblem(
    title = "Ambiguous Create",
    detail = "The creating resource must not have an existing identifier (non-zero id)"
)

class AmbiguousUpdateProblem : BadRequestProblem(
    title = "Ambiguous Update",
    detail = "The updating resource's id must match the path"
)

/**
 * Indicates the request itself is not structurally valid
 * and, most likely, it is mistakenly crafted by frontend,
 * which is NOT the end-user's fault.
 */
class InvalidRequestProblem(detail: String?) : BadRequestProblem(
    title = "Invalid Request",
    detail = detail
)

/**
 * Indicates the request itself is structurally valid, but
 * the actual requested input can not be processed.
 *
 * The different between this and [InvalidRequestProblem] is that
 * the requested input may be controlled solely by the end-user,
 * which in turn is the end-user's fault.
 */
class BadUserRequestProblem(detail: String?) : BadRequestProblem(
    detail = detail
)
