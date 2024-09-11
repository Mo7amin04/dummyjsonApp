package com.example.dummyjsonapp

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class login {

    val client = OkHttpClient()
    val mediaType = "application/json".toMediaType()
    val body = "{\r\n    \"username\": \"emilys\",\r\n    \"password\": \"emilyspass\" \r\n}".toRequestBody(mediaType)
    val request = Request.Builder()
        .url("https://dummyjson.com/auth/login")
        .post(body)
        .addHeader("Content-Type", "application/json")
        .addHeader("Cookie", "accessToken=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3MjU5NTQ0MDQsImV4cCI6MTcyNTk1ODAwNH0.cL2kXPdBsrHRpVHrG_x6OQe6iSh00K_3M2dyAVSBkz8; refreshToken=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3MjU5NTQ0MDQsImV4cCI6MTcyODU0NjQwNH0.zt6d2BPKnOi-rypHV_aaCDgx7G7x5B_ant4WFUE6ACY")
        .build()
    val response = client.newCall(request).execute()
}