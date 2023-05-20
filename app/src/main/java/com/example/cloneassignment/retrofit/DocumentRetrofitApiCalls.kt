package com.example.cloneassignment.retrofit


import okhttp3.ResponseBody
import retrofit2.Call

import retrofit2.http.*

interface DocumentRetrofitApiCalls {

    @POST("/v2/client/document/uploadpdf")
    suspend fun uploadPdf(
        @HeaderMap headers: Map<String, String>,
        @Body apiBodyParams: UploadRequest
    ): UploadResponse?

    @GET("/v2/client/document/{documentId}")
    suspend fun retrieveDocumentDetails(
        @HeaderMap headers: Map<String, String>,
        @Path("documentId") documentId: String
    ): UploadResponse?

    @GET("/v2/client/document/download")
    @Streaming
    suspend fun retrievePdfFile(
        @HeaderMap headers: Map<String, String>,
        @Query("documentId") documentId: String
    ): Call<ResponseBody>
}