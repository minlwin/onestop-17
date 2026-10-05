import { ModificationResult } from '@/lib/types';
import { ForgotPasswordForm } from '@/lib/types/forms/auth.schema';
import 'server-only'

export async function sendRequest(form: ForgotPasswordForm) : Promise<ModificationResult<string>> {
    // Call backend API
    return {
        result: "We send security code to your email. Please reset your password with security code."
    }
}