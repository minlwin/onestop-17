import 'server-only'

import { ActivationForm, AuthResult, ModificationResult, SignInForm, SignUpForm } from "@/lib/types";
import { publicRequest } from '..';

export async function signIn(form: SignInForm) : Promise<AuthResult> {
    return await publicRequest("anonymous/auth/generate", form, {method : "POST"})
}

export async function signUp(form: SignUpForm) : Promise<ModificationResult<string>> {
    return await publicRequest("anonymous/signup", form, {method : "POST"})
}

export async function activate(id: string, form : ActivationForm) : Promise<ModificationResult<string>> {
    return await publicRequest(`anonymous/activate/${id}`, form, {method : "POST"})
}