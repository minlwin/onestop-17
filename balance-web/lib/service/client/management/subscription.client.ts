import 'server-only'
import { ModificationResult, PageResult, SubscriptionDetails, SubscriptionListItem, SubscriptionPageSearch, SubscriptionStatusForm } from "@/lib/types";
import { secureRequest } from "..";

const BASE_API = "management/subscriptions"

export async function search(form : SubscriptionPageSearch) : Promise<PageResult<SubscriptionListItem>> {
    return await secureRequest(BASE_API, form)
}

export async function findById(id : string) : Promise<SubscriptionDetails> {
    return await secureRequest(`${BASE_API}/${id}`)
}

export async function updateStatus(id : string, form : SubscriptionStatusForm) : Promise<ModificationResult<string>> {
    return await secureRequest(`${BASE_API}/${id}`, form, {method : "PUT"})
}