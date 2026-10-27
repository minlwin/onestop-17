import { EntryDetails, EntryForm, EntryListItem, EntryPageSearch, PageResult } from "@/lib/types"
import "server-only"
import { secureRequest } from ".."

const BASE_API = "member/entry"

export async function search(form : EntryPageSearch) : Promise<PageResult<EntryListItem>> {
    return await secureRequest(BASE_API, form)
}

export async function findById(id : any) : Promise<EntryDetails> {
    return await secureRequest(`${BASE_API}/${id}`)
}

export async function create(form : EntryForm) : Promise<string> {
    return await secureRequest(BASE_API, form, {method : "POST"})
}