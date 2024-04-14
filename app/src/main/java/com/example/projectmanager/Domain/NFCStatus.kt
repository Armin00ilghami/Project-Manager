package com.example.projectmanager.Domain

enum class NFCStatus {
    NoOperation,
    Tap,
    Process,
    Confirmation,
    Read,
    Write,
    NotSupported,
    NotEnabled,
}