package com.example.cloneassignment.retrofit

data class UploadResponse(
var agreement_status: String = "",
var agreement_type: String = "",
var channel: String = "",
var created_at: String = "",
var file_name: String = "",
var id: String = "",
var is_agreement: Boolean = false,
var no_of_pages: Int = 0,
var self_sign_type: String = "",
var self_signed: Boolean = false,
var sign_request_details: SignRequestDetails = SignRequestDetails(),
var signing_parties: List<SigningParty> = listOf()

)
