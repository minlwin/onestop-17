import 'server-only'

import { ActivationForm, AuthResult, ModificationResult, SignInForm, SignUpForm } from "@/lib/types";

export async function signIn(form: SignInForm) : Promise<AuthResult> {
    return {
        name : "John Doe",
        email : "johndoe@example.com",
        role : "Administrator",
        accessToken: "dummytoken",
        refreshToken: "dummytoken"
    }
}

export async function signUp(form: SignUpForm) : Promise<ModificationResult<string>> {
    return {
        result: "Your account has been created successfully. Please activate your account."
    }
}

export async function activate(form : ActivationForm) : Promise<ModificationResult<string>> {
    return {
        result: "Your account has been activated successfully. Please sign in to your account."
    }
}