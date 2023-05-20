package com.example.cloneassignment.repository

import android.util.Base64
import android.util.Log
import com.example.cloneassignment.retrofit.DocumentRetrofitApiCalls
import com.example.cloneassignment.retrofit.Signer
import com.example.cloneassignment.retrofit.UploadRequest
import com.example.cloneassignment.retrofit.UploadResponse
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class MainRepository @Inject constructor(
    var documentRetrofitApiCalls: DocumentRetrofitApiCalls
) {
    suspend fun uploadPdfDocument(
        identifier: String,
        name: String,
        reason: String,
        fileName: String,
        fileData: String
    ): UploadResponse? {
        val header = HashMap<String, String>()
        header["content-type"] = "application/json"
        val signers = Signer(
            name = name,
            identifier = identifier,
            reason = reason
        )
        val request = UploadRequest(
            file_name = fileName,
            file_data = fileData,
            signers = listOf(signers)
        )
        return documentRetrofitApiCalls.uploadPdf(
            headers = header,
            request
        )
    }

    private fun getEncodedParameter(): String? {
        val clientId = "AIZ67DUSNZ8TGWJV4DZ7DI3T5Z2LN2W2"
        val clientSecret = "ASN9AVKHU6HF41KTU71G3KNXLG1ET7BC"
        val togetherString = "$clientId:$clientSecret"
        return Base64.encodeToString(
            togetherString.toByteArray(),
            Base64.NO_WRAP
        )
    }

    suspend fun retrieveDocumentDetails(documentId: String): UploadResponse? {
        val encodeToString = getEncodedParameter()
        val header = HashMap<String, String>()
        return documentRetrofitApiCalls.retrieveDocumentDetails(
            header,
            documentId
        )
    }

    suspend fun downloadPdfFile(documentId: String): File? {
        val target = File("document.pdf")
        try {
            val encodeToString = getEncodedParameter()
            val header = HashMap<String, String>()
            val resp = documentRetrofitApiCalls.retrievePdfFile(header, documentId).execute()
            if (resp.isSuccessful) {
                resp.body()?.byteStream()?.use {
                    target.parentFile?.mkdirs()
                    FileOutputStream(target).use { targetOutputStream ->
                        it.copyTo(targetOutputStream)
                    }
                } ?: throw RuntimeException("failed to download: $documentId")
            }
        } catch (e: Exception) {
            Log.d("TAG", "downloadPdfFile: ${e.printStackTrace()}")
        }

        return target
    }
}
