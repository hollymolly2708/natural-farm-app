package com.natural_farm.android.e_commerce.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("address")
	val address: AddressResponse? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("__v")
	val v: Int? = null,

	@field:SerializedName("name")
	val name: NameResponse? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class AddressResponse(

	@field:SerializedName("zipcode")
	val zipcode: String? = null,

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("street")
	val street: String? = null,

	@field:SerializedName("geolocation")
	val geolocation: GeolocationResponse? = null
)

data class GeolocationResponse(

	@field:SerializedName("lat")
	val lat: String? = null,

	@field:SerializedName("long")
	val jsonMemberLong: String? = null
)

data class NameResponse(

	@field:SerializedName("firstname")
	val firstname: String? = null,

	@field:SerializedName("lastname")
	val lastname: String? = null
)
