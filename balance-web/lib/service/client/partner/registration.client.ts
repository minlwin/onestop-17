import { ModificationResult, RegistrationForm } from '@/lib/types'
import 'server-only'
import { secureRequest } from '..'

const BASE_API = "partner/registration"

export async function create(form : RegistrationForm) : Promise<ModificationResult<string>> {
    return secureRequest(BASE_API, form)
}