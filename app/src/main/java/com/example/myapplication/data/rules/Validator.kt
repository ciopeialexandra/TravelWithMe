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
    fun validatePrivacyPolicyAcceptance(statusValue: Boolean):ValidationResult {
        return ValidationResult(
            statusValue
        )
    }


}

data class ValidationResult(val status:Boolean = false)
