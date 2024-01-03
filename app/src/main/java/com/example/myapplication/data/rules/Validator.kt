package com.example.myapplication.data.rules

object Validator {
    fun validateFirstName(fname: String):ValidationResult{
        return ValidationResult(
            (!fname.isNullOrEmpty()&&fname.length>=2)
        )
    }
    fun validateLastName(lname: String):ValidationResult{
        return ValidationResult(
            (!lname.isNullOrEmpty()&&lname.length>=2)
        )
    }
    fun validateEmail(emailV: String):ValidationResult{
        return ValidationResult(
            (!emailV.isNullOrEmpty())
        )
    }
    fun validatePassword(passwordV: String):ValidationResult{
        return ValidationResult(
            (!passwordV.isNullOrEmpty()&&passwordV.length>=2)
        )
    }
    fun validateDescription(descriptionV: String):ValidationResult{
        return ValidationResult(
            (!descriptionV.isNullOrEmpty()&&descriptionV.length>=3)
        )
    }
    fun validateCountry(countryV: String):ValidationResult{
        return ValidationResult(
            (!countryV.isNullOrEmpty()&&countryV.length>=4)
        )
    }
    fun validateCity(cityV: String):ValidationResult{
        return ValidationResult(
            (!cityV.isNullOrEmpty()&&cityV.length>=4)
        )
    }
    fun validateAttractions(attractionsV: String):ValidationResult{
        return ValidationResult(
            (!attractionsV.isNullOrEmpty()&&attractionsV.length>=3)
        )
    }
    fun validateRestaurants(restaurantsV: String):ValidationResult{
        return ValidationResult(
            (!restaurantsV.isNullOrEmpty()&&restaurantsV.length>=5)
        )
    }
    fun validatePrivacyPolicyAcceptance(statusValue: Boolean):ValidationResult {
        return ValidationResult(
            statusValue
        )
    }


}

data class ValidationResult(val status:Boolean = false)
