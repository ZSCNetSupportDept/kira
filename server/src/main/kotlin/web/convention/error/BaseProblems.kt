package love.sola.kira.web.convention.error

import org.zalando.problem.Exceptional
import org.zalando.problem.Status.*
import org.zalando.problem.StatusType
import org.zalando.problem.ThrowableProblem
import java.net.URI
import org.zalando.problem.AbstractThrowableProblem as AbstractThrowableProblem0

open class AbstractThrowableProblem(
    type: URI? = null,
    title: String? = null,
    status: StatusType? = null,
    detail: String? = null,
    instance: URI? = null,
    cause: ThrowableProblem? = null,
    parameters: Map<String, Any>? = null
) : AbstractThrowableProblem0(type, title, status, detail, instance, cause, parameters) {
    override fun getCause(): Exceptional? = super.cause
}

open class BadRequestProblem(
    title: String? = BAD_REQUEST.reasonPhrase,
    detail: String? = null,
    type: URI? = null
) : AbstractThrowableProblem(type, title, BAD_REQUEST, detail)

open class UnauthorizedProblem(
    title: String? = UNAUTHORIZED.reasonPhrase,
    detail: String? = "Full authentication is required to access this resource",
    type: URI? = null
) : AbstractThrowableProblem(type, title, UNAUTHORIZED, detail)

open class ForbiddenProblem(
    title: String? = FORBIDDEN.reasonPhrase,
    detail: String? = "Access is denied",
    type: URI? = null
) : AbstractThrowableProblem(type, title, FORBIDDEN, detail)

open class NotFoundProblem(
    title: String? = NOT_FOUND.reasonPhrase,
    detail: String? = null,
    type: URI? = null
) : AbstractThrowableProblem(type, title, NOT_FOUND, detail)
