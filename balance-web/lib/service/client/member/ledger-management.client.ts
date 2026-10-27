import { LedgerForm, LedgerListItem, LedgerSearch, ModificationResult } from "@/lib/types";
import "server-only"
import { secureRequest } from "..";

const BASE_API = "member/ledger"

export async function search(form : LedgerSearch) : Promise<LedgerListItem[]> {
    return await secureRequest(BASE_API, form)
}

export async function create(form : LedgerForm) : Promise<ModificationResult<string>> {
    return await secureRequest(BASE_API, form, {method : "POST"})
}

export async function update(id: any, form : LedgerForm) : Promise<ModificationResult<string>> {
    return await secureRequest(`${BASE_API}/${id}`, form, {method : "PUT"})
}
