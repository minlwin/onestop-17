import * as client from "@/lib/service/client/management/registration.client"
import { ModificationResult, PageResult, RegistrationListItem, RegistrationPageSearch, RegistrationStatusForm } from "@/lib/types";

export async function search(form : RegistrationPageSearch) : Promise<PageResult<RegistrationListItem>> {
    return await client.search(form)
}

export async function updateStatus(id : string, form : RegistrationStatusForm) : Promise<ModificationResult<string>> {
    return await client.updateStatus(id, form)
}
