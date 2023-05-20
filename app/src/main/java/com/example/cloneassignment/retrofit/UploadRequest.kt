package com.example.cloneassignment.retrofit

data class UploadRequest(
    var display_on_page: String = "all",
    var expire_in_days: Int = 10,
    var file_data: String = "",
    var file_name: String = "",
    var signers: List<Signer> = listOf()
)
