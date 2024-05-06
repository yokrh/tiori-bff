package com.example.tiori.bff.controller.response

data class ApiResponse<T: ApiResponse.Body>(
    val header: Header,
    val body: T?
) {
    data class Header(
        val status: String,
        val code: String,
        val message: String,
    ) {
        companion object {
            val SUCCESS = Header("S", "0000", "Successful")
            val ERROR = Header("F", "9000", "Failed")
        }
    }

    interface Body
    class EmptyBody: Body

    companion object {
        fun <T: Body> success(body: T): ApiResponse<T> {
            return ApiResponse(Header.SUCCESS, body)
        }

        fun success(): ApiResponse<EmptyBody> {
            return ApiResponse(Header.SUCCESS, null)
        }

        fun error(message: String): ApiResponse<EmptyBody> {
            return ApiResponse(Header.ERROR.copy(message = message), null)
        }
    }
}
