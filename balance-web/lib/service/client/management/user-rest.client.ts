import "server-only"

import { ModificationResult } from "@/lib/types";
import { RetirementForm, UserDetails, UserForm, UserListItem, UserSearch } from "@/lib/types/management/user.model";
import { secureRequest } from "..";

const BASE_URL = "management/users"

export async function search(form : UserSearch) : Promise<UserListItem[]> {
    return await secureRequest(BASE_URL, form)
}

export async function findById(id : string) : Promise<UserDetails> {
    return await secureRequest(`${BASE_URL}/${id}`)
}

export async function create(form: UserForm) : Promise<ModificationResult<string>> {
    return await secureRequest(BASE_URL, form, {method : 'POST'})
}

export async function updateRetirement(id : string, form : RetirementForm) : Promise<ModificationResult<string>> {
    return await secureRequest(`${BASE_URL}/${id}`, form, {method : 'PUT'})
}