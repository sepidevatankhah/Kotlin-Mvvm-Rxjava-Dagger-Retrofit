package de.joyn.myapplication.domain.repository

interface ConnectivityManager {
    fun hasNetwork(): Boolean?
}