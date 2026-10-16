import 'server-only'

import { ActivationForm, AuthResult, ModificationResult, SignInForm, SignUpForm } from "@/lib/types";
import { publicRequest } from '..';

export async function signIn(form: SignInForm) : Promise<AuthResult> {
    return await publicRequest("anonymous/auth/generate", form, {method : "POST"})
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