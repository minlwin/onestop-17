import 'server-only'
import { ModificationResult, PageResult, RegistrationDetails, RegistrationListItem, RegistrationPageSearch, RegistrationStatusForm } from "@/lib/types"
import { secureRequest } from ".."

const BASE_API = "management/registrations"

export async function search(form : RegistrationPageSearch) : Promise<PageResult<RegistrationListItem>> {
    return await secureRequest(BASE_API, form)
}

export async function findById(id : string) : Promise<RegistrationDetails> {
    return await secureRequest(`${BASE_API}/${id}`)
}

export async function updateStatus(id : string, form : RegistrationStatusForm) : Promise<ModificationResult<string>> {
    return await secureRequest(`${BASE_API}/${id}`, form, {method : "PUT"})
}