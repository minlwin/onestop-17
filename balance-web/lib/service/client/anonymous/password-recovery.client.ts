import { ModificationResult } from '@/lib/types';
import { ForgotPasswordForm, ResetPasswordForm } from '@/lib/types/anonymous/auth.schema';
import 'server-only'

export async function sendRequest(form: ForgotPasswordForm) : Promise<ModificationResult<string>> {
    // Call backend API
    return {
        result: "We send security code to your email. Please reset your password with security code."
    }
}

export async function resetPassword(form : ResetPasswordForm) : Promise<ModificationResult<string>> {
    return {
        result: "Your password has been reset successfully. Please sign in again."
    }   
}